package com.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.os.Bundle;
import com.model.MainFunc;
import com.model.Restriction;
import java.util.List;
import com.model.GraphObjective;
import com.model.GraphRestriction;
import com.model.Objective;
import com.model.Restriction;
import com.model.constants;
import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {

    private MutableLiveData<List<Restriction>> restrictsMutable;
    public LiveData<List<Restriction>> restricts = restrictsMutable;


    private MutableLiveData<MainFunc> mainFunctionMutable;
    public LiveData<MainFunc> mainFunction = mainFunctionMutable;

    }

    public void GraphSolution(Bundle bundle){

    }

    public Object ConvertToGraphRestriction(Restriction[] restrictions){

        List<GraphRestriction> result = new ArrayList<>();

        for(Restriction restric : restrictions){
            double[] coeffs = restric.getDoubleCoeffs();

            if(coeffs.length != 2)
                return false;

            Float x_value = (float)coeffs[0];
            Float y_value = (float)coeffs[1];
            Float result_value = (float)restric.ResultCoeffAsDouble() - (float)restric.FreeCoeffAsDouble();
            constants.Sign sign = restric.sign;

            result.add(new GraphRestriction(x_value, y_value, sign, result_value));
        }

        return result;
    }

    public Object ConvertToGraphObjective(Objective objective){

        double[] coeffs = objective.getDoubleCoeffs();

        if(coeffs.length != 2)
            return false;

        Float x_value = (float)coeffs[0];
        Float y_value = (float)coeffs[1];
        constants.GoalType goal = objective.goal_type;

        return new GraphObjective(x_value, y_value, goal);
    }
}
