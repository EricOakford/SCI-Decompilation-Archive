;;; Sierra Script 1.0 - (do not remove this comment)
(script# 681)
(include game.sh)
(use Main)
(use KQ5Room)
(use Sync)
(use RandCyc)
(use PolyPath)
(use Reverse)
(use Motion)
(use Actor)
(use System)

(public
	cdElfToon 0
)

(local
	local0
	gGameEgoMoveSpeed
)
(instance cdElfToon of KQ5Room
	(properties
		picture 83
	)
	
	(method (init)
		(super init:)
		(Load PICTURE 84)
		(Load SCRIPT 941)
		(Load SCRIPT 929)
		(Load SCRIPT 969)
		(Load SOUND 90)
		(Load VIEW 796)
		(Load VIEW 797)
		(Load VIEW 2)
		(Load VIEW 1032)
		(Load VIEW 780)
		(Load 142 5099)
		(= gGameEgoMoveSpeed (theGame egoMoveSpeed?))
		(theGame egoMoveSpeed: 1)
		(self setScript: enterRoom)
	)
	
	(method (dispose)
		(theGame egoMoveSpeed: gGameEgoMoveSpeed)
		(DisposeScript 941)
		(DisposeScript 969)
		(super dispose:)
	)
)

(instance cartoon of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(elf hide:)
				(workers hide:)
				(worker2 hide:)
				(ego hide:)
				((ego head?) hide:)
				(DrawPic 84)
				(theMouth init:)
				(arms init:)
				(theEyes init:)
				(= seconds 3)
			)
			(1
				(theMouth setCycle: MouthSync 5099)
				(SpeakAudio 5099 self)
			)
			(2
				(cls)
				(theMouth setCycle: 0)
				(= seconds 2)
			)
			(3
				(arms cycleSpeed: 1 setCycle: EndLoop)
				(= local0 1)
				(= seconds 2)
			)
			(4
				(curRoom setScript: exitRoom)
				(theMouth hide:)
				(arms dispose:)
				(theEyes dispose:)
				(self dispose:)
			)
		)
	)
)

(instance theMouth of Prop
	(properties
		x 105
		y 72
		view 1032
		cel 4
		priority 10
		signal (| fixedLoop fixPriOn)
		cycleSpeed 2
	)
)

(instance theEyes of Prop
	(properties
		x 119
		y 60
		view 780
		loop 1
		priority 10
		signal (| fixedLoop fixPriOn)
	)
	
	(method (doit)
		(super doit: &rest)
		(if (and (== (Random 1 40) 1) (not cycler) (not local0))
			(self setCycle: EndLoop)
		)
	)
)

(instance arms of Prop
	(properties
		x 128
		y 93
		view 780
		loop 2
		priority 10
		signal (| fixedLoop fixPriOn)
	)
)

(instance enterRoom of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(DrawPic 83)
				(if (!= (theMusic number?) 90)
					(theMusic number: 90 loop: -1 vol: 127 playBed:)
				)
				(workers setLoop: 2 init:)
				(worker2 setLoop: 3 cycleSpeed: 2 setCycle: Forward init:)
				(elf setLoop: 1 posn: 48 87 init:)
				(ego
					init:
					view: 796
					setLoop: 8
					cel: 2
					ignoreActors: TRUE
					illegalBits: 0
					setStep: 2 1
					posn: 280 24
					normal: 0
				)
				((ego head?) hide:)
				(= cycles 15)
			)
			(1
				(ego
					setCycle: Reverse
					setMotion: MoveTo 271 51 self
					cycleSpeed: 1
					moveSpeed: 1
				)
			)
			(2
				(ego
					setMotion: MoveTo 253 107 self
					cycleSpeed: 1
					moveSpeed: 1
				)
			)
			(3
				(ego
					setLoop: 9
					posn: 251 124
					cel: 0
					setCycle: EndLoop self
					cycleSpeed: 2
				)
			)
			(4
				(ego
					view: 2
					setStep: 2 1
					setCycle: KQ5SyncWalk
					normal: 1
					setLoop: -1
					setMotion: MoveTo (- (ego x?) 4) (- (ego y?) 0) self
				)
				((ego head?) show:)
			)
			(5
				(SpeakAudio 5096 self)
				(elf setScript: elfComesDown)
			)
			(6
				(ego setMotion: MoveTo 232 133 self)
			)
			(7
				(cls)
				(ego setMotion: MoveTo 65 128 self)
			)
			(8
				(elfComesDown cue:)
				(self dispose:)
			)
		)
	)
)

