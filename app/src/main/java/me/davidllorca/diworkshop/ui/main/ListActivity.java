package me.davidllorca.diworkshop.ui.main;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.davidllorca.diworkshop.Constants;
import me.davidllorca.diworkshop.R;
import me.davidllorca.diworkshop.data.model.Character;
import me.davidllorca.diworkshop.data.remote.CharacterListResponse;
import me.davidllorca.diworkshop.data.remote.RickAndMortyApi;
import me.davidllorca.diworkshop.ui.common.ServerErrorDialogFragment;
import me.davidllorca.diworkshop.ui.detail.DetailActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListActivity extends AppCompatActivity  implements
        Callback<CharacterListResponse> {

    private RecyclerView mRecyclerView;
    private CharacterAdapter mCharacterAdapter;

    private RickAndMortyApi mRickAndMortyApi;

    private Call<CharacterListResponse> mCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // init recycler view
        mRecyclerView = findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mCharacterAdapter = new CharacterAdapter(new OnCharacterClickListener() {
            @Override
            public void onQuestionClicked(Character character) {
                DetailActivity.start(ListActivity.this, character.getId());
            }
        });

        mRecyclerView.setAdapter(mCharacterAdapter);

        // init retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mRickAndMortyApi = retrofit.create(RickAndMortyApi.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mCall = mRickAndMortyApi.getAllCharacters();
        mCall.enqueue(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mCall != null) {
            mCall.cancel();
        }
    }

    @Override
    public void onResponse(Call<CharacterListResponse> call, Response<CharacterListResponse> response) {
        CharacterListResponse responseSchema;
        if (response.isSuccessful() && (responseSchema = response.body()) != null) {
            mCharacterAdapter.bindData(responseSchema.getCharacter());
        } else {
            onFailure(call, null);
        }
    }

    @Override
    public void onFailure(Call<CharacterListResponse> call, Throwable t) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(ServerErrorDialogFragment.newInstance(), null)
                .commitAllowingStateLoss();
    }

    // --------------------------------------------------------------------------------------------
    // RecyclerView adapter
    // --------------------------------------------------------------------------------------------


    public interface OnCharacterClickListener {
        void onQuestionClicked(Character character);
    }

    public static class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {

        private final OnCharacterClickListener mListener;

        private List<Character> mCharacterList = new ArrayList<>(0);

        public class CharacterViewHolder extends RecyclerView.ViewHolder {
            public TextView mName;

            public CharacterViewHolder(View view) {
                super(view);
                mName = view.findViewById(R.id.tv_name);
            }
        }

        public CharacterAdapter(OnCharacterClickListener onCharacterClickListener) {
            mListener = onCharacterClickListener;
        }

        public void bindData(List<Character> characters) {
            mCharacterList = new ArrayList<>(characters);
            notifyDataSetChanged();
        }

        @Override
        public CharacterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_character, parent, false);

            return new CharacterViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(CharacterViewHolder holder, final int position) {
            holder.mName.setText(mCharacterList.get(position).getName());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onQuestionClicked(mCharacterList.get(position));
                }
            });
        }

        @Override
        public int getItemCount() {
            return mCharacterList.size();
        }
    }
}
