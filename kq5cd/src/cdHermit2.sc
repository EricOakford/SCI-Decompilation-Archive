;;; Sierra Script 1.0 - (do not remove this comment)
(script# 661)
(include game.sh)
(use Main)
(use KQ5Room)
(use Sync)
(use LoadMany)
(use Motion)
(use Actor)
(use System)

(public
	cdHermit2 0
)

(local
	saveSpeed
)
(instance cdHermit2 of KQ5Room
	(properties
		picture 87
		style FADEOUT
	)
	
	(method (init)
		(LoadMany VIEW
			832 626 1042 828 1041 826
			0 824 624 625 618 827
		)
		(Load SCRIPT 941)
		(Load SCRIPT 929)
		(Load SOUND 820)
		(LoadMany 142
			1136 1137 1138 1139 1141
			1142 1143 1144 1145 1146
		)
		(if (!= (theMusic number?) 820)
			(theMusic number: 820 loop: -1 vol: 127 playBed:)
		)
		(if (== prevRoomNum 662)
			(hermit view: 626 posn: 136 118 moveSpeed: 1 init:)
			(ego init: view: 0 normal: 1 posn: 158 121 setLoop: 5)
			(= cedricX 201)
			(= cedricY 110)
			(= global325 3070)
			(curRoom setRegions: 202)
			(self setScript: cartoon3)
		else
			(hermit posn: 146 118 moveSpeed: 1 setLoop: 0 init:)
			(ego
				init:
				posn: 185 132
				setLoop: 0
				signal: 16384
				illegalBits: 0
				moveSpeed: 2
				view: 832
				normal: 0
			)
			((ego head?) hide:)
			(self setScript: cartoon)
		)
		(if (== gGNumber_2 46)
			(sailBoat
				init:
				y: (if (== curRoomNum 44) 153 else 173)
				stopUpd:
				ignoreActors:
			)
			(sail
				posn: (+ (sailBoat x?) 7) (sailBoat y?)
				setPri: (sailBoat priority?)
				setLoop: 2
				init:
			)
		)
		(chimney setCycle: Forward init:)
		(super init:)
		(= saveSpeed (theGame egoMoveSpeed?))
		(theGame egoMoveSpeed: 1)
	)
	
	(method (dispose)
		(theGame egoMoveSpeed: saveSpeed)
		(super dispose: &rest)
	)
)

(instance cartoon of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 15))
			(1
				(cls)
				(SpeakAudio 1135 self)
			)
			(2
				(ego setScript: putDownOwl)
				(hermit cycleSpeed: 2 setCycle: EndLoop self)
			)
			(3
				(hermit setLoop: 1 setCycle: EndLoop self)
			)
			(4
				(hermit setLoop: 2 setCycle: Forward)
				(= cycles 10)
			)
			(5 (hermit setCycle: EndLoop self))
			(6
				(hermit setLoop: 1 setCycle: BegLoop self)
			)
			(7
				(hermit setLoop: 0 setCycle: BegLoop self)
			)
			(8
				(cls)
				(hermit view: 626 setLoop: 0 cel: 2)
				(= cycles 4)
			)
			(9
				(hermit view: 1042 setLoop: 0 cel:)
				(= cycles 1)
			)
			(10
				(theMouth
					init:
					setPri: (+ (hermit priority?) 1)
					posn: (- (hermit x?) 2) (- (hermit y?) 39)
				)
				(= cycles 1)
			)
			(11
				(theMouth setCycle: MouthSync 1136)
				(SpeakAudio 1136 self)
			)
			(12
				(putDownOwl cue:)
				(theMouth setCycle: 0 hide:)
				(hermit
					view: 1042
					setCycle: Walk
					setLoop: 0
					signal: 18432
					illegalBits: 0
					cycleSpeed: 0
					moveSpeed: 0
					setMotion: MoveTo 141 130 self
				)
			)
			(13
				(hermit
					view: 626
					setLoop: 0
					setMotion: MoveTo 175 127 self
				)
			)
			(14
				(hermit
					view: 1041
					setLoop: 0
					cycleSpeed: 2
					setCycle: EndLoop self
				)
			)
			(15
				(hermit view: 828 setLoop: 2 setCycle: Forward)
				(= cycles 20)
			)
			(16 (hermit setCycle: EndLoop self))
			(17
				(hermit setLoop: 3 setCycle: EndLoop self)
			)
			(18 (cls) (= seconds 3))
			(19
				(self setScript: getBetterScript self)
			)
			(20
				(owlHead hide:)
				(theMouth changeMouth: 1 setCycle: MouthSync 1137 show:)
				(SpeakAudio 1137 self)
			)
			(21
				(theMouth setCycle: 0 hide:)
				(= cycles 1)
			)
			(22
				(cls)
				(shell init:)
				(hermit
					view: 624
					loop: 4
					cycleSpeed: 2
					cel: 0
					setCycle: EndLoop self
				)
			)
			(23
				(theMouth
					changeMouth: 0
					setPri: (+ (hermit priority?) 1)
					posn: (+ (hermit x?) 4) (- (hermit y?) 37)
					show:
					setCycle: MouthSync 1138
				)
				(SpeakAudio 1138 self)
			)
			(24
				(theMouth setCycle: 0 hide:)
				(= cycles 1)
			)
			(25
				(theMouth changeMouth: 1 show: setCycle: MouthSync 1139)
				(SpeakAudio 1139 self)
			)
			(26
				(theMouth setCycle: 0 hide:)
				(= cycles 1)
			)
			(27
				(cls)
				(theMouth
					changeMouth: 0
					setPri: (+ (hermit priority?) 1)
					posn: (+ (hermit x?) 4) (- (hermit y?) 37)
					setCycle: MouthSync 1141
					show:
				)
				(SpeakAudio 1141 self)
			)
			(28
				(cls)
				(theMouth setCycle: 0 hide:)
				(hermit
					view: 626
					setCycle: Walk
					setStep: 3 1
					setLoop: 1
					cycleSpeed: 0
					moveSpeed: 0
					setMotion: MoveTo 141 130 self
				)
			)
			(29
				(hermit setLoop: 2 setMotion: MoveTo 136 118 self)
			)
			(30
				(ego setLoop: 1)
				(shell init:)
				(hermit
					view: 624
					loop: 4
					setCel: 0
					cycleSpeed: 2
					setCycle: EndLoop self
				)
			)
			(31
				(ego moveSpeed: 0 setLoop: -1)
				(hermit setLoop: -1)
				(curRoom newRoom: 662)
			)
		)
	)
)

