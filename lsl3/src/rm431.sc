;;; Sierra Script 1.0 - (do not remove this comment)
(script# 431)
(include game.sh)
(use Main)
(use n021)
(use Intrface)
(use Jump)
(use Motion)
(use Game)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	rm431 0
)

(local
	[actors 6]
	[veggies 6]
	toX = [999 113 127 141 155 169 183 197 999]
	[msgBuf 40]
	[titleBuf 22]
)
(instance rm431 of Room
	(properties
		picture 430
		horizon 1
	)
	
	(method (init &tmp i)
		(Load SOUND 10)
		(Load SOUND 433)
		(Load SOUND 432)
		(Load SCRIPT JUMP)
		(Load PICTURE 99)
		(Load FONT 7)
		(super init:)
		(self setScript: RoomScript)
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
		(= [veggies 1] aActor1)
		(= [veggies 2] aActor2)
		(= [veggies 3] aActor3)
		(= [veggies 4] aActor4)
		(= [veggies 5] aActor5)
		(NormalEgo)
		(ego
			view: 431
			setLoop: 0
			setCycle: Walk
			setStep: 2 1
			setCycle: Walk
			setPri: 1
			posn: 16 75
			ignoreActors:
			init:
		)
		(HandsOff)
		(RoomScript changeState: 1)
	)
)

