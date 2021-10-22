;;; Sierra Script 1.0 - (do not remove this comment)
(script# 430)
(include game.sh)
(use Main)
(use n021)
(use Intrface)
(use Jump)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm430 0
)

(local
	actor4
	actor3
	actor5
	[money 6]
	[actors 6]
	actorXY = [0 0 54 74 41 78 26 83 11 88 -4 93]
)
(instance rm430 of Room
	(properties
		picture 430
		horizon 1
	)
	
	(method (init &tmp i)
		(= showroomState SRshowDone)
		(= oldStatus currentStatus)
		(= currentStatus curRoomNum)
		(HandsOff)
		(Load VIEW 432)
		(Load SOUND 431)
		(Load SOUND 13)
		(Load SOUND 10)
		(Load SCRIPT JUMP)
		(Load PICTURE 431)
		(music number: 430 loop: -1 play:)
		(super init:)
		(aCurtain ignoreActors: init:)
		(aActor1 ignoreActors: init:)
		(aActor2 ignoreActors: init:)
		(aActor3 ignoreActors: init:)
		(aActor4 ignoreActors: init:)
		(aActor5 ignoreActors: init:)
		(= saveSpeed (theGame setSpeed: 6))
		(= [actors 1] aActor1)
		(= [actors 2] aActor2)
		(= [actors 3] aActor3)
		(= [actors 4] aActor4)
		(= [actors 5] aActor5)
		(aMoney1 ignoreActors: init:)
		(aMoney2 ignoreActors: init:)
		(aMoney3 ignoreActors: init:)
		(aMoney4 ignoreActors: init:)
		(aMoney5 ignoreActors: init:)
		(= [money 1] aMoney1)
		(= [money 2] aMoney2)
		(= [money 3] aMoney3)
		(= [money 4] aMoney4)
		(= [money 5] aMoney5)
		(= actor3 aActor3)
		(= actor4 aActor4)
		(= actor5 aActor5)
		(ego posn: 999 999 init: hide:)
		(for ((= i 1)) (<= i 5) ((++ i))
			([actors i]
				view: 433
				posn: [actorXY (* i 2)] [actorXY (+ 1 (* i 2))]
				setPri: 3
				setStep: 1 1
			)
		)
		(self setScript: RoomScript)
	)
)

