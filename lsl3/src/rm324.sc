;;; Sierra Script 1.0 - (do not remove this comment)
(script# 324)
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
	rm324 0
)

(local
	local0
	interruptions
)
(instance rm324 of Room
	(properties
		picture 323
	)
	
	(method (init)
		(Load VIEW 327)
		(Load VIEW 328)
		(Load SOUND 20)
		(Load SOUND 8)
		(Load SOUND 9)
		(Load SOUND 11)
		(Load SCRIPT JUMP)
		(Load PICTURE 99)
		(super init:)
		(addToPics add: atpProps doit:)
		(aSuzi init:)
		(aChair init:)
		(aDoorNorth init:)
		(aDoorSouth init:)
		(aPhone init:)
		(aTrapdoor init:)
		(self setScript: RoomScript)
		(= currentStatus egoSHOWGIRL)
		(= currentEgoView 708)
		(NormalEgo loopE)
		(ego posn: 41 156 illegalBits: 0 init:)
		(HandsOff)
	)
)

(instance RoomScript of Script
	(method (changeState newState)
		(ChangeScriptState self newState 1 2)
		(switch (= state newState)
			(0
				(= cycles 22)
			)
			(1
				(Print 324 0)
				(= seconds 3)
			)
			(2
				(Print 324 1)
				(Print 324 2 #at -1 144)
				(= seconds 3)
			)
			(3
				(Print 324 3)
				(= seconds 3)
			)
			(4
				(Print 324 4)
				(ego
					ignoreActors:
					illegalBits: 0
					moveSpeed: 1
					cycleSpeed: 1
					setMotion: MoveTo (+ 40 (ego x?)) (ego y?) self
				)
			)
			(5
				(ego setMotion: MoveTo 115 134 self)
			)
			(6
				(Print 324 5)
				(ego loop: 2)
				(aSuzi
					view: 327
					setPri: -1
					setLoop: -1
					setCycle: Walk
					setMotion: MoveTo 232 125 self
				)
				(aChair view: 329 posn: 247 133 setLoop: 3 stopUpd:)
			)
			(7
				(aSuzi setMotion: MoveTo 190 125 self)
			)
			(8
				(Print 324 6)
				(aSuzi setMotion: MoveTo 190 141 self)
			)
			(9
				(aSuzi setMotion: MoveTo 47 156 self)
				(= cycles 27)
			)
			(10
				(Print 324 7)
			)
			(11
				(aDoorNorth setCycle: BegLoop)
				(aDoorSouth setCycle: BegLoop self)
				(music fade:)
			)
			(12
				(soundFX number: 11 loop: 1 play:)
				(aDoorNorth stopUpd:)
				(aDoorSouth stopUpd:)
				(Print 324 8)
				(aSuzi setMotion: MoveTo (+ 15 (ego x?)) 156 self)
			)
			(13
				(Print 324 9)
				(aSuzi setMotion: MoveTo 148 133 self)
			)
			(14
				(Print 324 10)
				(ego
					cycleSpeed: 1
					setPri: 8
					view: 322
					cel: 0
					setCycle: EndLoop self
				)
				(aSuzi loop: 1)
				(addToPics add: atpHat doit:)
			)
			(15
				(Print 324 11)
				(= seconds 3)
			)
			(16
				(aSuzi
					cycleSpeed: 2
					view: 328
					setLoop: 0
					cel: 0
					setCycle: EndLoop self
				)
				(music number: 8 loop: -1 play:)
			)
			(17
				(= seconds 3)
			)
			(18
				(Print 324 12)
				(= seconds 3)
			)
			(19
				(Print 324 13)
				(= seconds 3)
			)
			(20
				(Print 324 14)
				(Bset fScrewedSuzi)
				(theGame changeScore: 100)
				(aSuzi hide:)
				(ego
					view: 328
					setLoop: 2
					setCel: 0
					cycleSpeed: 3
					posn: 123 118
				)
				(addToPics add: atpSuziClothes doit:)
				(= cycles 22)
			)
			(21
				(ego setCycle: EndLoop self)
			)
			(22
				(= seconds 3)
			)
			(23
				(ego setCycle: BegLoop self)
			)
			(24
				(ego
					viewer: humpCycler
					posn: 123 118
					setLoop: 3
					setCycle: Forward
				)
				(= cycles 0)
				(= seconds 3)
			)
			(25
				(if (> (++ interruptions) 1)
					(self cue:)
				else
					(aTrapdoor setMotion: MoveTo 152 6 self)
				)
			)
			(26
				(music stop:)
				(soundFX number: 20 loop: -1 play:)
				(aPhone
					view: 323
					setPri: 13
					setStep: 1 1
					setLoop: 3
					setCycle: Forward
					setMotion: MoveTo 146 6 self
				)
			)
			(27
				(ego viewer: 0 setCel: 0)
				(if (== interruptions 1)
					(Print 324 15)
				)
				(= seconds 3)
			)
			(28
				(switch interruptions
					(1
						(Print 324 16)
					)
					(2
						(Print 324 17)
						(Print 324 18 #at -1 144)
					)
					(3
						(Print 324 19)
					)
					(4
						(Printf 324 20 expletive)
						(Print 324 21 #at -1 144)
					)
				)
				(= cycles 12)
			)
			(29
				(aSuzi
					cycleSpeed: 0
					setLoop: 4
					cel: 0
					posn: 133 117
					setPri: 9
					setCycle: EndLoop self
					show:
				)
			)
			(30
				(aSuzi setLoop: 5 cel: 0 setCycle: EndLoop self)
				(aPhone setLoop: 6 setCel: 1 stopUpd:)
				(soundFX stop:)
				(= local0 0)
			)
			(31
				(aSuzi setLoop: 6 setCycle: Forward)
				(if (== interruptions 4)
					(self changeState: 51)
				else
					(= cycles (Random 11 33))
				)
			)
			(32
				(aSuzi setCel: 1)
				(= cycles (Random 5 11))
				(if (> 3 (++ local0))
					(= state 30)
				)
			)
			(33
				(aSuzi setLoop: 5 setCel: 255 setCycle: BegLoop self)
			)
			(34
				(Print 324 22)
				(aPhone setCel: 0 stopUpd:)
				(if (== interruptions 1)
					(aPhone setMotion: MoveTo (aPhone x?) -111)
				)
				(aSuzi setLoop: 4 setCel: 255 setCycle: BegLoop self)
				(music number: 8 loop: -1 play:)
			)
			(35
				(aSuzi hide:)
				(ego viewer: humpCycler setCycle: Forward)
				(= seconds 2)
			)
			(36
				(switch interruptions
					(1
						(Print 324 23)
						(self changeState: 24)
					)
					(2
						(Print 324 24)
						(Print 324 25 #at -1 144)
					)
					(3
						(Print 324 26)
					)
				)
				(= seconds 2)
			)
			(37
				(ego viewer: 0 setCel: 0)
				(aSuzi setLoop: 4 cel: 0 setCycle: EndLoop self show:)
			)
			(38
				(aSuzi setLoop: 7 cel: 0 setCycle: EndLoop self)
				(aPhone setCel: 2 stopUpd:)
			)
			(39
				(aSuzi setLoop: 8 setCycle: Forward)
				(= seconds 4)
			)
			(40
				(aSuzi setLoop: 7 setCel: 255 setCycle: BegLoop self)
			)
			(41
				(aSuzi setLoop: 4 setCel: 255 setCycle: BegLoop self)
			)
			(42
				(Print 324 27)
				(aPhone
					setLoop: 6
					setCel: 0
					setMotion: MoveTo (aPhone x?) -111
				)
				(if (< interruptions 3)
					(self changeState: 24)
				else
					(= seconds 2)
				)
			)
			(43
				(Print 324 28)
				(= seconds 3)
			)
			(44
				(aSuzi setLoop: 9 cel: 0 cycleSpeed: 1 setCycle: EndLoop self)
			)
			(45
				(ego viewer: humpCycler setCycle: Forward)
				(aSuzi
					view: 323
					setLoop: 5
					setCel: 0
					posn: 141 102
					setPri: 15
					setStep: 4 4
					setMotion: MoveTo 215 74 self
				)
			)
			(46
				(aSuzi setCel: 1 setMotion: JumpTo 289 121 self)
			)
			(47
				(aSuzi
					setCel: 2
					setStep: 5 5
					setMotion: MoveTo 221 189 self
				)
			)
			(48
				(aSuzi setMotion: MoveTo 177 233 self)
			)
			(49
				(= seconds 2)
			)
			(50
				(aSuzi
					view: 328
					setLoop: 4
					cel: 0
					posn: 133 117
					setPri: 9
					hide:
				)
				(= seconds 2)
				(= state 23)
			)
			(51
				(Print 324 29)
				(Print 324 30)
				(music number: 9 loop: 1 play:)
				(= lawyerState LSWaiting4Divorce)
				(= cycles 22)
			)
			(52
				(curRoom drawPic: 99 IRISIN)
				(cast eachElementDo: #hide)
				(= cycles 22)
			)
			(53
				(Print 324 31)
				(Print 324 32)
				(curRoom newRoom: 320)
			)
		)
	)
)

(instance atpProps of PicView
	(properties
		y 132
		x 240
		view 329
		loop 4
		priority 10
		signal ignrAct
	)
)

(instance atpHat of PicView
	(properties
		y 138
		x 115
		view 328
		loop 1
		priority 14
		signal ignrAct
	)
)

(instance atpSuziClothes of PicView
	(properties
		y 133
		x 148
		view 323
		loop 7
		priority 14
		signal ignrAct
	)
)

(instance aChair of Prop
	(properties
		y 133
		x 247
		view 329
		loop 2
		signal ignrAct
	)
	
	(method (init)
		(super init:)
		(self setPri: 4 stopUpd:)
	)
)

(instance aSuzi of Actor
	(properties
		y 120
		x 242
		view 324
		loop 1
	)
	
	(method (init)
		(super init:)
		(self
			ignoreHorizon:
			ignoreActors:
			illegalBits: 0
			setPri: 13
			stopUpd:
		)
	)
)

(instance aPhone of Actor
	(properties
		y -111
		x 146
		view 323
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self
			ignoreHorizon:
			ignoreActors:
			setLoop: 3
			setPri: 13
			setStep: 1 1
			setCycle: Forward
		)
	)
)

(instance aTrapdoor of Actor
	(properties
		y -4
		x 152
		view 323
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self
			ignoreHorizon:
			ignoreActors:
			setLoop: 4
			setPri: 5
			setStep: 1 1
		)
	)
)

(instance aDoorSouth of Prop
	(properties
		y 103
		x 20
		view 323
	)
	
	(method (init)
		(super init:)
		(self setCel: 255 setPri: 13 ignoreActors: stopUpd:)
	)
)

(instance aDoorNorth of Prop
	(properties
		y 95
		x 41
		view 323
		loop 1
	)
	
	(method (init)
		(super init:)
		(self setCel: 255 setPri: 11 ignoreActors: stopUpd:)
	)
)

(instance humpCycler of Code
	(method (doit)
		(if (not (Random 0 9))
			(ego cycleSpeed: (Random 0 5))
		)
	)
)
