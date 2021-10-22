;;; Sierra Script 1.0 - (do not remove this comment)
(script# 610)
(include game.sh)
(use Main)
(use n021)
(use Intrface)
(use Follow)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm610 0
)

(instance rm610 of Room
	(properties
		picture 610
		east 620
	)
	
	(method (init)
		(HandsOff)
		(Bset fSaveDisabled)
		(Bset fCursorHidden)
		(Bset fAutoSaveDisabled)
		(= saveSpeed (theGame setSpeed: 6))
		(= playingAsPatti TRUE)
		(= currentEgoView 801)
		(= currentEgo (Format @egoName 610 0))
		(super init:)
		(addToPics
			add: atpSign
			add: atpStencil1
			add: atpStencil2
			add: atpHouse1
			add: atpHouse2
			add: atpHouse3
			add: atpHouse4
			add: atpHouse5
			add: atpHouse6
			add: atpHouse7
			doit:
		)
		(self setScript: RoomScript)
		(HandsOff)
		(Load SOUND 4)
		(Load SOUND 5)
		(Load SOUND 6)
		(Load SOUND 12)
		(Load SOUND 611)
		(Load SOUND 699)
		(aMan1 init:)
		(aMan2 init:)
		(aCar1 init:)
		(aCar2 init:)
		(aTowers init:)
		(aHole init:)
		(aCamera init:)
		(ego
			illegalBits: 0
			posn: 171 -50
			view: 804
			setLoop: 0
			setPri: 14
			setCycle: Walk
			setStep: 1 11
			init:
		)
		(aLarry
			ignoreHorizon:
			illegalBits: 0
			posn: 73 -70
			view: 631
			setLoop: 1
			setPri: 9
			setCycle: Walk
			setStep: 1 11
			init:
		)
	)
)

