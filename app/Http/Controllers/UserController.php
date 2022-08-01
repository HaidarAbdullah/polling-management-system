<?php

namespace App\Http\Controllers;

use App\Models\NationalNumber;
use Illuminate\Http\Request;
use App\Models\User;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Hash;
class UserController extends Controller
{
    public function checkout(Request $request)
    {
        $number=NationalNumber::where('number',$request->number)->get();
        if($number->isEmpty())
        {
            return response()->json([
                'success'=>'true',
                'message'=>'welocm in our family'
            ]);
        }
        return response()->json([
            'success'=>'false',
            'message'=>'you are already have an account'
        ]);
    }

    public function register(Request $request) 
    {
        $user = User::create($request->all());
        $user->password = Hash::make($user->password);
        $user->save();

        $token = $user->createToken('token')->plainTextToken;

        return response()->json([
            'user' => $user,
            'token' => $token
        ], 200);
    }

    public function login(Request $request)
    {
        if (!Auth::attempt(['email' => $request['email'], 'password' => $request['password']])) {
            return response()->json([
                'success' => 'false',
                'message' => 'credientials not match'
            ]);
        }
        $user = auth()->user();
        return response()->json([
            'success'=>'true',
            'message'=>'welcome',
            'token' => $user->createToken('token')->plainTextToken
        ]);
    }

    public function logout()
    {
        auth()->user()->tokens()->delete();
        return response()->json([
            'message' => 'logged out successfully !'
        ]);
    }
    
    
}
