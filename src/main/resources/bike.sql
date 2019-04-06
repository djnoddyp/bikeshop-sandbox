INSERT INTO public.bike(
            id, colour, created_on, make, model, price, stock, style)
    VALUES (6,5,'2019-03-01','GT','Tequesta',399.99,2,1);

    update public.bike set created_on = '2019-03-01' where created_on is null;


