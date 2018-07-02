  $(document).ready(function() {
     var orderId;
     var sendName;
    var rcvName; 
    var kind;
    var company; 
    var fast;
    var secure;
    var reason;

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
             last_name: {
                validators: {
                     stringLength: {
                        min: 2,
                    },
                    notEmpty: {
                        message: '请填写收件人姓名'
                    }
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

                sendName = $('#input-send-name').val();
                rcvName = $('#input-rcv-name').val();
                kind = $('#input-kind').val();
                company = $('#input-company').val();
                fast = $('#input-fast').val();
                secure = $('#input-secure').val();
                reason = $('#input-reason').val();
                orderId = $('#input-order-id').val();

                $("#show-send-name").html(sendName);
                $("#show-rcv-name").html(rcvName);
                $("#show-kind").html(kind);
                $("#show-company").html(company);
                $("#show-order-id").html(orderId);
                $("#show-fast").html(fast);
                $("#show-secure").html(secure);
                $("#show-reason").html(reason);


                $('#input-form').fadeOut("200",function(){
                    $('#show-form').fadeIn("200",function(){
                    });

                });
                
            }
        });

        
        $("#show-cancel").on('click',function(){
            $("#show-form").fadeOut("200",function(){
                $('#submit-info').removeAttr("disabled"); 
                $("#input-form").fadeIn("200");
            });
        });

        $('#show-ok').on('click',function(){
            location.href="http://wap.guoguo-app.com/wuliuDetail.htm?mailNo="+orderId+"";
            //window.open("http://wap.guoguo-app.com/wuliuDetail.htm?mailNo="+orderId+"","物流查询");
        });

});