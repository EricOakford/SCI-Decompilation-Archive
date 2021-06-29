;;; Sierra Script 1.0 - (do not remove this comment)
(script# TWROOM)
(include game.sh)
(use Main)
(use PolyPath)
(use Game)
(use User)
(use System)

(public
	proc896_0 0
)

(local
	toX
	toY
)
(procedure (proc896_0)
)

(class ADRoom of Room
	(properties
		style FADEOUT
		autoLoad 0
	)
	
	(method (init &tmp [temp0 2])
		(= number curRoomNum)
		(= perspective picAngle)
		(if picture
			(self drawPic: picture)
		)
		(cond 
			((not (cast contains: ego)) 0)
			(script 0)
		)
		(if autoLoad
			(Load RES_MESSAGE curRoomNum)
			(Lock RES_MESSAGE curRoomNum 1)
		)
	)
	
	(method (doit &tmp dir)
		(= dir -1)
		(cond 
			(script (script doit:))
			(
				(= dir
					(switch ((User alterEgo?) edgeHit?)
						(NORTH north)
						(EAST east)
						(SOUTH south)
						(WEST west)
					)
				)
				(theGame handsOff:)
				(if (== dir -1)
					(self setScript: sRW 0 ((User alterEgo?) edgeHit?))
				else
					(self setScript: lRS 0 dir)
				)
			)
		)
	)
	
	(method (newRoom n)
		(Lock RES_MESSAGE curRoomNum 0)
		(super newRoom: n)
	)
)

(instance lRS of Script

	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= temp1
					(/ (CelWide (ego view?) (ego loop?) (ego cel?)) 2)
				)
				(switch register
					((client north?)
						(curRoom newRoom: register)
					)
					((client south?)
						(= temp0 (CelHigh (ego view?) (ego loop?) (ego cel?)))
						(ego setMotion: PolyPath (ego x?) (+ 189 temp0) self)
					)
					((client east?)
						(ego setMotion: PolyPath (+ 319 temp1) (ego y?) self)
					)
					((client west?)
						(ego setMotion: PolyPath (- 0 temp1) (ego y?) self)
					)
				)
			)
			(1 (ego hide:) (= cycles 2))
			(2
				(curRoom setScript: 0 newRoom: register)
			)
		)
	)
)

(instance sRW of Script

	(method (changeState newState &tmp egoX egoY temp2 temp3)
		(switch (= state newState)
			(0
				(= toX (= egoX (ego x?)))
				(= toY (= egoY (ego y?)))
				(= temp3 (CelWide (ego view?) (ego loop?) (ego cel?)))
				(= temp2 (CelHigh (ego view?) (ego loop?) (ego cel?)))
				(switch register
					(1
						(+= toY 5)
					)
					(2
						(+= egoX temp3)
						(-= toX 5)
					)
					(3
						(+= egoY temp2)
						(-= toY 5)
					)
					(4
						(-= egoX temp3)
						(+= toX 5)
					)
				)
				(ego ignoreActors: TRUE setMotion: PolyPath egoX egoY self)
			)
			(1 (= seconds 2))
			(2
				(ego setMotion: PolyPath toX toY self)
			)
			(3
				(ego ignoreActors: FALSE)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)