(instance RoomScript of Script
	(method (doit &tmp i)
		(super doit:)
		(if
			(and
				(== -1 (music prevSignal?))
				(== (music number?) 432)
			)
			(self changeState: 22)
		)
		(if (== currentStatus 431)
			(cond 
				((& (ego onControl: 1) cBLUE) (= i 1))
				((& (ego onControl: 1) cGREEN) (= i 2))
				((& (ego onControl: 1) cCYAN) (= i 3))
				((& (ego onControl: 1) cRED) (= i 4))
				((& (ego onControl: 1) cMAGENTA) (= i 5))
				((& (ego onControl: 1) cBROWN) (= i 6))
				(else (= i 0))
			)
			(ego setLoop: i)
			(aFloor
				posn: [toX i] 97
				setCel:
					(aSpotlight
						posn: [toX i] 52
						setCel: (if (< machineSpeed 39) (return))
					)
			)
		)
	)
	
	(method (changeState newState &tmp i)
		(ChangeScriptState self newState 1 12)
		(switch (= state newState)
			(0)
			(1
				(Print 431 4 #at 10 5 #width 290)
				(aCurtain setCycle: EndLoop self)
				(= seconds 3)
			)
			(2)
			(3
				(aCurtain stopUpd:)
				(Print (Format @msgBuf 431 5 expletive) #font 7)
				(Print 431 6)
				(Print 431 7)
				(= seconds 3)
			)
			(4
				(Print 431 8 #at 10 5 #width 290)
				(Print 431 9)
				(Print 431 10 #at -1 144)
				(= seconds 3)
			)
			(5
				(Print 431 11 #at -1 10)
				(= seconds 3)
			)
			(6
				(Print 431 12 #at -1 10)
				(Print 431 13)
				(ego setPri: -1 setMotion: MoveTo 36 95 self)
			)
			(7
				(Print 431 14)
				(= currentStatus 432)
				(User canInput: 1)
				(music stop:)
				(ego cel: 0)
				(aCurtain setCycle: BegLoop self)
			)
			(8
				(aCurtain stopUpd:)
				(= seconds 3)
			)
			(9
				(Print 431 15 #at 10 5 #width 290)
				(= seconds 10)
			)
			(10
				(music number: 433 loop: -1 play:)
				(Print 431 16 #at -1 10)
				(Print 431 17 #at -1 144)
				(Print 431 18)
				(= seconds 3)
			)
			(11
				(Print 431 19 #at -1 10)
				(Print 431 20 #at -1 144)
				(= cycles (Random 2 8))
			)
			(12
				(HandsOff)
				(for ((= i 1)) (<= i 5) ((++ i))
					([veggies i]
						view: 430
						setCycle: Forward
						setStep: 6 6
						setScript: (VeggieScript new:)
					)
				)
				(= seconds 8)
			)
			(13
				(Print 431 21 #dispose #at 10 5 #width 290)
				(= seconds 3)
			)
			(14
				(cls)
				(for ((= i 1)) (<= i 5) ((++ i))
					(([veggies i] script?) changeState: 2)
				)
				(theGame setScript: (ScriptID DYING))
				((ScriptID DYING)
					caller: 437
					register: (Format @msgBuf 431 22)
					next: (Format @titleBuf 431 23)
				)
			)
			(15
				(= currentStatus 431)
				(HandsOff)
				(Ok)
				(theGame changeScore: 43)
				(Print 431 24 #at 10 5 #width 290)
				(aSpotlight setLoop: 5 setPri: 14 ignoreActors: init:)
				(aFloor setLoop: 6 setPri: 7 ignoreActors: init:)
				(ego setMotion: MoveTo [toX 6] 95 self)
				(= cycles 4)
			)
			(16
				(music number: 432 loop: 1 play: self)
			)
			(17
				(ego setMotion: MoveTo [toX 1] 95 self)
			)
			(18
				(Print 431 25 #at 10 5 #width 290 #dispose)
				([actors 1] setScript: (MoneyScript new:))
				(ego setMotion: MoveTo [toX 3] 95 self)
			)
			(19
				([actors 2] setScript: (MoneyScript new:))
				([actors 3] setScript: (MoneyScript new:))
				(ego setStep: 1 1 setMotion: MoveTo [toX 6] 95 self)
			)
			(20
				(cls)
				(Print 431 26 #at 10 5 #width 290 #time 6 #dispose)
				([actors 4] setScript: (MoneyScript new:))
				([actors 5] setScript: (MoneyScript new:))
				(= register 0)
				(self cue:)
			)
			(21
				(ego
					setMotion: MoveTo (Random [toX 1] [toX 6]) 95 self
				)
				;EO: Tweaked code based on decompiled NRS script
				(/ machineSpeed 10)
				(if (>= 2 (++ register))
					(-- state)
				)
				;(if (>= (/ machineSpeed 10) (++ register)) (-- state))
			)
			(22
				(music loop: 1 play:)
				(curRoom drawPic: 99 IRISIN)
				(cast eachElementDo: #hide)
				(for ((= i 1)) (<= i 5) ((++ i))
					(([actors i] script?) changeState: 2)
				)
				(= cycles 20)
			)
			(23
				(music fade:)
				(Print 431 27)
				(Print 431 28)
				(Print 431 29)
				(Print 431 30
					#at 10 144
					#width 290
				)
				(Load VIEW 708)
				(= currentEgoView 708)
				(= currentStatus egoSHOWGIRL)
				(= showroomState SRdone)
				(= dollars 500)
				(Format ((Inventory at: iMoney) name?) 431 31)
				((Inventory at: iMoney) view: 24)
				(ego get: iMoney)
				(theGame setSpeed: saveSpeed)
				(curRoom newRoom: 420)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			((or (Said '/naked,dance') (Said 'naked,dance'))
				(self changeState: 15)
			)
			((Said 'address')
				(Print 431 0)
				(Print 431 1)
			)
			(
				(and
					(Said 'look>')
					(Said '[/area,backstage,couple,man,babe]')
				)
				(Print 431 2)
				(Print 431 3 #at -1 144)
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

(instance VeggieScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles (Random 1 9))
			)
			(1
				(client
					setLoop: (Random 8 9)
					posn: (Random 8 290) (Random 144 157)
					setMotion: JumpTo (ego x?) (- (ego y?) (Random 0 33)) self
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
					setMotion: JumpTo (ego x?) (- (ego y?) (Random 0 33)) self
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
