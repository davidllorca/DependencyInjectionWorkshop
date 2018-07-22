package me.davidllorca.diworkshop.ui.main;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.davidllorca.diworkshop.R;
import me.davidllorca.diworkshop.data.model.Character;
import me.davidllorca.diworkshop.ui.common.mvcviews.BaseViewMvc;

public class ListViewMvcImpl extends BaseViewMvc<ListViewMvc.Listener>
implements ListViewMvc {

    private RecyclerView mRecyclerView;
    private CharacterAdapter mCharacterAdapter;

    public ListViewMvcImpl(LayoutInflater inflater, ViewGroup container) {
        setRootView(inflater.inflate(R.layout.activity_list, container, false));

        // init recycler view
        mRecyclerView = findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mCharacterAdapter = new CharacterAdapter(new OnCharacterClickListener() {
            @Override
            public void onCharacterClicked(Character character) {
                for (Listener listener : getListeners()){
                    listener.onCharacterClicked(character);
                }
            }
        });
        mRecyclerView.setAdapter(mCharacterAdapter);
    }

    @Override
    public void bindCharacters(List<Character> characters) {
        mCharacterAdapter.bindData(characters);
    }

    // --------------------------------------------------------------------------------------------
    // RecyclerView adapter
    // --------------------------------------------------------------------------------------------


    public interface OnCharacterClickListener {
        void onCharacterClicked(Character character);
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
                    mListener.onCharacterClicked(mCharacterList.get(position));
                }
            });
        }

        @Override
        public int getItemCount() {
            return mCharacterList.size();
        }
    }
}
