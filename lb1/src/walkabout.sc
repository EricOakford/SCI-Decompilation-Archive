;;; Sierra Script 1.0 - (do not remove this comment)
(script# 381)
(include game.sh)
(use Main)
(use Intrface)
(use Path)
(use Avoider)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	walkabout 0
)
(synonyms
	(butler person fellow)
)

(local
	theGJeevesX = [0 0 0 0 100 174 167 163 205 163 260 130 0 0 0 0 260 130 70 170 0 0 0 0 114 124 80 170 20 47 100 168]
	local36 = [0 0 0 0 -20 140 340 163 340 163 340 130 0 0 140 125 140 125 140 240 0 0 0 0 111 124 -20 170 -40 47 -20 168]
	local72 = [0 0 0 0 -20 176 -20 161 -20 150 154 144 0 0 0 0 303 101 0 0 0 0 0 0 0 0 340 175 80 47 340 168]
	local108 = [53 12 4 10 3 9 58 58 9 13 69 69 13 21 22 23 12 35]
	room9aPts = [
		32 152
		66 124
		114 124
		PATHEND
		]
	room9bPts = [
		66 124
		32 162
		340 162
		PATHEND
		]
	room23Pts = [
		66 143
		64 174
		-20 174
		PATHEND
		]
)
(instance walkabout of Region
	
	(method (init &tmp [temp0 50])
		(super init:)
		(|= global195 $0400)
		(Load SCRIPT AVOIDER)
		(Load SCRIPT PATH)
		(if (== [global368 2] 0) (= [global368 2] 1800))
		(Jeeves
			setAvoider: ((Avoider new:) offScreenOK: TRUE)
			setCycle: Walk
			loop: 1
			init:
			hide:
		)
		(= gMyMusic 0)
		(if
			(and
				(== [local108 (- 17 global155)] curRoomNum)
				(> [global368 2] (* (- 16 global155) 100))
			)
			(if gJeevesX
				(= [theGJeevesX (* global155 2)] gJeevesX)
				(= [theGJeevesX (+ (* global155 2) 1)] gJeevesY)
			)
			(Jeeves
				posn: [theGJeevesX (* global155 2)] [theGJeevesX (+ (* global155 2) 1)]
				show:
			)
			(self setScript: walkThru)
		)
	)
	
	(method (doit &tmp [temp0 50])
		(if
			(and
				(== (mod [global368 2] 100) 0)
				(== [local108 (/ [global368 2] 100)] curRoomNum)
			)
			(= global155 (- (- 18 (/ [global368 2] 100)) 1))
			(if
				(and
					(not (& (ego onControl: FALSE) cRED))
					(== global155 8)
				)
				(self setScript: outTheDoor)
			else
				(Jeeves
					posn: [local36 (* global155 2)] [local36 (+ (* global155 2) 1)]
					show:
				)
				(if (== curRoomNum 23) (= [local72 (* global155 2)] 0))
				(self setScript: walkThru)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(if script
			(= gJeevesX (Jeeves x?))
			(= gJeevesY (Jeeves y?))
		else
			(= gJeevesX 0)
			(= gJeevesY 0)
		)
		(super dispose:)
		(DisposeScript AVOIDER)
		(DisposeScript PATH)
	)
	
	(method (handleEvent event &tmp temp0)
		(return (if (event claimed?) (return TRUE) else FALSE))
	)
)

(instance walkThru of Script

	(method (changeState newState &tmp [temp0 50])
		(switch (= state newState)
			(0
				(Jeeves show:)
				(if (== [local72 (* global155 2)] 0)
					(cond 
						((and (== curRoomNum 9) (== global155 9))
							(Jeeves setMotion: room9a self)
						)
						((and (== curRoomNum 9) (== global155 12))
							(Jeeves setMotion: room9b self)
						)
						((== curRoomNum 23)
							(Jeeves setMotion: room23 self)
						)
					)
				else
					(Jeeves
						setMotion:
							MoveTo
							[local72 (* global155 2)]
							[local72 (+ (* global155 2) 1)]
							self
					)
				)
			)
			(1
				(if (== global155 5)
					(if (== (gDoor cel?) 0)
						(HandsOff)
						(gDoor cycleSpeed: 3 setCycle: EndLoop self)
						(gMySound number: 107 play:)
					else
						(= state 0)
						(= cycles 1)
					)
				else
					(++ global155)
					(= [global368 2] (- 1700 (* global155 100)))
					(Jeeves hide:)
					(client setScript: 0)
				)
			)
			(2
				(HandsOn)
				(Jeeves setPri: 2 setMotion: MoveTo 140 125 self)
			)
			(3
				(gDoor cycleSpeed: 3 setCycle: BegLoop self)
				(gMySound number: 107 play:)
			)
			(4
				(++ global155)
				(= [global368 2] (- 1700 (* global155 100)))
				(gDoor stopUpd:)
				(Jeeves setPri: -1 hide:)
				(client setScript: 0)
			)
		)
	)
)

(instance outTheDoor of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(gDoor cycleSpeed: 3 setCycle: EndLoop self)
				(gMySound number: 107 play:)
				(Jeeves show: posn: 140 125 setPri: 2 loop: 2)
			)
			(1
				(Jeeves setPri: -1 setMotion: MoveTo 164 144 self)
			)
			(2
				(HandsOn)
				(Jeeves setMotion: MoveTo 303 101 self)
				(gDoor setCycle: BegLoop)
				(gMySound number: 108 play:)
			)
			(3
				(gDoor stopUpd:)
				(++ global155)
				(= [global368 2] (- 1700 (* global155 100)))
				(Jeeves setPri: -1 hide:)
				(client setScript: 0)
			)
		)
	)
)

(instance Jeeves of Actor
	(properties
		y 131
		x 286
		view 440
	)
	
	(method (handleEvent event)
		(= theTalker talkJEEVES)
		(cond 
			((or (MousedOn self event shiftDown) (Said 'examine/butler'))
				(event claimed: TRUE)
				(if (& global207 $0400)
					(Print 381 0)
				else
					(|= global207 $0400)
					(Say 0 381 1)
				)
				(event claimed: TRUE)
			)
			((Said 'flirt//butler<with')
				(Print 381 2)
			)
			((Said 'ask,tell//*<about')
				(Print 381 3)
			)
			((Said 'hold,deliver/*')
				(if (and theInvItem haveInvItem)
					(Print 381 4)
				else
					(DontHave)
				)
			)
			((Said '/butler>')
				(cond 
					((Said 'converse')
						(Print 381 3)
					)
					((Said 'get')
						(Print 381 5)
					)
					((Said 'kill')
						(Print 381 6)
					)
					((Said 'kiss')
						(Print 381 7)
					)
					((Said 'embrace')
						(Print 381 8)
					)
				)
			)
		)
	)
)

(instance room9a of Path
	
	(method (at n)
		(return [room9aPts n])
	)
)

(instance room9b of Path
	
	(method (at n)
		(return [room9bPts n])
	)
)

(instance room23 of Path

	(method (at n)
		(return [room23Pts n])
	)
)
