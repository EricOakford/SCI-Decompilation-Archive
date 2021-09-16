;;; Sierra Script 1.0 - (do not remove this comment)
(script# 21)
(include game.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Motion)
(use Game)
(use Invent)
(use Actor)
(use System)

(public
	Room21 0
)

(local
	aBall
	ripple1
	ripple2
	ripple3
	saveViewer
)
(instance Room21 of Room
	(properties
		picture 21
	)
	
	(method (init)
		(= north 15)
		(= south 27)
		(= east 22)
		(= west 20)
		(= horizon 68)
		(= isIndoors FALSE)
		(ego edgeHit: 0)
		(super init:)
		(if isNightTime
			(curRoom overlay: 121)
		)
		(self setRegions: WOODS WATER RIVER)
		(Load VIEW 518)
		(Load VIEW 21)
		(Load VIEW 23)
		(= ripple1 (Prop new:))
		(= ripple2 (Prop new:))
		(= ripple3 (Prop new:))
		(ripple3
			isExtra: TRUE
			view: 657
			loop: 2
			cel: 0
			posn: 295 118
			setPri: 0
			cycleSpeed: 1
			setCycle: Forward
			ignoreActors:
			init:
		)
		(ripple1
			isExtra: TRUE
			view: 657
			loop: 0
			cel: 2
			posn: 13 181
			setPri: 0
			cycleSpeed: 1
			setCycle: Forward
			ignoreActors:
			init:
		)
		(ripple2
			isExtra: TRUE
			view: 657
			loop: 1
			cel: 0
			posn: 122 154
			setPri: 0
			cycleSpeed: 1
			setCycle: Forward
			ignoreActors:
			init:
		)
		(if (< (ego y?) horizon)
			(ego y: (+ horizon 2))
		)
		(if ((Inventory at: iGoldBall) ownedBy: 21)
			((= aBall (View new:))
				view: 518
				loop: 1
				cel: 0
				x: 164
				y: 129
				stopUpd:
				init:
			)
		)
		(switch prevRoomNum
			(0
				(ego posn: 160 185)
			)
			(26
				(cond 
					((!= (ego view?) 2)
						(ego posn: 1 174)
					)
					((< (ego x?) 191)
						(ego posn: 1 136)
					)
					(else
						(ego posn: 7 186)
					)
				)
				(RedrawCast)
			)
			(22
				(cond 
					((!= (ego view?) 2)
						(ego posn: 318 116)
					)
					(
						(and
							(>= (ego y?) 105)
							(< (ego y?) 121)
							(== (ego view?) 2)
						)
						(ego posn: 318 102)
					)
					(else
						(ego posn: 318 (ego y?))
					)
				)
			)
			(20
				(if (> (ego y?) 140)
					(ego posn: 1 139)
				else
					(ego posn: 1 (ego y?))
				)
			)
			(27
				(ego posn: (ego x?) (- 189 (ego yStep?)))
			)
			(15
				(ego posn: (ego x?) (+ horizon 2))
			)
		)
		((ego viewer?) doit:)
		(ego init:)
		(if (== prevRoomNum 0)
			(ego x: 180 y: 188)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if (and (== (event type?) saidEvent) (Said 'look>'))
				(cond 
					((Said '<under/bridge')
						(cond 
							((ego inRect: 120 130 195 139)
								(ego setScript: egoActions)
							)
							(
								(or
									(ego inRect: 70 115 254 169)
									(ego inRect: 189 93 318 139)
								)
								(Print 21 0)
							)
							(else
								(Print 800 1)
							)
						)
					)
					((Said '/bridge')
						(Print 21 1)
					)
					((Said '[<around][/room]')
						(Print 21 2)
					)
				)
			else
				FALSE
			)
		)
	)
	
	(method (newRoom n)
		(if (ego inRect: -6 166 20 192)
			(super newRoom: 26)
		else
			(super newRoom: n)
		)
	)
)

(instance egoActions of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setAvoider: Avoider setMotion: MoveTo 141 133 self)
			)
			(1
				(= saveViewer (ego viewer?))
				(ego setAvoider: 0 viewer: 0)
				(ego loop: 0 view: 21 cel: 255 setCycle: EndLoop self)
			)
			(2
				(ego view: 23 loop: 0 cel: 255 setCycle: EndLoop self)
			)
			(3
				(if ((Inventory at: iGoldBall) ownedBy: 21)
					(aBall dispose:)
					(theGame changeScore: 2)
					(Print 21 3 #draw)
					(= gotItem TRUE)
					((Inventory at: iGoldBall) moveTo: ego)
				else
					(Print 21 4 #draw)
				)
				(ego loop: 2 cel: 255 setCycle: EndLoop self)
			)
			(4
				(ego view: 21 setCel: 255 setCycle: BegLoop self)
			)
			(5
				(ego view: 2 setCycle: Walk)
				(ego viewer: saveViewer)
				(HandsOn)
			)
		)
	)
)
