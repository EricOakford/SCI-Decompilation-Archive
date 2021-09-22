;;; Sierra Script 1.0 - (do not remove this comment)
(script# 640)
(include game.sh)
(use Main)
(use n021)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm640 0
)

(local
	sceneTakes
)
(instance rm640 of Room
	(properties
		picture 640
	)
	
	(method (init)
		(Load SOUND 640)
		(Load SOUND 120)
		(Load PICTURE 99)
		(super init:)
		(addToPics
			add: atpChest
			add: atpWheel
			add: atpBarrel1
			add: atpBarrel2
			add: atpBarrel3
			add: atpBertaButt
			doit:
		)
		(aCamera init:)
		(aRosella init:)
		(aRoberta init:)
		(NormalActor aLarry 0 720)
		(aLarry posn: 8 174 init: stopUpd:)
		(self setScript: RoomScript)
		(= saveSpeed (theGame setSpeed: 6))
		(NormalEgo 0)
		(ego posn: 12 183 init: stopUpd:)
		(HandsOff)
	)
)

(instance RoomScript of Script
	(method (changeState newState)
		(ChangeScriptState self newState 1 2)
		(switch (= state newState)
			(0
				(= seconds 3)
			)
			(1
				(Print 640 0)
				(= seconds 2)
			)
			(2
				(aCamera setStep: 1 1 setMotion: MoveTo 96 189 self)
			)
			(3
				(Print 640 1)
				(aCamera setMotion: MoveTo 47 238 self)
			)
			(4
				(aCamera stopUpd:)
				(Print 640 2)
				(= seconds 2)
			)
			(5
				(Print 640 3)
				(music fade:)
				(= seconds 2)
			)
			(6
				(Print 640 4)
				(aRoberta setCycle: EndLoop)
				(music number: 640 loop: 1 play:)
				(= seconds 3)
			)
			(7
				(aRosella
					setStep: 2 2
					moveSpeed: 1
					cycleSpeed: 1
					setMotion: MoveTo 177 86 self
				)
			)
			(8
				(aRosella
					moveSpeed: 0
					cycleSpeed: 0
					setLoop: 5
					setStep: 2 4
					setMotion: MoveTo 177 123 self
				)
			)
			(9
				(aRoberta setCycle: BegLoop)
				(= cycles 15)
			)
			(10
				(aRosella setLoop: 0)
				(music fade:)
				(switch (++ sceneTakes)
					(1
						(Print 640 5)
					)
					(2
						(Print 640 6)
					)
					(3
						(aRosella stopUpd:)
						(Print 640 7)
						(++ state)
					)
				)
				(= cycles 22)
			)
			(11
				(aRosella
					setLoop: 3
					setStep: 2 1
					moveSpeed: 1
					cycleSpeed: 1
					setMotion: MoveTo 224 115 self
				)
				(if (< sceneTakes 3)
					(= state 5)
				)
			)
			(12
				(aRosella cycleSpeed: 1 setLoop: 2 setCycle: Forward)
				(= seconds 2)
			)
			(13
				(Print 640 8)
				(= seconds 2)
			)
			(14
				(Printf 640 9 (if (>= filthLevel 3) {on_} else {}))
				(aRosella setLoop: 1)
				(= seconds 2)
			)
			(15
				(Print 640 10)
				(Print 640 11)
				(aRoberta setCycle: EndLoop self)
			)
			(16
				(Print 640 12)
				(aRosella setLoop: 2)
				(= seconds 3)
			)
			(17
				(aRoberta setLoop: 2 setCycle: Forward)
				(= seconds 3)
			)
			(18
				(Print 640 13)
				(Print 640 14)
				(aRoberta setCel: 0)
				(aRosella loop: 0 stopUpd:)
				(aCamera setMotion: MoveTo 41 244 self)
			)
			(19
				(ego setMotion: MoveTo 227 187 self)
				(= cycles 9)
			)
			(20
				(aLarry setMotion: MoveTo 239 176 self)
				(= cycles 22)
			)
			(21
				(Print 640 15)
				(= cycles 11)
			)
			(22
				(Print 640 16)
				(music number: 699 loop: -1 play:)
				(aRoberta setCycle: Forward)
			)
			(23
				(Print 640 17)
				(aRoberta setCel: 0)
				(= seconds 3)
			)
			(24
				(aLarry loop: 2 forceUpd: stopUpd:)
				(Print 640 18)
				(aRoberta setCycle: Forward)
				(= seconds 2)
			)
			(25
				(Print 640 19)
				(aRoberta setCel: 0)
				(= seconds 2)
			)
			(26
				(Print 640 20)
				(Print 640 21 #at 10 -1 #width 290)
				(Print 640 22)
				(= seconds 2)
			)
			(27
				(Print 640 23)
				(Print 640 24)
				(= seconds 3)
			)
			(28
				(Print 640 25)
				(= seconds 3)
			)
			(29
				(Print 640 26)
				(curRoom drawPic: 99 6)
				(cast eachElementDo: #hide)
				(= cycles 22)
			)
			(30
				(Print 640 27)
				(music fade:)
				(= seconds 6)
			)
			(31
				(music number: 120 loop: -1 play:)
				(= seconds 3)
			)
			(32
				(Print 640 28 #time 4 #dispose)
				(= seconds 5)
			)
			(33
				(Print 640 29)
				(= seconds 3)
			)
			(34 (curRoom newRoom: 650))
		)
	)
)

(instance aLarry of Actor)

(instance atpChest of PicView
	(properties
		y 142
		x 89
		view 640
		signal ignrAct
	)
)

(instance atpWheel of PicView
	(properties
		y 141
		x 159
		view 640
		cel 1
		priority 10
		signal ignrAct
	)
)

(instance atpBarrel1 of PicView
	(properties
		y 137
		x 250
		view 640
		cel 2
		priority 9
		signal ignrAct
	)
)

(instance atpBarrel2 of PicView
	(properties
		y 134
		x 47
		view 640
		cel 3
		priority 9
		signal ignrAct
	)
)

(instance atpBarrel3 of PicView
	(properties
		y 135
		x 209
		view 640
		cel 4
		priority 9
		signal ignrAct
	)
)

(instance atpBertaButt of PicView
	(properties
		y 166
		x 257
		view 643
		loop 3
		priority 15
	)
)

(instance aRoberta of Prop
	(properties
		y 166
		x 257
		view 643
		loop 1
	)
	
	(method (init)
		(super init:)
		(self ignoreActors: setPri: 15)
	)
)

(instance aCamera of Actor
	(properties
		y 189
		x 120
		view 640
		loop 1
		illegalBits $0000
	)
)

(instance aRosella of Actor
	(properties
		y 126
		x 177
		view 644
	)
	
	(method (init)
		(super init:)
		(self
			ignoreActors:
			illegalBits: 0
			setStep: 2 1
			setCycle: Walk
			setLoop: 4
		)
	)
)