(instance elfComesDown of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(elf cel: 0 cycleSpeed: 2 setCycle: Forward)
				(= cycles 30)
			)
			(1
				(elf
					setLoop: 4
					cel: 0
					setCycle: Forward
					setMotion: MoveTo 40 93 self
				)
			)
			(2
				(elf cel: 0 posn: 40 100 setLoop: 5 setCycle: EndLoop self)
			)
			(3
				(elf posn: 49 127 setLoop: 6 cel: 0 setCycle: EndLoop self)
			)
			(4 (elf cel: 0 setLoop: 7))
			(5
				(ego get: iElfShoes)
				(SolvePuzzle 2)
				(elf setCycle: EndLoop self)
			)
			(6 (= cycles 10))
			(7
				((ego head?) hide:)
				(ego
					normal: 0
					view: 796
					setLoop: 0
					setCel: 0
					setCycle: CycleTo 4 1 self
					cycleSpeed: 3
				)
			)
			(8 (curRoom setScript: cartoon))
		)
	)
)

(instance exitRoom of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(DrawPic 83)
				(workers show:)
				(worker2 show:)
				(elf show:)
				((ego head?) hide:)
				(ego view: 796 normal: 0 setLoop: 0 show:)
				(ego cel: 4)
				(= cycles 15)
			)
			(1 (ego setCycle: EndLoop self))
			(2
				(ego view: 2 normal: 1 setLoop: 1)
				((ego head?) show:)
				(elf loop: 1 cel: 0 setCycle: CycleTo 6 1 self)
			)
			(3
				(elf
					view: 797
					loop: 3
					cel: 0
					cycleSpeed: 8
					setCycle: RandCycle
				)
				(SpeakAudio 5097 self)
			)
			(4
				(cls)
				(elf loop: 5 cel: 0 setCycle: BegLoop)
				(ego
					normal: 0
					view: 797
					setLoop: 1
					cel: 0
					cycleSpeed: 8
					setCycle: RandCycle
				)
				((ego head?) hide:)
				(talkingHead
					init:
					x: (ego x?)
					y: (- (ego y?) 26)
					cycleSpeed: 8
					setCycle: RandCycle
				)
				(SpeakAudio 5098 self)
			)
			(5
				(cls)
				(ego
					normal: 1
					view: 2
					setLoop: -1
					setCycle: KQ5SyncWalk
					setMotion: MoveTo 259 147 self
				)
				(talkingHead dispose:)
				((ego head?) show:)
			)
			(6
				((ego head?) hide:)
				(ego
					normal: 0
					view: 796
					loop: 11
					cel: 1
					setCycle: CycleTo 4 1 self
				)
			)
			(7
				(ego loop: 12 cel: 0 setCycle: EndLoop self)
			)
			(8
				(ego loop: 13 setCycle: EndLoop self)
			)
			(9
				(elf cycleSpeed: 5 setCycle: EndLoop self)
				(ego cycleSpeed: 3 setCycle: Forward)
			)
			(10
				(ego
					loop: 12
					cel: 2
					cycleSpeed: (theGame egoMoveSpeed?)
					setCycle: BegLoop self
				)
			)
			(11
				(elf setCycle: BegLoop self)
				(ego loop: 11 cel: 4 setCycle: CycleTo 1 -1 self)
			)
			(12)
			(13
				(crawlThroughHole start: 1)
				(self setScript: crawlThroughHole)
			)
		)
	)
)

(instance crawlThroughHole of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setCel: -1 setMotion: PolyPath 259 147 self)
			)
			(1
				(cls)
				(ego
					normal: 0
					view: 796
					setLoop: 14
					cel: 0
					setCycle: CycleTo 3 1 self
				)
				((ego head?) hide:)
			)
			(2
				(ego x: (+ (ego x?) 3) setCel: 5)
				(= cycles 4)
			)
			(3
				(ego x: (+ (ego x?) 3) setCel: 4)
				(= cycles 3)
			)
			(4
				(ego x: (+ (ego x?) 3) setCel: 5)
				(= cycles 4)
			)
			(5
				(theMusic fade:)
				(curRoom newRoom: 215)
			)
		)
	)
)

(instance workers of Prop
	(properties
		x 97
		y 82
		view 796
		cycleSpeed 1
	)
	
	(method (doit)
		(super doit:)
		(if (== (theMusic prevSignal?) 10)
			(theMusic prevSignal: 0)
			(self cel: 0 setCycle: EndLoop)
		)
	)
)

(instance worker2 of Prop
	(properties
		x 125
		y 81
		view 796
	)
)

(instance elf of Actor
	(properties
		x 40
		y 93
		view 796
		priority 9
		signal (| ignrAct fixPriOn)
	)
)

(instance talkingHead of Prop
	(properties
		view 797
		loop 2
		priority 15
		signal fixPriOn
	)
)
