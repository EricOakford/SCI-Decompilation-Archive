;;; Sierra Script 1.0 - (do not remove this comment)
(script# 650)
(include game.sh)
(use Main)
(use Intrface)
(use Reverse)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm650 0
)

(local
	local0
	congratsMsg
	[plotString 222]
)
(procedure (PrintPlot theWidth &tmp t)
	(if (< argc 1)
		(= theWidth 200)
	)
	(Print @plotString
		#at -1 10
		#width theWidth
		#time (= t (PrintDelay @plotString))
		#dispose
	)
	(return (+ 2 t))
)

(instance rm650 of Room
	(properties
		picture 650
	)
	
	(method (init)
		(HandsOff)
		(Load SCRIPT REVERSE)
		(aLeg init:)
		(aMonitor init:)
		(aLeftHand init:)
		(aRightHand init:)
		(aDoor init:)
		(aTheEnd init:)
		(super init:)
		(addToPics
			add: atpKeyboard
			add: atpGlass
			add: atpBigGlass
			doit:
		)
		(self setScript: RoomScript)
		(= saveSpeed (theGame setSpeed: 6))
		(NormalEgo 6 650)
		(HandsOff)
		(ego
			posn: 999 999
			setPri: 15
			setStep: 1 1
			setLoop: 6
			ignoreActors:
			moveSpeed: 2
			init:
		)
	)
)

(instance RoomScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 3)
			)
			(1
				(Format @plotString 650 0)
				(= seconds (PrintPlot))
			)
			(2
				(Format @plotString 650 1)
				(= seconds (PrintPlot))
			)
			(3
				(Format @plotString 650 2)
				(= seconds (PrintPlot 122))
			)
			(4
				(Format @plotString 650 3)
				(= seconds (PrintPlot 111))
			)
			(5
				(Format @plotString 650 4)
				(= seconds (PrintPlot))
			)
			(6
				(Format @plotString 650 5)
				(aMonitor hide:)
				(= seconds (PrintPlot))
			)
			(7
				(aMonitor show: setLoop: 5 setCycle: Forward)
				(aDoor posn: 64 99)
				(ego posn: 64 103)
				(= seconds 6)
			)
			(8
				(ego setMotion: MoveTo 64 100 self)
			)
			(9
				(aDoor setCycle: EndLoop self)
			)
			(10
				(ego posn: 999 999)
				(= seconds 3)
			)
			(11
				(aDoor setCycle: BegLoop self)
			)
			(12
				(= seconds 6)
			)
			(13
				(aMonitor hide:)
				(aDoor posn: 234 345)
				(ego posn: 999 999)
				(= seconds 3)
			)
			(14
				(aMonitor show: loop: 3 setCycle: Forward)
				(= seconds 3)
			)
			(15
				(if congratsMsg
					(= seconds 7)
					(= state 6)
				else
					(aTheEnd init: setCycle: EndLoop self)
				)
			)
			(16
				(aTheEnd setLoop: 1 cycleSpeed: 3 setCycle: Forward)
				(= seconds 3)
			)
			(17
				(= congratsMsg TRUE)
				(Format @plotString 650 6)
				(= seconds (PrintPlot))
			)
			(18
				(Format @plotString 650 7)
				(= seconds (PrintPlot))
			)
			(19
				(if
					(and
						(Btst fGotSuntan)
						(Btst fMetDale)
						(Btst fScrewedBambi)
						(Btst fListenedToComedian)
						(not (Btst fSkippedRafting))
						(not (Btst fFoundGymKeyAccidentally))
						(Btst fScrewedSuzi)
					)
					(Format @plotString 650 8)	;congratulations!
				else
					(Format @plotString 650 9)	;something you didn't do
				)
				(= seconds (PrintPlot))
			)
			(20
				(if (not (Btst fSkippedRafting))
					(self cue:)
				else
					(Format @plotString 650 10)
					(= seconds (PrintPlot))
				)
			)
			(21
				(if (Btst fListenedToComedian)
					(self cue:)
				else
					(Format @plotString 650 11)
					(= seconds (PrintPlot))
				)
			)
			(22
				(if (Btst fFoundGymKeyAccidentally)
					(Format @plotString 650 12)
					(= seconds (PrintPlot))
				else
					(self cue:)
				)
			)
			(23
				(if (Btst fGotSuntan)
					(self cue:)
				else
					(Format @plotString 650 13)
					(= seconds (PrintPlot))
				)
			)
			(24
				(if (not (Btst fMetDale))
					(Format @plotString 650 14)
					(= seconds (PrintPlot))
				else
					(self cue:)
				)
			)
			(25
				(if (Btst fScrewedBambi)
					(self cue:)
				else
					(Format @plotString 650 15)
					(= seconds (PrintPlot))
				)
			)
			(26
				(if (Btst fScrewedSuzi)
					(self cue:)
				else
					(Format @plotString 650 16)
					(= seconds (PrintPlot))
				)
			)
			(27
				(= state 6)
			)
		)
	)
	
	(method (handleEvent event)
		(if
			(and
				(not (super handleEvent: event))
				(not (event claimed?))
				modelessDialog
				seconds
				(== (event message?) ENTER)
				(== (event type?) keyDown)
			)
			(event claimed: TRUE)
			(= seconds 0)
			(cls)
			(self cue:)
		)
	)
)

