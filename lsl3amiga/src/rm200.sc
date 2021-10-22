;;; Sierra Script 1.0 - (do not remove this comment)
(script# 200)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm200 0
)

(local
	local0
	[plotString 222]
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

(instance rm200 of Room
	(properties
		picture 200
		east 210
		south 210
	)
	
	(method (init)
		(Load VIEW 200)
		(super init:)
		(self setScript: RoomScript)
		(if (and (Btst fOpening200) (not (Btst fCredits200)))
			(Load VIEW 201)
			(aCredit1 init:)
			(aCredit2 init:)
		)
		(addToPics add: atpBinocular1 add: atpBinocular2 doit:)
		(if (and (!= prevRoomNum 203) (!= prevRoomNum 206))
			(ego posn: 315 167 loop: 1)
		)
		(NormalEgo)
		(ego init:)
	)
)

(instance RoomScript of Script
	(method (doit)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not (Btst fCredits200))
					(= seconds 5)
				)
			)
			(1
				(Format @plotString 200 18)
				(= seconds (PrintPlot))
			)
			(2
				(Bset fCredits200)
				(if (not (Btst fBrokeUp))
					(Format @plotString 200 19)
					(= seconds (PrintPlot))
				)
			)
			(3
				(= seconds 0)
			)
		)
	)
	
	(method (handleEvent event)
		(if
			(and
				(not (super handleEvent: event))
				modelessDialog
				(== (event message?) ENTER)
				(== (event type?) keyDown)
			)
			(event claimed: TRUE)
			(cls)
			(self cue:)
		)
		(cond 
			((Said '/binocular,binocular>')
				(cond 
					((Said 'get')
						(Print 200 0)
					)
					((Said 'use,(look<through,in)')
						(cond 
							(playingAsPatti
								(Print 200 1)
							)
							(
								(and
									(not (& (ego onControl:) cGREEN))
									(not (& (ego onControl:) cCYAN))
							 	)
								(NotClose)
							)
							((& (ego onControl:) cCYAN)
								(Print 200 2)
							)
							((Btst fLookedInBinoculars)
								(Print 200 3)
							)
							(else (Ok)
								(curRoom newRoom: 206)
							)
						)
					)
					((Said 'look')
						(Print 200 4)
					)
				)
			)
			((Said 'get/awning')
				(Print 200 5)
				(Print 200 6 #at -1 144)
			)
			((or (Said 'look/air') (Said 'up<look'))
				(Print 200 7)
			)
			((Said 'look>')
				(cond 
					((Said '/air,up')
						(Print 200 7)
					)
					((Said '/cliff,land,cliff')
						(Print 200 8)
					)
					((Said '/fence,rail')
						(Print 200 9)
					)
					((Said '/cliff,edge')
						(Print 200 10)
					)
					((Said '/bay,beach,point,bay,bay')
						(Print 200 11)
					)
					((Said '/camp,down,building,casino,hotel,trap')
						(Print 200 12)
						(if (<= filthLevel 1)
							(Print 200 13)
						)
					)
					((Said '/blade,carpet,carpet')
						(Print 200 14)
					)
					((Said '/bird')
						(Print 200 15)
					)
					((Said '/awning')
						(if (& (ego onControl:) cBLUE)
							(if (not (Btst fLookedAtPlaque))
								(Bset fLookedAtPlaque)
								(theGame changeScore: 2)
							)
							(Ok)
							(curRoom newRoom: 203)
						else
							(NotClose)
						)
					)
					((Said '[/look,area]')
						(Print 200 16)
						(Print 200 17)
					)
				)
			)
		)
	)
)

(instance atpBinocular1 of PicView
	(properties
		y 82
		x 137
		view 200
		signal ignrAct
	)
)

(instance atpBinocular2 of PicView
	(properties
		y 79
		x 205
		view 200
		cel 1
		signal ignrAct
	)
)

(instance aCredit1 of Prop
	(properties
		y 131
		x 288
		view 201
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
		view 201
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
				(Bset fCredits200)
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
