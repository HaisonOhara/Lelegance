<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Especifico</title>
    </head>
    <body>
        <form method="post" action="preAssinar">
            <div style="height: 300px; width: 200px;">
                <p>${estilo.descricao}</p>
                <p>${estilo.valor}</p>
                <select name="plano">
                    <option value="1">1</option>
                    <option value="3">3</option>
                    <option value="6">6</option>
                </select>
                <!--atributo alterado para hiden,Usuario nao precisa visualizar id-->
                <p><input type="hidden" name="id" value="${estilo.id}"</p>

                <!--<p><input type="text" name="MedidaCamiseta" value="Tamanho Camiseta"</p>-->
                <p>
                <h3>Medida Camiseta</h3>
                <select type="text" name="MedidaCamiseta">
                    <option value="PP">PP</option>
                    <option value="P">P</option>
                    <option value="M">M</option>
                    <option value="G">G</option>
                    <option value="GG">GG</option>
                </select>
                </p>
                <p>
                <h3>Medida Calça</h3>
                <select type="number" name="MedidaCalca">
                    <option value=33>33</option>
                    <option value=34>34</option>
                    <option value=35>35</option>
                    <option value=36>36</option>
                    <option value=37>37</option>
                    <option value=38>38</option>
                    <option value=39>39</option>
                    <option value=40>40</option>
                    <option value=41>41</option>
                    <option value=42>42</option>
                    <option value=43>43</option>
                    <option value=44>44</option>
                    <option value=46>46</option>                                    
                </select>
                </p>
                <p>
                <h3>Medida Calçado</h3>
                <select type="number" name="MedidaCalcado">
                    <option value=34>34</option>
                    <option value=36>36</option>
                    <option value=38>38</option>
                    <option value=40>40</option>
                    <option value=42>42</option>
                    <option value=44>44</option>
                    <option value=46>46</option>
                    <option value=48>48</option>
                    <option value=50>50</option>                                     
                </select>
                </p>

                <p> <input type="submit" value="comprar"> </p>
            </div>
        </form>
    </body>
</html>