(instance RoomScript of Script
	(method (doit)
		(super doit:)
	)
	
	(method (changeState newState &tmp i)
		(ChangeScriptState self newState 1 12)
		(switch (= state newState)
			(0
				([actors 1] setCycle: EndLoop self)
			)
			(1
				([actors 1] cel: 0 setCycle: EndLoop)
				([actors 2] setCycle: EndLoop self)
			)
			(2
				([actors 1] cel: 0 setCycle: EndLoop)
				([actors 2] cel: 0 setCycle: EndLoop)
				([actors 3] setCycle: EndLoop self)
			)
			(3
				([actors 1] cel: 0)
				([actors 2] cel: 0 setCycle: EndLoop)
				([actors 3] cel: 0 setCycle: EndLoop)
				([actors 4] setCycle: EndLoop self)
			)
			(4
				(Print 430 0 #at 10 144 #width 290 #dispose)
				([actors 2] cel: 0)
				([actors 3] cel: 0 setCycle: EndLoop)
				([actors 4] cel: 0 setCycle: EndLoop)
				([actors 5] setCycle: EndLoop self)
			)
			(5
				([actors 3] cel: 0)
				([actors 4] cel: 0 setCycle: EndLoop)
				([actors 5] cel: 0 setCycle: EndLoop self)
			)
			(6
				([actors 4] cel: 0)
				([actors 5] setCycle: EndLoop self)
			)
			(7
				([actors 5] cel: 0 setCycle: EndLoop self)
			)
			(8
				([actors 4] cel: 0 setCycle: EndLoop self)
			)
			(9
				([actors 3] cel: 0 setCycle: EndLoop self)
			)
			(10
				([actors 2] cel: 0 setCycle: EndLoop self)
			)
			(11
				([actors 1] cel: 0 setCycle: EndLoop self)
			)
			(12
				([actors 2] setCycle: EndLoop self)
			)
			(13
				([actors 3] setCycle: EndLoop self)
			)
			(14
				([actors 4] setCycle: EndLoop self)
			)
			(15
				([actors 5] setCycle: EndLoop self)
			)
			(16
				([actors 4] setCycle: EndLoop self)
			)
			(17
				([actors 3] setCycle: EndLoop self)
			)
			(18
				([actors 2] setCycle: EndLoop self)
			)
			(19
				(cls)
				([actors 1] setCycle: EndLoop self)
			)
			(20
				([actors 1] setCycle: EndLoop self)
				([actors 2] setCycle: Forward)
			)
			(21
				([actors 1] setCycle: EndLoop self)
				([actors 3] setCycle: Forward)
			)
			(22
				([actors 1] setCycle: EndLoop self)
				([actors 4] setCycle: Forward)
			)
			(23
				([actors 1] setCycle: Forward)
				([actors 5] setCycle: Forward)
				(= cycles 55)
			)
			(24
				(for ((= i 1)) (<= i 5) ((++ i))
					([actors i] setMotion: MoveTo 119 52)
				)
				(= cycles 66)
			)
			(25
				(music fade:)
				(= cycles 66)
			)
			(26
				(music number: 10 loop: -1 play:)
				(Print 430 1 #at 10 5 #width 290)
				([actors 1] dispose:)
				([actors 2] dispose:)
				(actor3
					view: 430
					setLoop: 3
					posn: 167 97
					setPri: 2
					hide:
				)
				(actor4
					view: 430
					setLoop: 4
					posn: 148 163
					setPri: 3
					hide:
				)
				(actor5
					view: 432
					setLoop: 0
					setCel: 0
					posn: 149 140
					setPri: 4
					hide:
				)
				(= cycles 22)
			)
			(27
				(curRoom drawPic: 431 8)
				(aSpotlight
					loop: 1
					setCel: 1
					posn: 160 45
					setPri: 1
					ignoreActors:
					init:
					stopUpd:
				)
				(aFloor
					loop: 2
					setCel: 1
					posn: 152 102
					setPri: 6
					ignoreActors:
					init:
					stopUpd:
				)
				(actor4 show:)
				(actor5 show:)
				(= cycles 11)
			)
			(28
				(actor3 show: setMotion: MoveTo 149 97 self)
			)
			(29
				(actor3 stopUpd:)
				(actor5 setMotion: MoveTo 149 86)
				(actor4 setMotion: MoveTo 148 109 self)
			)
			(30
				(actor4 stopUpd:)
				(music number: 431 loop: -1 play:)
				(actor5 setLoop: 3 setCycle: Forward)
				(= cycles 33)
			)
			(31
				(actor5 setLoop: 4)
				(= cycles 33)
			)
			(32
				(actor5 cel: 0 setCycle: EndLoop self)
			)
			(33
				(actor5 setLoop: 1 setCycle: Forward)
				(Print 430 2 #at 10 144 #width 290 #dispose)
				(for ((= i 1)) (<= i 5) ((++ i))
					([money i] setScript: (MoneyScript new:))
				)
				(self cue:)
			)
			(34
				(= cycles (* 2 (NumCels actor5)))
				(if (> speed 1)
					(theGame setSpeed: (-- speed))
					(-- state)
				)
			)
			(35
				(cls)
				(actor5 setCycle: Forward)
				(= cycles (+ 7 (* 127 2)))
				;EO: replaced "machineSpeed" with 127 to fix an original speed bug
			)
			(36
				(if (< 127 39)
					(self cue:)
				else
					(aSpotlight setCycle: Forward)
					(aFloor setCycle: Forward)
					(= cycles (+ 7 (* 127 2)))
				)
			)
			(37
				(aSpotlight setCel: 1 stopUpd:)
				(aFloor setCel: 1 stopUpd:)
				(self cue:)
			)
			(38
				(= cycles
					(* (+ 1 (> 127 39)) (NumCels actor5))
				)
				(if (< speed 6)
					(theGame setSpeed: (++ speed))
					(-- state)
				)
			)
			(39
				(actor5 setLoop: 2 setCycle: EndLoop self)
			)
			(40
				(actor5 setCel: 255)
				(music fade:)
				(= seconds 4)
			)
			(41
				(Print 430 3 #at 10 5 #width 290)
				(music number: 13 loop: -1 play:)
				(actor4 setMotion: MoveTo 148 163)
				(actor5 setMotion: MoveTo 149 140 self)
				(for ((= i 1)) (<= i 5) ((++ i))
					(([money i] script?) changeState: 2)
				)
			)
			(42
				(music fade:)
				(Print 430 4 #at 10 5 #width 290)
				(actor3 setMotion: MoveTo 167 97 self)
			)
			(43
				(curRoom drawPic: 430 DISSOLVE)
				(actor3 dispose:)
				(aSpotlight dispose:)
				(aFloor dispose:)
				(Animate (cast elements?) FALSE)
				(Print 430 5 #at 10 5 #width 290)
				(= seconds 3)
			)
			(44
				(Print 430 6)
				(Print 430 7 #at -1 144)
				(= seconds 2)
			)
			(45
				(theGame setSpeed: saveSpeed)
				(= currentStatus oldStatus)
				(curRoom newRoom: 420)
			)
		)
	)
)

(instance aActor1 of Actor)

(instance aActor2 of Actor)

(instance aActor3 of Actor)

(instance aActor4 of Actor)

(instance aActor5 of Actor)

(instance aSpotlight of Prop
	(properties
		y 999
		x 999
		view 430
	)
)

(instance aFloor of Prop
	(properties
		y 999
		x 999
		view 430
	)
)

(instance aCurtain of Prop
	(properties
		y 63
		x 15
		view 430
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 2 ignoreActors: stopUpd:)
	)
)

(instance aMoney1 of Actor)

(instance aMoney2 of Actor)

(instance aMoney3 of Actor)

(instance aMoney4 of Actor)

(instance aMoney5 of Actor)

(instance MoneyScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client
					view: 430
					setLoop: 7
					setCycle: Forward
					setStep: 7 7
				)
				(= cycles (Random 1 9))
			)
			(1
				(client
					posn: (Random 8 290) (Random 144 157)
					setMotion: JumpTo
						(actor5 x?)
						(- (actor5 y?) (Random 0 33))
						self
				)
				(-- state)
			)
			(2
				(client dispose:)
				(self dispose:)
			)
		)
	)
)
