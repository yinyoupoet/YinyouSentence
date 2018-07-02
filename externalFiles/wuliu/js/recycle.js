  $(document).ready(function() {

    var name;
    var phone; 
    var kind;
    var address; 
    var method;
    var grade;

    $('#contact_form').bootstrapValidator({
        // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            first_name: {
                validators: {
                        stringLength: {
                        min: 2,
                    },
                        notEmpty: {
                        message: '请填写寄件人姓名'
                    }
                }
            },
             telephone: {
                validators: {
                     stringLength: {
                        min: 11,
                        max: 11,
                        message: '请检查电话号码长度',
                    },
                    notEmpty: {
                        message: '请填写电话号码'
                    },
                    numeric:{message: '请不要输入非法字符'}
                }
            },
            delivery: {
                validators: {
                    notEmpty: {
                        message: '请填写您选择的快递公司'
                    }
                }
            },
            order_id: {
                validators: {
                    notEmpty:{message: '快递单号不能为空'},
                    numeric:{message: '请输入正确的快递单号'}
                }
            },
            category: {
                validators: {
                    notEmpty: {
                        message: '请输入退货商品的种类'
                    }
                }
            },
            address: {
                validators: {
                     stringLength: {
                        min: 8,
                    },
                    notEmpty: {
                        message: 'Please supply your street address'
                    }
                }
            },
            city: {
                validators: {
                     stringLength: {
                        min: 4,
                    },
                    notEmpty: {
                        message: 'Please supply your city'
                    }
                }
            },
            state: {
                validators: {
                    notEmpty: {
                        message: 'Please select your state'
                    }
                }
            },
            zip: {
                validators: {
                    notEmpty: {
                        message: 'Please supply your zip code'
                    },
                    zipCode: {
                        country: 'US',
                        message: 'Please supply a vaild zip code'
                    }
                }
            },
            comment: {
                validators: {
                      stringLength: {
                        min: 10,
                        max: 200,
                        message:'Please enter at least 10 characters and no more than 200'
                    },
                    notEmpty: {
                        message: 'Please supply a description of your project'
                    }
                    }
                }
            },
            submitHandler: function (validator, form, submitButton) {
                //alert("submit");
                /*$("#input-form").hide();
                console.log("一次");*/

                name = $('#input-name').val();
                phone = $('#input-tel').val();
                kind = $('#input-kind').val();
                address = $('#input-address').val();
                method = $('#input-method').val();
                grade = $('#input-grade').val();


                $("#show-name").html(name);
                $("#show-telephone").html(phone);
                $("#show-kind").html(kind);
                $("#show-address").html(address);
                $("#show-method").html(method);
                $("#show-grade").html(grade);

                $('#input-form').fadeOut("200",function(){
                    $('#show-form').fadeIn("200",function(){
                    });

                });
                
            }
        });

        
        $('#show-ok').on('click',function(){
            location.href="./return.html";
        });

});