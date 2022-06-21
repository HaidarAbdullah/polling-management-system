<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Choice extends Model
{
    use HasFactory;
    protected $table = "choices";      ### Rename table name if you want , it depends on your local DataBase ###
    protected $fillable=['box_id','option1','option2','option3','option4','option5'];
    public $timestamps=false;
}
