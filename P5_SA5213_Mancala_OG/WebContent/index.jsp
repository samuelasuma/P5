<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>


<html lang="en">

<link href="resources/css/style.css" rel="stylesheet" />


<head>
<meta charset="utf-8">
<title>Mancala</title>

<link rel="stylesheet" href="resources/css/app.css">
</head>
<body data-player="one">
	<h1>Mancala</h1>

	<form method="post" action="<c:url value='MancalaController'/>">

		<div class="board">
			<div class="player-two store">
				<p>${cala.pits[6]}</p>
			</div>

			<div class="rows">
				<div class="row player-two">
					<c:forEach items="${cala.pits}" var="pit" begin="0" end="5"
						varStatus="status">

						<div class="pit">
						
							<button type="submit" name="pit_index" value="${status.index}"
								class="pit_btn" >
								<p>${pit}</p>
							</button>
						
							
						</div>



					</c:forEach>
				</div>
				<div class="row player-one">
					<c:forEach items="${cala.pits}" var="pit" begin="7" end="12"
						varStatus="status">


						<div class="pit">
							<button name="pit_index" type="submit" value="${status.index}"
								class="pit_btn" >
								<p>${pit}</p>
							</button>
						</div>


					</c:forEach>
				</div>

			</div>

			<div class="player-one store">
				<p>${cala.pits[13]}</p>
			</div>
		</div>
	</form>

	<p class="status">

		<c:choose>
			<c:when test="${cala.gameOver(cala.pits)}">
				<c:choose>
					<c:when
						test="${cala.gameOver(cala.pits)== 'One' ||  cala.gameOver(cala.pits) == 'Two'}">
				
						${cala.winner(cala.gameOver(cala.pits))} Won!
						</c:when>
					<c:otherwise>
						<p class="draw">Draw!</p>	
						</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
		
		Current player: <span class="current-player" data-player="${currentplayer}">${currentplayer}</span>

			</c:otherwise>
		</c:choose>

	</p>


	<button class="new-game" name="reset"
						type="submit">New Game</button>
	




</body>



</html>