(instance atpKeyboard of PicView
	(properties
		y 189
		x 60
		view 650
		priority 14
	)
)

(instance atpGlass of PicView
	(properties
		y 158
		x 221
		view 650
		cel 1
		priority 15
		signal ignrAct
	)
)

(instance atpBigGlass of PicView
	(properties
		y 173
		x 192
		view 650
		cel 2
		priority 15
		signal ignrAct
	)
)

(instance aRightHand of Prop
	(properties
		y 173
		x 103
		view 650
		loop 1
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 15 setCycle: Forward ignoreActors:)
	)
	
	(method (doit &tmp temp0)
		(super doit:)
		(cond 
			((== (= temp0 (Random 0 33)) 1)
				(self posn: 100 178)
			)
			((== temp0 2)
				(self posn: 101 184)
			)
			((== temp0 3)
				(self posn: 34 180)
			)
			((== temp0 4)
				(self posn: 36 184)
			)
			((< temp0 17)
				(self cel: 0)
			)
			(else
				(self cel: 1)
			)
		)
	)
)

(instance aLeftHand of Prop
	(properties
		y 173
		x 17
		view 650
		loop 2
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 15 setCycle: Forward ignoreActors:)
	)
	
	(method (doit &tmp temp0)
		(super doit:)
		(cond 
			((== (= temp0 (Random 0 33)) 1)
				(self posn: 11 173)
			)
			((== temp0 2)
				(self posn: 18 180)
			)
			((== temp0 3)
				(self posn: 8 185)
			)
			((< temp0 17)
				(self cel: 0)
			)
			(else
				(self cel: 1)
			)
		)
	)
)

(instance aMonitor of Prop
	(properties
		y 104
		x 61
		view 650
		loop 3
	)
	
	(method (init)
		(super init:)
		(self setPri: 4 setCycle: Forward ignoreActors:)
	)
	
	(method (doit)
		(super doit:)
		(if (== loop 3)
			(switch (Random 0 7)
				(1
					(self setCycle: Forward)
				)
				(2
					(self setCycle: Reverse)
				)
				(3
					(self cycleSpeed: (Random 0 1))
				)
			)
		)
	)
)

(instance aLeg of Prop
	(properties
		y 189
		x 306
		view 650
		loop 4
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setCycle: Forward)
	)
	
	(method (doit)
		(super doit:)
		(switch (Random 0 7)
			(0 (self setCycle: Forward))
			(7 (self setCel:))
		)
	)
)

(instance aDoor of Prop
	(properties
		y 990
		x 640
		view 650
		loop 7
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 7 ignoreActors:)
	)
)

(instance aTheEnd of Prop
	(properties
		y 47
		x 96
		view 651
	)
)
