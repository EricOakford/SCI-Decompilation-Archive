;;; Sierra Script 1.0 - (do not remove this comment)
(script# 404)
(include game.sh)
(use Main)
(use PolyPath)
(use System)


(class MChase of PolyPath
	(properties
		who 0
		distance 0
		targetX 0
		targetY 0
		doitSkips 10
	)
	
	(method (init actor theWho dist whoCares obstList &tmp [buffer 20])
		(if argc
			(cond 
				((>= argc 5)
					(= obstacles obstList)
				)
				((not (IsObject obstacles))
					(= obstacles (curRoom obstacles?))
				)
			)
			(if (>= argc 1)
				(= client actor)
				(if (>= argc 2)
					(= who theWho)
					(= targetX (who x?))
					(= targetY (who y?))
					(if (>= argc 3)
						(= distance dist)
						(if (>= argc 4)
							(= caller whoCares)
						)
					)
				)
			)
			(super init: client targetX targetY caller 1 obstacles)
		)
	)
	
	(method (doit)
		(cond 
			(
				(>=
					(GetDistance targetX targetY (who x?) (who y?))
					distance
				)
				(if (not doitSkips)
					(= doitSkips 10)
					(if points
						(Memory MDisposePtr points)
					)
					(= points 0)
					(= value 2)
					(self init: client who)
				else
					(if
					(>= (Abs (- gameTime b-moveCnt)) (client moveSpeed?))
						(= b-moveCnt gameTime)
						(DoBresen self)
					)
					(-- doitSkips)
				)
			)
			(
				(<=
					(= global461
						(GetDistance
							(client x?)
							(client y?)
							(who x?)
							(who y?)
						)
					)
					distance
				)
				(self moveDone:)
			)
			(
				(>=
					(= global461
						(GetDistance
							(client x?)
							(client y?)
							(who x?)
							(who y?)
						)
					)
					global462
				)
				(self moveDone:)
			)
			(
			(>= (Abs (- gameTime b-moveCnt))
				(client moveSpeed?))
				(= b-moveCnt gameTime)
				(DoBresen self)
			)
		)
	)
	
	(method (moveDone &tmp temp0)
		(cond 
			((or (<= global461 distance) (> global461 global462))
				(client cue:)
			)
			((== (WordAt points value) $7777)
				(if points
					(Memory MDisposePtr points)
				)
				(= points 0)
				(= value 2)
				(self init: client who)
			)
			(else
				(self init: client)
			)
		)
	)
)