(instance cartoon3 of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(DrawPic 87 FADEOUT)
				(hermit view: 626 posn: 136 118 moveSpeed: 1 show:)
				(ego view: 0 normal: 1 posn: 158 121 setLoop: 5 show:)
				((ego head?) show:)
				(= cedricX 201)
				(= cedricY 110)
				(= global325 3070)
				(curRoom setRegions: 202)
				(ego
					view: 0
					ignoreActors:
					illegalBits: 0
					setLoop: 7
					cel: 1
				)
				(= cycles 1)
			)
			(1
				(hermit
					view: 1042
					illegalBits: 0
					setStep: 3 2
					setCycle: Walk
					setLoop: 0
					ignoreActors: 1
					setMotion: MoveTo 169 188 self
				)
			)
			(2
				(hermit dispose:)
				(theAudio number: 8123 loop: 1 play:)
				(ego
					setCycle: KQ5SyncWalk
					setLoop: -1
					setMotion: MoveTo 169 188 self
				)
			)
			(3
				(globalCedric view: 138 setLoop: 8 setCycle: EndLoop self)
			)
			(4
				(theMusic fade:)
				(curRoom newRoom: 660)
			)
		)
	)
)

(instance hermit of Actor
	(properties
		view 826
	)
)

(instance owl of Actor
	(properties
		view 832
	)
)

(instance putDownOwl of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (ego setCycle: EndLoop self))
			(1 (= seconds 2))
			(2
				(owl setLoop: 1 posn: 201 115 cycleSpeed: 2 cel: 0 init:)
				(ego view: 0 posn: 177 128 illegalBits: 0 normal: 1)
				((ego head?) show:)
				(client cue:)
			)
			(3
				(ego
					setStep: 3 2
					cycleSpeed: 0
					setCycle: KQ5SyncWalk
					setLoop: -1
					setMotion: MoveTo 158 121 self
				)
			)
			(4
				(ego setLoop: 2)
				((ego head?) show:)
				(= cycles 1)
			)
			(5
				(ego setLoop: -1)
				(= cycles 1)
			)
			(6 (self dispose:))
		)
	)
)

(instance getBetterScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(owl view: 824 cel: 0 setLoop: 0 setCycle: EndLoop self)
			)
			(1
				(owl setLoop: 1 cel: 0)
				(owlHead
					cycleSpeed: 2
					setLoop: 2
					cel: 0
					posn: (- (owl x?) 1) (- (owl y?) 9)
					setCycle: Forward
					init:
				)
				(= cycles 10)
			)
			(2 (owlHead setCycle: EndLoop self))
			(3
				(owl setLoop: 3 cel: 0 setCycle: EndLoop self)
			)
			(4
				(owl setLoop: 4 cel: 0 setCycle: EndLoop self)
			)
			(5
				(owlHead setPri: (+ (owl priority?) 1) setLoop: 5)
				(self dispose:)
			)
		)
	)
)

(instance owlHead of Prop
	(properties
		view 824
	)
)

(instance chimney of Prop
	(properties
		x 18
		y 70
		z 20
		view 625
		loop 2
		cycleSpeed 3
		detailLevel 3
	)
)

(instance shell of Prop
	(properties
		view 624
		loop 5
	)
	
	(method (doit)
		(super doit:)
		(if (== (hermit view?) 624)
			(self setPri: (+ (hermit priority?) 1))
			(switch (hermit cel?)
				(0
					(self posn: (+ (hermit x?) 8) (- (hermit y?) 20))
				)
				(1
					(self posn: (+ (hermit x?) 13) (- (hermit y?) 23))
				)
				(2
					(self posn: (+ (hermit x?) 15) (- (hermit y?) 35))
				)
			)
		else
			(self dispose:)
		)
	)
)

(instance sailBoat of Actor
	(properties
		x 280
		y 173
		view 618
		cel 1
		priority -1
		signal (| ignrAct stopUpdOn)
	)
)

(instance sail of Actor
	(properties
		z 15
		view 618
		loop 3
		signal (| ignrAct stopUpdOn)
	)
)

(instance theMouth of Prop
	(properties
		view 624
		loop 15
	)
	
	(method (changeMouth param1)
		(switch param1
			(0
				(theMouth
					view: 624
					loop: 15
					setPri: (+ (hermit priority?) 1)
				)
			)
			(1
				(theMouth
					view: 824
					setPri: (+ (owl priority?) 1)
					setLoop: 5
					posn: (- (owl x?) 1) (- (owl y?) 9)
				)
			)
		)
	)
)
