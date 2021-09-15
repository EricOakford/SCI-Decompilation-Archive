;;; Sierra Script 1.0 - (do not remove this comment)
(script# 210)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm210 0
)

(local
	[plotString 200]
)
(procedure (PrintPlot &tmp t)
	(Print @plotString
		#at 10 5
		#width 290
		#time (= t (PrintDelay @plotString))
		#dispose
	)
	(return (+ 3 t))
)

(instance rm210 of Room
	(properties
		picture 210
		south 220
	)
	
	(method (init)
		(super init:)
		(self setScript: RoomScript)
		(cond 
			((not (Btst fCredits210))
				(Load VIEW 53)
			)
			((not (Btst fBrokeUp))
				(Load VIEW 53)
			)
			((not (Btst fCredits210))
				(Load VIEW 212)
				(aCredit1 init:)
				(aCredit2 init:)
			)
		)
		(if (InRoom iWood)
			(Load VIEW 709)
			(aWood init:)
		)
		(cond 
			((== prevRoomNum 200)
				(ego posn: 317 126 loop: 1)
			)
			((== prevRoomNum 216)
				(ego posn: 2 163)
			)
			((== prevRoomNum 213)
				(ego posn: 2 175)
			)
			(else
				(ego posn: 317 175)
			)
		)
		(NormalEgo)
		(ego init:)
	)
)

(instance RoomScript of Script
	(method (doit)
		(super doit:)
		(if (ego edgeHit?)
			(cond 
				((& (ego onControl:) cCYAN)
					(curRoom newRoom: 200)
				)
				((& (ego onControl:) cGREEN)
					(curRoom newRoom: 216)
				)
				((& (ego onControl:) cRED)
					(curRoom newRoom: 220)
				)
				((& (ego onControl:) cBLUE)
					(curRoom newRoom: 213)
				)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((not (Btst fCredits210))
						(= cycles 30)
					)
					((not (Btst fBrokeUp))
						(= cycles 20)
						(++ state)
					)
				)
			)
			(1
				(Format @plotString 210 5)
				(= seconds (PrintPlot))
			)
			(2
				(Bset fCredits210)
				(if (not (Btst fBrokeUp))
					(aCredit1
						view: 53
						posn: 0 121
						setCycle: Forward
						cycleSpeed: 1
						ignoreActors:
						init:
						setPri: 12
					)
					(Format @plotString 210 6)
					(= seconds (PrintPlot))
				)
			)
			(3
				(aCredit1 dispose:)
				(= seconds 0)
			)
			(4
				(= cycles (= seconds 0))
				(Ok)
				(HandsOff)
				(if (< (ego x?) (aWood x?))
					(ego
						illegalBits: 0
						setMotion: MoveTo (- (aWood x?) 19) (ego y?) self
					)
				else
					(ego
						illegalBits: 0
						setMotion: MoveTo (+ (aWood x?) 19) (ego y?) self
					)
				)
			)
			(5
				(ego
					view: 709
					loop: (> (ego x?) (aWood x?))
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(6
				(aWood hide:)
				(ego get: iWood setCycle: BegLoop self)
			)
			(7
				(NormalEgo)
				(theGame changeScore: 2)
				(Print 210 7)
			)
		)
	)
	
	(method (handleEvent event &tmp temp0)
		(if
			(and
				(not (super handleEvent: event))
				(not (event claimed?))
				modelessDialog
				(== (event message?) ENTER)
				(== (event type?) keyDown)
			)
			(event claimed: TRUE)
			(cls)
			(self cue:)
		)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(if (Said '/palm')
				(Printf 210 0
					(if (InRoom iWood)
						{ Beneath its outstretched boughs lies a beautiful piece of wood, probably cut by a native then forgotten.}
					else
						{}
					)
				)
			)
			(if (and (InRoom iWood) (Said '/backdrop,granadilla'))
				(Print 210 1)
			)
			(if (Said '[/area]')
				(Print 210 2)
			)
		)
		(if (Said 'get/granadilla')
			(cond 
				((!= currentStatus egoNORMAL)
					(GoodIdea)
				)
				((not (InRoom iWood))
					(Print 210 3)
				)
				((not (& (ego onControl:) cBROWN))
					(Print 210 4)
				)
				(else
					(self changeState: 4)
				)
			)
		)
	)
)

(instance aWood of View
	(properties
		y 161
		x 223
		view 210
	)
)

(instance aCredit1 of Prop
	(properties
		y 131
		x 288
		view 212
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 15 ignoreActors:)
	)
)

(instance aCredit2 of Prop
	(properties
		y 154
		x 288
		view 212
		loop 1
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 15 ignoreActors: setScript: CreditsScript)
	)
)

(instance CreditsScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 4)
			)
			(1
				(aCredit1 setCycle: EndLoop)
				(= cycles 16)
			)
			(2
				(aCredit2 setCycle: EndLoop)
				(= cycles 22)
			)
			(3
				(Bset fCredits210)
				(aCredit1 setCycle: BegLoop)
				(aCredit2 setCycle: BegLoop self)
			)
			(4
				(aCredit1 dispose:)
				(aCredit2 dispose:)
			)
		)
	)
)