(instance RoomScript of Script
	(method (doit)
		(super doit:)
		(if (& (ego onControl:) cBLUE)
			(curRoom newRoom: 620)
		)
	)
	
	(method (changeState newState)
		(ChangeScriptState self newState 5 2)
		(switch (= state newState)
			(0
				(if (< prevRoomNum curRoomNum)
					(= seconds 3)
				)
			)
			(1
				(Print 610 5 #at 10 40 #width 290)
				(= seconds 3)
			)
			(2
				(Print 610 6)
				(= seconds 2)
			)
			(3
				(Print 610 7)
				(music number: 610 loop: 1 play: self)
				(= seconds 3)
			)
			(4
				(Print 610 8 #time 2)
				(= seconds 3)
			)
			(5
				(Print 610 9 #time 2)
				(aCamera setCycle: Forward)
				(= seconds 3)
			)
			(6
				(Print 610 10 #time 2)
				(= seconds 3)
			)
			(7
				(aCamera setMotion: MoveTo 0 0 setCycle: Forward)
				(aCar1 setMotion: MoveTo 0 129)
				(aMan1 setMotion: MoveTo 0 143)
				(Man2Script cue:)
			)
			(8
				(cls)
				(Print 610 11)
				(music number: 4 loop: 1 play:)
				(aMan1
					moveSpeed: 0
					cycleSpeed: 0
					setStep: 5 5
					setMotion: MoveTo -20 (aMan1 y?)
				)
				(aMan2
					moveSpeed: 0
					cycleSpeed: 0
					setStep: 5 5
					setMotion: MoveTo (aMan2 x?) 222
				)
				(aCar1 stopUpd:)
				(aCar2 stopUpd:)
				(aCamera stopUpd:)
				(ego setMotion: MoveTo 171 119 self)
			)
			(9
				(aHole dispose:)
				(music number: 12 loop: 1 play:)
				(ego
					posn: 171 143
					setPri: 12
					setMotion: MoveTo 175 186 self
				)
			)
			(10
				(ego loop: 1 cel: 0 stopUpd:)
				(Animate (cast elements?) 0)
				(music number: 5 loop: 1 play:)
				(ShakeScreen 2 1)
				(= cycles 33)
			)
			(11
				(cls)
				(music number: 4 loop: 1 play:)
				(aLarry setMotion: MoveTo 73 73 self)
			)
			(12
				(aLarry posn: 73 89 view: 611 setLoop: 0 setStep: 2 1)
				(music number: 12 loop: 1 play:)
				(Animate (cast elements?) FALSE)
				(ShakeScreen 1 shakeSDown)
				(Print 610 12 #at -1 10 #dispose)
				(= cycles 11)
			)
			(13
				(music number: 6 loop: -1 play:)
				(cls)
				(Print 610 13
					#at 100 22 ;148 22
					#dispose
				)
				(aLarry setMotion: MoveTo 169 109 self)
			)
			(14
				(aMan1 dispose:)
				(aMan2 dispose:)
				(music number: 611 loop: -1 play:)
				(aLarry setLoop: 1 setCycle: Forward)
				(aTowers cycleSpeed: 1 setCycle: EndLoop self)
			)
			(15
				(aTowers setLoop: 4 cycleSpeed: 0 setCycle: Forward)
				(= cycles 33)
			)
			(16
				(cls)
				(aLarry setMotion: MoveTo 167 110 self)
			)
			(17
				(music number: 4 loop: 1 play:)
				(aLarry setPri: 10 setLoop: 2 cel: 0 setCycle: EndLoop self)
			)
			(18
				(aLarry posn: 165 180 setPri: 12 setLoop: 3 cel: 0)
				(= cycles 15)
			)
			(19
				(music number: 5 loop: 1 play: self)
			)
			(20
				(Print 610 14)
				(= seconds 3)
			)
			(21
				(Print 610 15)
				(= cycles 11)
			)
			(22
				(aLarry setCycle: EndLoop self)
			)
			(23
				(NormalActor aLarry 2 720)
				(aLarry illegalBits: 0)
				(= cycles 22)
			)
			(24
				(Print 610 16)
				(= seconds 2)
			)
			(25
				(ego setCycle: EndLoop self)
			)
			(26
				(NormalEgo 3 currentEgoView)
				(ego illegalBits: 0)
				(HandsOff)
				(= cycles 22)
			)
			(27
				(Print 610 17)
				(Print 610 18)
				(ego setMotion: MoveTo 308 183 self)
				(= cycles 11)
			)
			(28
				(aLarry setMotion: MoveTo 276 185 self)
			)
			(29
				(ego loop: 1)
			)
			(30
				(Bclr fSaveDisabled)
				(Bclr fCursorHidden)
				(Bclr fAutoSaveDisabled)
				(theGame setSpeed: saveSpeed)
				(NormalEgo 1)
				(ego setPri: 7)
				(aLarry
					setPri: 7
					illegalBits: cWHITE
					setMotion: Follow ego 28
				)
				(Print 610 19)
				(= seconds 2)
			)
			(31
				(Print 610 20)
				(Print 610 21)
				(music number: 699 loop: musicLoop play:)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(cond 
				((Said '/door,wall,awning,exit')
					(Print 610 1)
				)
				((Said '/gravestone')
					(Print 610 2)
				)
				((Said '[/backdrop,buffet,backstage,area]')
					(Print 610 3)
					(Print 610 4 #at -1 144)
				)
			)
		)
	)
)

(instance atpSign of PicView
	(properties
		y 46
		x 285
		view 610
		loop 2
	)
)

(instance atpStencil1 of PicView
	(properties
		y 143
		view 610
		loop 2
		cel 1
		priority 15
		signal ignrAct
	)
)

(instance atpStencil2 of PicView
	(properties
		y 142
		x 173
		view 610
		loop 2
		cel 1
		priority 15
		signal ignrAct
	)
)

(instance atpHouse1 of PicView
	(properties
		y 126
		x 91
		view 610
		priority 9
	)
)

(instance atpHouse2 of PicView
	(properties
		y 104
		x 5
		view 610
		cel 1
		priority 9
	)
)

(instance atpHouse3 of PicView
	(properties
		y 126
		x 41
		view 610
		cel 1
		priority 9
	)
)

(instance atpHouse4 of PicView
	(properties
		y 99
		x 57
		view 610
		cel 3
		priority 9
	)
)

(instance atpHouse5 of PicView
	(properties
		y 97
		x 95
		view 610
		cel 4
		priority 9
	)
)

(instance atpHouse6 of PicView
	(properties
		y 116
		x 128
		view 610
		cel 4
		priority 9
	)
)

(instance atpHouse7 of PicView
	(properties
		y 109
		x 123
		view 610
		cel 3
		priority 9
	)
)

(instance aHole of View
	(properties
		y 120
		x 156
		view 610
		loop 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 13 ignoreActors: stopUpd:)
	)
)

(instance aCamera of Actor
	(properties
		x 145
		view 616
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self ignoreHorizon: ignoreActors: setStep: 1 1)
	)
)

(instance aCar1 of Actor
	(properties
		y 129
		x 145
		view 614
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self
			ignoreActors:
			setPri: 9
			setStep: 1 1
			setLoop: 1
			setCycle: Walk
		)
	)
)

(instance aMan1 of Actor
	(properties
		y 143
		x 145
		view 613
		cycleSpeed 2
		illegalBits $0000
		moveSpeed 2
	)
	
	(method (init)
		(super init:)
		(self ignoreActors: setLoop: 1 setCycle: Walk)
	)
)

(instance aCar2 of Actor
	(properties
		y 127
		x 130
		view 614
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self
			ignoreActors:
			setPri: 9
			setStep: 1 1
			setLoop: 2
			setCycle: Walk
		)
	)
)

(instance aMan2 of Actor
	(properties
		y 141
		x 130
		view 613
		cycleSpeed 1
		illegalBits $0000
		moveSpeed 1
	)
	
	(method (init)
		(super init:)
		(self
			ignoreActors:
			setStep: 2 2
			setLoop: 3
			setCycle: Walk
			setScript: Man2Script
		)
	)
)

(instance Man2Script of Script
	(method (changeState newState)
		(ChangeScriptState self newState 1 2)
		(switch (= state newState)
			(0)
			(1
				(aCar2 setMotion: MoveTo 88 102)
				(aMan2 setMotion: MoveTo 88 116 self)
			)
			(2
				(aCar2 setMotion: MoveTo 130 127)
				(aMan2 setLoop: 2 setMotion: MoveTo 130 141)
			)
		)
	)
)

(instance aTowers of Prop
	(properties
		y 109
		x 170
		view 610
		loop 3
	)
	
	(method (init)
		(super init:)
		(self setPri: 10 stopUpd: ignoreActors:)
	)
)

(instance aLarry of Actor)
