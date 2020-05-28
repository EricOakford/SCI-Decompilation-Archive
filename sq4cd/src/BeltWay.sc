;;; Sierra Script 1.0 - (do not remove this comment)
(script# BELTWAY) ;804
(include sci.sh)
(use Main)
(use System)


(local
	local0 =  1
)
(class BeltWay of Code
	(properties
		who 0
		xStep 0
		yStep 0
		xOff 0
		yOff 0
		xTweak 0
		yTweak 0
		key 0
		head 0
		xDir 0
		yDir 0
		onCon 0
		tixCnt 0
		cnt 0
	)
	
	(method (doit &tmp theXTweak theYTweak)
		(if (not onCon)
			(if
			(and (user canControl:) (not (== curRoom 400)))
				(switch (who loop?)
					(0 (who x: (+ (who x?) 5)))
					(1 (who x: (- (who x?) 5)))
					(2 (who y: (+ (who y?) 5)))
					(3 (who y: (- (who y?) 5)))
					(4
						(who y: (+ (who y?) 5) x: (+ (who x?) 5))
					)
					(5
						(who y: (+ (who y?) 5) x: (- (who x?) 5))
					)
					(6
						(who y: (- (who y?) 5) x: (+ (who x?) 5))
					)
					(7
						(who y: (- (who y?) 5) x: (- (who x?) 5))
					)
				)
			)
			(who setMotion: 0 setHeading: head)
			(= onCon 1)
			(= cnt xOff)
			(who moveSpeed: speed)
		)
		(if
			(or
				(and
					(who mover?)
					(>=
						(Abs (- gameTime ((who mover?) b-moveCnt?)))
						(who moveSpeed?)
					)
				)
				(and
					(not (who mover?))
					(>= (Abs (- gameTime tixCnt)) speed)
				)
			)
			(if (not (who mover?))
				(= tixCnt gameTime)
			else
				(= tixCnt ((who mover?) b-moveCnt?))
			)
			(cond 
				((and (== (who heading?) key) (who mover?)) (= cnt xOff) (= theXTweak xTweak) (= theYTweak yTweak))
				((not cnt) (= cnt xOff) (= theXTweak xStep) (= theYTweak yOff))
				(else (-- cnt) (= theXTweak xStep) (= theYTweak yStep))
			)
			(who
				x: (+ (who x?) (* theXTweak xDir))
				y: (+ (who y?) (* theYTweak yDir))
			)
		)
	)
)
