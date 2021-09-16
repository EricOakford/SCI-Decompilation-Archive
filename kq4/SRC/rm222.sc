;;; Sierra Script 1.0 - (do not remove this comment)
(script# 222)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Menu)
(use Actor)
(use System)

(public
	Room222 0
)

(local
	soundIsOn
	saveSignal
	[local2 3]
	tear
	theTalker
	talkerEyes
	sparkle
	fairy1
	fairy2
	[local11 2]
	rosella
	[local14 5]
	genesta
	[local20 4]
	poof
	saveBits
	[local26 2]
	ripple1
	ripple2
	waveIndex
	[local31 50]
)
(instance openMusic of Sound)

(instance wave1 of Prop)

(instance wave2 of Prop)

(instance waves of List)

(instance Room222 of Room
	(properties
		picture 25
		style DISSOLVE
	)
	
	(method (init)
		(Load VIEW 757)
		(Load VIEW 755)
		(Load VIEW 771)
		(Load VIEW 753)
		(Load VIEW 765)
		(Load PICTURE 25)
		(Load PICTURE 205)
		(Load PICTURE 209)
		(= userFont smallFont)
		(super init:)
		(curRoom setScript: RoomActions)
	)
	
	(method (handleEvent event)
		(cond 
			(
				(and
					(== (event type?) keyDown)
					(== (event message?) `#2)
				)
				(= soundIsOn (DoSound SoundOn))
				(DoSound SoundOn (not soundIsOn))
			)
			(
				(and
					(== (event type?) keyDown)
					(== (event message?) ENTER)
				)
				(theGame restart:)
			)
		)
	)
	
	(method (newRoom n)
		(TheMenuBar draw:)
		(cls)
		(if (IsObject waves)
			(waves dispose:)
		)
		(= userFont bigFont)
		(super newRoom: n)
	)
)

(instance RoomActions of Script
	(method (doit &tmp [openMusicStr 40])
		(super doit:)
		(if debugging
			(Display
				(Format @openMusicStr 222 0
					state
					(openMusic prevSignal?)
					saveSignal
				)
				p_at 0 0
				p_back vWHITE
			)
		)
	)
	
	(method (changeState newState &tmp [temp0 20])
		(switch (= state newState)
			(0
				(openMusic number: 106 loop: 6 play: highState)
				(setupRoom25 doit:)
				(= poof (Prop new:))
				(poof
					posn: 229 125
					view: 680
					loop: 0
					cel: 255
					setCycle: CycleTo 5 1 self
					init:
				)
			)
			(1
				(poof setLoop: 0 setCycle: EndLoop self)
				(= rosella
					((Prop new:)
						view: 757
						posn: 229 125
						setLoop: 0
						cel: 0
						init:
						yourself:
					)
				)
			)
			(2
				(poof dispose:)
				(= genesta
					((Actor new:)
						view: 755
						loop: 0
						posn: -20 (+ (curRoom horizon?) 40)
						setPri: 15
						illegalBits: 0
						ignoreActors:
						cycleSpeed: 0
						setCycle: Forward
						setMotion: MoveTo 260 126
						init:
						yourself:
					)
				)
				(= fairy1
					((Actor new:)
						view: 110
						loop: 0
						posn: -30 (+ (curRoom horizon?) 20)
						illegalBits: 0
						setPri: 14
						ignoreActors:
						cycleSpeed: 0
						setCycle: Forward
						setMotion: MoveTo 260 70 self
						init:
						yourself:
					)
				)
				(if howFast
					(= fairy2
						((Actor new:)
							view: 114
							loop: 0
							posn: -40 (+ (curRoom horizon?) 2)
							setPri: 14
							ignoreActors:
							illegalBits: 0
							cycleSpeed: 0
							setCycle: Forward
							setMotion: MoveTo 260 85
							init:
							yourself:
						)
					)
				)
			)
			(3
				(fairy1 observeBlocks: fairyBlock)
				(if howFast
					(fairy2 observeBlocks: fairyBlock)
				)
				(rosella dispose:)
				(= rosella
					((Prop new:)
						view: 757
						posn: 229 125
						setLoop: 0
						setCel: 1
						init:
						yourself:
					)
				)
				(genesta setLoop: 3 cel: 0 stopUpd:)
				(fairy1 setMotion: Wander)
				(if howFast
					(fairy2 setMotion: Wander)
				)
				(= saveBits
					(Print 222 1
						#title {Genesta}
						#at 100 151
						#width 200
						#dispose
					)
				)
				(Timer setReal: self 12)
			)
			(4
				(rosella dispose:)
				(genesta hide:)
				(cls)
				(if howFast
					(ripple1 dispose:)
					(ripple2 dispose:)
					(waveActions changeState: 10)
				)
				(fairy1 dispose:)
				(if howFast
					(fairy2 dispose:)
				)
				(setupRoom209 doit:)
				(= rosella
					((Actor new:)
						view: 771
						loop: 0
						posn: 107 78
						cel: 0
						setPri: 15
						ignoreActors:
						stopUpd:
						forceUpd:
						init:
						yourself:
					)
				)
				((View new:)
					view: 771
					loop: 1
					cel: 3
					posn: 192 77
					setPri: 15
					ignoreActors:
					addToPic:
				)
				(Timer setReal: self 1)
			)
			(5
				(cls)
				(= saveBits
					(Print 222 2 #at 50 151 #width 200 #draw #dispose)
				)
				(Timer setReal: self 9)
			)
			(6
				(cls)
				(rosella cycleSpeed: 2 setCycle: Forward show:)
				(= saveBits
					(Print 222 3
						#title {Rosella}
						#at 1 151
						#width 200
						#dispose
					)
				)
				(Timer setReal: self 12)
			)
			(7
				(cls)
				(sparkle dispose:)
				(genesta dispose:)
				(rosella hide:)
				(curRoom drawPic: 205)
				((View new:)
					view: 765
					setLoop: 0
					posn: 200 119
					setPri: 0
					ignoreActors:
					addToPic:
				)
				(= theTalker
					((Actor new:)
						view: 765
						loop: 2
						posn: 200 99
						setPri: 14
						ignoreActors:
						cycleSpeed: 3
						setCycle: Forward
						init:
						yourself:
					)
				)
				(blinkEyes changeState: 0)
				(= saveBits
					(Print 222 4
						#title {Genesta}
						#at 60 130
						#width 240
						#dispose
					)
				)
				(Timer setReal: self 11)
			)
			(8
				(cls)
				(= saveBits
					(Print 222 5
						#title {Genesta}
						#at 70 130
						#width 239
						#dispose
					)
				)
				(Timer setReal: self 11)
			)
			(9
				(blinkEyes changeState: 4)
				(Timer setReal: self 14)
			)
			(10
				(cls)
				(blinkEyes changeState: 7)
				(theTalker dispose:)
				(setupRoom209 doit:)
				(rosella
					view: 771
					loop: 0
					cel: 0
					posn: 107 78
					setPri: 15
					ignoreActors:
					cycleSpeed: 2
					show:
					stopUpd:
					forceUpd:
				)
				(= genesta
					((Actor new:)
						view: 771
						loop: 1
						posn: 192 77
						setPri: 15
						ignoreActors:
						cycleSpeed: 2
						setCycle: Forward
						init:
						yourself:
					)
				)
				(Timer setReal: self 2)
			)
			(11
				(cls)
				(= saveBits
					(Print 222 6
						#title {Genesta}
						#at 100 130
						#width 200
						#dispose
					)
				)
				(Timer setReal: self 13)
			)
			(12
				(cls)
				(= saveBits
					(Print 222 7
						#title {Genesta}
						#at 100 130
						#width 200
						#dispose
					)
				)
				(Timer setReal: self 9)
			)
			(13
				(cls)
				(genesta setCel: 0 stopUpd:)
				(rosella setCycle: Forward)
				(= saveBits
					(Print 222 8
						#title {Rosella}
						#at 1 130
						#width 200
						#dispose
					)
				)
				(Timer setReal: self 9)
			)
			(14
				(cls)
				(rosella setCel: 0 stopUpd:)
				(genesta setCycle: Forward)
				(= saveBits
					(Print 222 9
						#title {Genesta}
						#at 100 130
						#width 200
						#dispose
					)
				)
				(Timer setReal: self 14)
			)
			(15
				(cls)
				(sparkle dispose:)
				(rosella dispose:)
				(genesta dispose:)
				(curRoom drawPic: 205)
				((View new:)
					view: 753
					setLoop: 0
					posn: 50 119
					setPri: 0
					ignoreActors:
					addToPic:
				)
				(= theTalker
					((Actor new:)
						view: 753
						loop: 2
						posn: 50 94
						setPri: 14
						ignoreActors:
						cycleSpeed: 3
						setCycle: Forward
						init:
						yourself:
					)
				)
				(rosellaBlinks changeState: 0)
				(cls)
				(= saveBits
					(Print 222 10
						#title {Rosella}
						#at 1 130
						#width 200
						#dispose
					)
				)
				(Timer setReal: self 9)
			)
			(16
				(cls)
				(theTalker hide:)
				(= saveBits
					(Print 222 11
						#title {Genesta}
						#at 100 130
						#width 200
						#dispose
					)
				)
				(Timer setReal: self 12)
			)
			(17
				(cls)
				(theTalker show:)
				(= saveBits
					(Print 222 12
						#title {Rosella}
						#at 1 130
						#width 200
						#dispose
					)
				)
				(Timer setReal: self 14)
			)
			(18
				(cls)
				(rosellaBlinks changeState: 4)
				(cast eachElementDo: #dispose)
				(setupRoom25 doit:)
				(= genesta
					((Actor new:)
						view: 755
						loop: 4
						posn: 260 125
						cel: 0
						setPri: 15
						illegalBits: 0
						ignoreActors:
						cycleSpeed: 5
						init:
						yourself:
					)
				)
				(= fairy1
					((Actor new:)
						view: 110
						loop: 0
						posn: 260 69
						illegalBits: 0
						setPri: 14
						ignoreActors:
						cycleSpeed: 0
						setMotion: Wander
						setCycle: Forward
						observeBlocks: fairyBlock
						init:
						yourself:
					)
				)
				(if howFast
					(= fairy2
						((Actor new:)
							view: 114
							loop: 0
							posn: 260 84
							setPri: 14
							ignoreActors:
							illegalBits: 0
							cycleSpeed: 0
							setMotion: Wander
							setCycle: Forward
							observeBlocks: fairyBlock
							init:
							yourself:
						)
					)
				)
				(= rosella
					((Actor new:)
						view: 757
						setLoop: 0
						posn: 229 125
						cel: 1
						setPri: 14
						init:
						yourself:
					)
				)
				(Timer setReal: self 2)
			)
			(19
				(cls)
				(= saveBits
					(Print 222 13
						#title {Genesta}
						#at 100 151
						#width 200
						#dispose
					)
				)
				(Timer setReal: self 7)
			)
			(20
				(genesta setCycle: EndLoop)
				(Timer setReal: self 7)
			)
			(21
				(cls)
				(= saveBits
					(Print 222 14
						#title {Genesta}
						#at 80 151
						#width 220
						#dispose
					)
				)
				(genesta loop: 3 setCycle: Forward)
				(Timer setReal: self 11)
			)
			(22
				(cls)
				(= saveBits
					(Print 222 15
						#title {Genesta}
						#at 80 151
						#width 220
						#dispose
					)
				)
				(Timer setReal: self 10)
			)
			(23
				(cls)
				(genesta loop: 2 setCycle: EndLoop self)
			)
			(24
				(= poof
					((Actor new:)
						view: 770
						loop: 0
						cel: 0
						posn: (+ (rosella x?) 6) (rosella y?)
						ignoreActors:
						setCycle: EndLoop self
						cycleSpeed: 2
						setPri: 15
						init:
						yourself:
					)
				)
			)
			(25
				(poof setLoop: 1 setCycle: EndLoop self)
				(rosella loop: 1 forceUpd:)
			)
			(26
				(poof dispose:)
				(cls)
				(= saveBits
					(Print 222 16
						#title {Rosella}
						#at 1 151
						#width 200
						#dispose
					)
				)
				(rosella loop: 2 setCycle: EndLoop cycleSpeed: 4 cel: 0)
				(genesta loop: 3 stopUpd: forceUpd:)
				(Timer setReal: self 10)
			)
			(27
				(cls)
				(= saveBits
					(Print 222 17
						#title {Genesta}
						#at 80 151
						#width 220
						#dispose
					)
				)
				(Timer setReal: self 9)
			)
			(28
				(cls)
				(genesta
					setMotion: MoveTo -30 (+ (curRoom horizon?) 1)
					cycleSpeed: 0
					setCycle: Forward self
				)
				(fairy1 ignoreBlocks: fairyBlock)
				(if howFast
					(fairy2 ignoreBlocks: fairyBlock)
					(fairy2 setMotion: MoveTo -30 (+ (curRoom horizon?) 1))
				)
				(fairy1 setMotion: MoveTo -30 (+ (curRoom horizon?) 1))
				(cls)
				(= saveBits
					(Print 222 18 #title {Genesta} #at 200 151 #dispose)
				)
				(Timer setReal: self 7)
			)
			(29
				(rosella setLoop: 1 cel: 0 forceUpd:)
				(Timer setReal: self 13)
			)
			(30
				(cls)
				(= saveBits (Print 222 19 #at -1 151 #dispose))
				(Timer setReal: self 4)
			)
			(31
				(if
					(or
						(and
							(== saveSignal 127)
							(== (openMusic prevSignal?) 93)
						)
						(== (openMusic signal?) -1)
						(and
							(== (openMusic prevSignal?) (openMusic signal?))
							(== (openMusic signal?) 127)
						)
					)
					(cls)
					(= seconds 5)
				else
					(-- state)
					(= cycles 1)
				)
			)
			(32
				(openMusic dispose:)
				(theGame restart:)
			)
		)
	)
)

(instance stopRosella of Code
	(method (doit)
		(rosella stopUpd:)
	)
)

(instance hideRosella of Code
	(method (doit)
		(rosella hide:)
	)
)

(instance gotHere of Code
	(method (doit)
		(Print 222 20)
	)
)

(instance blinkEyes of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= talkerEyes
					((Actor new:)
						view: 765
						loop: 3
						setCel: 0
						posn: 201 67
						setPri: 14
						cycleSpeed: 2
						ignoreActors:
						hide:
						init:
						yourself:
					)
				)
				(self changeState: 1)
			)
			(1
				(talkerEyes hide:)
				(Timer setReal: self (Random 4 7))
			)
			(2
				(talkerEyes show: setCycle: EndLoop self)
			)
			(3
				(self changeState: 1)
			)
			(4
				(if timer (timer dispose:))
				(talkerEyes show:)
				(talkerEyes setLoop: 1 setCycle: EndLoop self)
			)
			(5
				(talkerEyes setLoop: 3 hide:)
				(= tear
					((Actor new:) view: 765 setLoop: 4 init: yourself:)
				)
				(tear
					cel: 2
					setCel:
					posn: (+ (talkerEyes x?) 9) (+ (talkerEyes y?) 6)
					setPri: 15
					ignoreActors:
					setStep: 0 1
					setMotion: MoveTo (+ (talkerEyes x?) 9) (+ (talkerEyes y?) 30) self
				)
			)
			(6
				(tear dispose:)
				(self changeState: 1)
			)
			(7
				(talkerEyes dispose:)
			)
		)
	)
)

(instance rosellaBlinks of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= talkerEyes
					((Actor new:)
						view: 753
						loop: 1
						setCel: 0
						posn: 50 64
						setPri: 14
						cycleSpeed: 5
						ignoreActors:
						init:
						yourself:
					)
				)
				(self changeState: 1)
			)
			(1
				(talkerEyes hide:)
				(Timer setReal: self (Random 4 7))
			)
			(2
				(talkerEyes show: setCycle: EndLoop self)
			)
			(3
				(self changeState: 1)
			)
			(4
				(if timer
					(timer dispose:)
				)
			)
		)
	)
)

(instance setupRoom209 of Code
	(method (doit)
		(curRoom drawPic: 209)
		((View new:)
			view: 771
			cel: 0
			loop: 2
			posn: 165 169
			ignoreActors:
			setPri: 1
			addToPic:
		)
		(= sparkle
			((Actor new:)
				view: 771
				loop: 3
				posn: 152 104
				setPri: 14
				ignoreActors:
				cycleSpeed: 0
				setCycle: Forward
				init:
				yourself:
			)
		)
	)
)

(instance setupRoom25 of Code
	(method (doit)
		(curRoom drawPic: 25)
		(if howFast
			(= ripple1 (Prop new:))
			(= ripple2 (Prop new:))
			(ripple1
				view: 666
				loop: 6
				cel: 1
				posn: 254 182
				setPri: 0
				ignoreActors:
				setCycle: Forward
				init:
			)
			(ripple2
				view: 666
				loop: 7
				cel: 1
				posn: 226 164
				setPri: 0
				ignoreActors:
				setCycle: Forward
				init:
			)
			(wave1
				view: 666
				loop: 3
				cel: 3
				posn: 177 74
				setPri: 0
				ignoreActors:
				setCycle: Forward
				cycleSpeed: 1
				init:
			)
			(wave2
				view: 666
				loop: 4
				cel: 3
				posn: 164 115
				setPri: 0
				ignoreActors:
				setCycle: Forward
				cycleSpeed: 1
				init:
			)
			(waves add: wave1)
			(waves add: wave2)
			(wave1 setScript: waveActions)
		)
	)
)

(instance fairyBlock of Cage
	(properties
		top 66
		left 152
		bottom 115
		right 319
	)
)

(instance waveActions of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(for ((= waveIndex 0)) (< waveIndex (waves size?)) ((++ waveIndex))
					((View new:)
						view: ((waves at: waveIndex) view?)
						loop: ((waves at: waveIndex) loop?)
						cel: 0
						setPri: 0
						ignoreActors:
						x: ((waves at: waveIndex) x?)
						y: ((waves at: waveIndex) y?)
						init:
						addToPic:
						yourself:
					)
				)
				(= waveIndex 0)
				(self changeState: 1)
			)
			(1
				((waves at: waveIndex) cel: 0 show: setCycle: EndLoop self)
			)
			(2
				((waves at: waveIndex) hide:)
				(if (< waveIndex (- (waves size?) 1))
					(++ waveIndex)
				else
					(= waveIndex 0)
				)
				(waveActions changeState: 1)
			)
			(10
				(wave2 dispose:)
				(wave1 setScript: 0 dispose:)
			)
		)
	)
)

(instance highState of Script
	(method (cue)
		(if (> (openMusic signal?) saveSignal)
			(= saveSignal (openMusic signal?))
		)
	)
)
