;;; Sierra Script 1.0 - (do not remove this comment)
(script# 659)
(include game.sh)
(use Main)
(use AudioScript)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	cdIntro10 0
)

(local
	[local0 3]
	local3
	local4
	local5
	local6
)
(instance cdIntro10 of Room
	(properties
		picture 76
		style FADEOUT
	)
	
	(method (init)
		(Load PICTURE 76)
		(HandsOff)
		(theGame setCursor: invCursor TRUE)
		(super init:)
		(UnLoad PICTURE 1)
		(LoadMany VIEW 764 766 0 768 777 783 1101)
		(owl
			init:
			cycleSpeed:
			(switch howFast
				(2 2)
				(else  0)
			)
		)
		(owlHead init:)
		(owlMouth init: hide:)
		(owlWing
			init:
			cycleSpeed:
			(switch howFast
				(2 2)
				(else  0)
			)
		)
		(crispin init:)
		(trunk init:)
		(crispinCup init:)
		(ego view: 766 setLoop: 0 normal: 0 posn: 173 125 init:)
		(ego cel: (- (NumCels ego) 1))
		((ego head?) hide:)
		(egoCup init:)
		(self setScript: a2s5Script setRegions: 769)
		(super init:)
	)
	
	(method (newRoom n)
		(super newRoom: n)
		(ego setPri: -1 setStep: 3 2 cycleSpeed: 0)
	)
)

(instance a2s5Script of AudioScript

	(method (changeState newState)
		(switch (= state newState)
			(0
				(Load SCRIPT 929)
				(LoadMany VIEW 765 1103 771 775 767 769 1100 1102 779)
				(Load RES_SYNC 10113)
				(= cycles 1)
			)
			(1
				(theMouth init: changeMouth: 0 play: 10113 1)
				(= waitForCue 4096)
			)
			(2
				(owlHead setCycle: EndLoop)
				(ego setScript: egoDrinkTea)
				(owl setScript: owlDrinkingTea)
				(= waitForCue 4352)
			)
			(3 (= waitForCue 4608))
			(4
				(theMouth hide:)
				(= cycles 1)
			)
			(5
				(if (== howFast 2)
					(ego view: 767 setLoop: 2 setCycle: EndLoop self)
				else
					(ego view: 767 setLoop: 2)
					(ego setCel: (- (NumCels ego) 1))
					(= cycles 1)
				)
			)
			(6
				(owl setScript: owlDrinkingTea)
				(theMouth changeMouth: 4 show:)
				(ego hide:)
				(= waitForCue 5378)
			)
			(7
				(if (== howFast 2)
					(ego setLoop: 2 setCycle: BegLoop self show:)
					(ego cel: (- (NumCels ego) 1))
				else
					(ego setLoop: 2 cel: 0 show:)
					(= cycles 1)
				)
				(theMouth hide:)
			)
			(8
				(ego setScript: egoDrinkTea)
				(owl setScript: owlDrinkingTea)
				(crispin hide:)
				(crispinTop init: setCycle: EndLoop)
				(crispinBottom init:)
				(= waitForCue 5632)
			)
			(9
				(crispinTop setCycle: 0)
				(= cycles 1)
			)
			(10
				(crispinTop setCel: 255)
				(theMouth changeMouth: 0 show: posn: 135 87 setPri: 15)
				(= waitForCue 5888)
			)
			(11
				(theMouth hide:)
				(crispinTop
					cycleSpeed:
					(switch howFast
						(2 1)
						(else  0)
					)
					setCycle: BegLoop
				)
				(= waitForCue 6144)
			)
			(12
				(ego setScript: egoDrinkTea)
				(theMouth changeMouth: 6 show:)
				(= waitForCue 6400)
			)
			(13
				(owlMouth setCycle: 0 setCel: 0 show:)
				(theMouth hide:)
				(if (== howFast 2)
					(crispinTop setLoop: 5 setCycle: EndLoop)
					(= waitForCue 8192)
				else
					(crispinTop setLoop: 5)
					(crispinTop cel: (- (NumCels crispinTop) 1))
					(= cycles 1)
				)
			)
			(14
				(crispinTop
					setCycle: 0
					setLoop: 6
					cycleSpeed:
					(switch howFast
						(2 2)
						(else  0)
					)
				)
				(theMouth changeMouth: 1 show:)
				(crispinTop hide:)
				(= waitForCue 8448)
			)
			(15 (= waitForCue 8960))
			(16
				(owlHead hide:)
				(owlWing hide:)
				(owlMouth hide:)
				(owl
					setLoop: 6
					cycleSpeed:
					(switch howFast
						(2 2)
						(else  1)
					)
					setCycle: EndLoop
				)
				(crispinTop setCel: 0 show:)
				(theMouth setCel: 255)
				(= waitForCue 9216)
			)
			(17
				(owl setCycle: 0)
				(= cycles 1)
			)
			(18
				(owl setCel: 255)
				(theMouth changeMouth: 6 posn: 96 94 show:)
				(= waitForCue 9472)
				659
				0
				(ego setScript: egoDrinkTea)
			)
			(19
				(theMouth hide:)
				(owl
					cycleSpeed:
					(switch howFast
						(2 2)
						(else  1)
					)
					setCycle: BegLoop self
				)
				(crispinTop hide:)
				(if (== howFast 2)
					(crispinBottom
						setLoop: 7
						cycleSpeed:
						(switch howFast
							(2 2)
							(else  0)
						)
						setPri: (crispinTop priority?)
						setCycle: EndLoop
					)
				else
					(crispinBottom
						setLoop: 7
						setPri: (crispinTop priority?)
						setCycle: 0
					)
					(crispinBottom cel: (- (NumCels crispinBottom) 1))
				)
			)
			(20
				(owlWing show:)
				(owl setLoop: 0 cel: 0)
				(owlHead show:)
				(owlMouth setCel: 0 show:)
				(= cycles 1)
			)
			(21
				(if
				(!= (crispinBottom cel?) (- (NumCels crispinBottom) 1))
					(-- state)
				)
				(= cycles 1)
			)
			(22
				(theMouth changeMouth: 2 show:)
				(= cycles 1)
			)
			(23 (= waitForCue 9988))
			(24
				(theMouth changeMouth: 6)
				(= waitForCue 10240)
			)
			(25
				(owlMouth setCycle: 0 setCel: 0)
				(theMouth hide:)
				(= local6 0)
				(crispinBottom setCycle: BegLoop self)
			)
			(26
				(crispinBottom setLoop: 0 cycleSpeed: 0 cel: 1)
				(theMouth changeMouth: 1 show:)
				(= waitForCue 12288)
			)
			(27
				(crispinBottom hide: dispose:)
				(theMouth hide:)
				(= local4 1)
				(crispin
					view: 771
					show:
					setPri: 14
					setLoop: 0
					cycleSpeed:
					(switch howFast
						(2 2)
						(else  0)
					)
					cel: 0
					setCycle: EndLoop self
				)
			)
			(28
				(crispinCup addToPic:)
				(crispin cel: 0 setLoop: 10 setCycle: EndLoop self)
			)
			(29
				(crispin
					view: 1102
					setLoop: 1
					cycleSpeed: (if (== howFast 2) 6 else 1)
					cel: 0
					setCycle: Forward
					signal: (| (crispin signal?) $4800)
					setMotion: MoveTo 145 126 self
					moveSpeed: (if (== howFast 2) 14 else 3)
				)
			)
			(30
				(crispin setMotion: MoveTo 207 126 self)
			)
			(31
				(ego view: 1100 setLoop: 4 cel: 0)
				(crispin
					setLoop: 3
					setCycle: Forward
					setMotion: MoveTo 219 118 self
				)
			)
			(32
				(crispin
					setCycle: 0
					moveSpeed: (if (== howFast 2) 2 else 0)
					cycleSpeed:
					(switch howFast
						(2 1)
						(else  0)
					)
				)
				(= waitForCue 12368)
			)
			(33
				(crispin
					view: 775
					setLoop: 4
					cel: 0
					setCycle: CycleTo 4 1 self
				)
			)
			(34 (= waitForCue 12544))
			(35
				(crispin setCycle: EndLoop self)
			)
			(36
				(crispin
					setLoop: 5
					setCycle: Forward
					cel: 0
					setScript: searchThroughChest self
				)
			)
			(37 (= waitForCue 13824))
			(38
				(crispin setCycle: 0)
				(= waitForCue 14080)
			)
			(39
				(if (== howFast 2)
					(crispin setLoop: 6 cel: 0 setCycle: EndLoop self)
				else
					(crispin setLoop: 6)
					(crispin cel: (- (NumCels crispin) 1))
					(= cycles 1)
				)
			)
			(40
				(if (== howFast 2)
					(crispin setLoop: 7 cel: 0 setCycle: EndLoop self)
				else
					(crispin setLoop: 7)
					(crispin cel: (- (NumCels crispin) 1))
					(= cycles 1)
				)
			)
			(41
				(crispin
					view: 1102
					setLoop: 2
					setCycle: Walk
					setMotion: MoveTo (- (crispin x?) 6) (crispin y?) self
				)
			)
			(42
				(crispin view: 765 setLoop: 3)
				(crispin cel: (- (NumCels crispin) 1))
				(theMouth changeMouth: 3 show:)
				(= waitForCue 14592)
				659
				1
			)
			(43
				(theMouth hide:)
				(if (== howFast 2)
					(crispin
						view: 765
						setLoop: 2
						cycleSpeed:
						(switch howFast
							(2 2)
							(else  0)
						)
						cel: 0
						setCycle: EndLoop self
					)
				else
					(crispin
						view: 765
						setLoop: 2
						cycleSpeed:
						(switch howFast
							(2 2)
							(else  0)
						)
					)
					(crispin cel: (- (NumCels crispin) 1))
					(= cycles 1)
				)
			)
			(44
				(if (== howFast 2) (= seconds 1) else (= cycles 1))
			)
			(45
				(ego
					view: 1100
					cycleSpeed:
					(switch howFast
						(2 2)
						(else  0)
					)
					setLoop: 4
					cel: 0
					setCycle: CycleTo 3 1 self
				)
			)
			(46
				(if (== howFast 2)
					(ego cel: 4 setCycle: EndLoop self)
					(crispin setLoop: 3 cel: 0 setCycle: EndLoop)
				else
					(ego cel: (- (NumCels ego) 1))
					(crispin setLoop: 3)
					(crispin cel: (- (NumCels crispin) 1))
					(= cycles 1)
				)
			)
			(47 (= waitForCue 16384))
			(48
				(theMouth changeMouth: 5 show:)
				(ego ignoreActors: 1 setLoop: 5 setCycle: EndLoop)
				(= waitForCue 16640)
			)
			(49
				(theMouth changeMouth: 3 show:)
				(= waitForCue 16896)
			)
			(50 659 2 (= waitForCue 17152))
			(51 (= waitForCue 17232))
			(52
				(crispin hide:)
				(theMouth hide:)
				(crispinBottom
					view: 765
					posn: 211 114
					setLoop: 4
					init:
					show:
				)
				(crispinTop
					view: 765
					posn: 206 100
					setLoop: 5
					cel: 0
					setCycle: Forward
					init:
					show:
				)
				(if (!= howFast 2)
					(= waitForCue 17408)
				else
					(= seconds 1)
				)
			)
			(53
				(if (== howFast 2)
					(crispinTop setCycle: EndLoop self)
				else
					(crispinTop setCycle: 0)
					(crispinTop cel: (- (NumCels crispinTop) 1))
					(= cycles 1)
				)
			)
			(54
				(if (== howFast 2)
					(= waitForCue 17408)
				else
					(= cycles 1)
				)
			)
			(55
				(theMouth posn: 208 88 setPri: 15 show:)
				(= waitForCue 17920)
			)
			(56
				(theMouth hide:)
				(crispinTop dispose:)
				(crispinBottom dispose:)
				(if (== howFast 2)
					(crispin
						show:
						view: 1103
						setLoop: 0
						cycleSpeed:
						(switch howFast
							(2 2)
							(else  0)
						)
						cel: 0
						setCycle: EndLoop self
					)
				else
					(crispin
						show:
						view: 1103
						setLoop: 0
						cycleSpeed:
						(switch howFast
							(2 2)
							(else  0)
						)
					)
					(crispin cel: (- (NumCels crispin) 1))
					(= cycles 1)
				)
			)
			(57
				(ego view: 769 get: 28 setLoop: 3 setCycle: CycleTo 3 1 self)
			)
			(58
				(ego setCycle: EndLoop self)
				(if (== howFast 2)
					(crispin view: 1103 setLoop: 1 cel: 0 setCycle: EndLoop)
				else
					(crispin view: 1103 setLoop: 1)
					(crispin cel: (- (NumCels crispin) 1))
				)
			)
			(59
				(if (!= (crispin cel?) (crispin lastCel:)) (-- state))
				(= cycles 1)
			)
			(60
				(ego cel: 0)
				(theMouth posn: 212 87 setPri: 15 show:)
				(= waitForCue 18432)
			)
			(61 (= waitForCue 18688))
			(62
				(ego view: 766 setLoop: 0)
				(ego cel: (- (NumCels ego) 1))
				((ego head?) hide:)
				(theMouth changeMouth: 6)
				(= waitForCue 20481)
			)
			(63
				(ego view: 1100 setLoop: 4 cel: 0)
				(owlMouth setCycle: 0 setCel: 0 show:)
				(theMouth changeMouth: 3 posn: 212 87)
				(= waitForCue 20736)
			)
			(64
				(theMouth changeMouth: 5 show:)
				(= waitForCue 20992)
			)
			(65
				(theMouth hide:)
				(owlHead hide:)
				(owlWing hide:)
				(owlMouth hide:)
				(owl setLoop: 7 cel: 0 setCycle: EndLoop self)
			)
			(66
				(owl setLoop: 8 cel: 0 setCycle: EndLoop self)
			)
			(67
				(crispin view: 771 setLoop: 11 setCycle: EndLoop)
				(owl
					setLoop: 9
					signal: 18432
					posn: 110 103
					cel: 0
					setCycle: Forward
					setMotion: MoveTo 146 178 self
				)
			)
			(68
				(owl dispose:)
				(= cycles 1)
			)
			(69
				(ego view: 766 setLoop: 1 setCycle: EndLoop self)
			)
			(70
				(egoCup addToPic:)
				(ego
					view: 0
					setLoop: 2
					normal: 1
					setCycle: KQ5SyncWalk
					cycleSpeed: 0
					moveSpeed: 1
					setStep: 3 2
					setMotion: MoveTo 145 191 self
				)
				((ego head?) show:)
			)
			(71
				(if (> (DoAudio 6) -1) (-- state))
				(= cycles 1)
			)
			(72
				(if global327 (= quit 1) else (curRoom newRoom: 1))
			)
		)
	)
)

(instance searchThroughChest of AudioScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= waitForCue 12800))
			(1
				(crispin setCycle: 0)
				(= waitForCue 13056)
			)
			(2
				(crispin setCycle: Forward)
				(= waitForCue 13312)
			)
			(3
				(crispin setCycle: 0)
				(= waitForCue 13568)
			)
			(4
				(crispin setCycle: Forward)
				(= cycles 1)
			)
			(5 (client setScript: 0))
		)
	)
)

(instance egoDrinkTea of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(egoCup hide:)
				(ego
					view: 779
					setLoop: 0
					cycleSpeed:
					(switch howFast
						(2 2)
						(else  0)
					)
					cel: 0
					setCycle: EndLoop self
				)
			)
			(1
				(ego setLoop: 1 cel: 0 setCycle: EndLoop self)
			)
			(2 (= seconds 2))
			(3 (ego setCycle: BegLoop self))
			(4
				(ego setLoop: 0)
				(ego cel: (- (NumCels ego) 1) setCycle: BegLoop self)
			)
			(5
				(ego view: 766 setLoop: 0)
				(ego cycleSpeed: 0)
				(egoCup show:)
				(client setScript: 0)
			)
		)
	)
)

(instance bodyScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (client setCycle: EndLoop self))
			(1 (= seconds 2))
			(2 (client setCycle: BegLoop self))
			(3 (client setScript: 0))
		)
	)
)

(instance egoCup of Prop
	(properties
		x 155
		y 103
		view 777
		cel 1
	)
)

(instance crispin of Actor
	(properties
		x 140
		y 124
		view 764
	)
	
	(method (doit)
		(super doit:)
		(if (not local4)
			(self
				priority: (+ (trunk priority?) 1)
				signal: (| (self signal?) $0010)
			)
		)
	)
)

(instance crispinCup of Prop
	(properties
		x 144
		y 105
		view 777
		signal $4000
	)
)

(instance crispinTop of Prop
	(properties
		x 135
		y 107
		view 764
		loop 2
	)
	
	(method (doit)
		(super doit:)
		(self
			priority: (+ (crispinBottom priority?) 1)
			signal: (| (self signal?) $0010)
		)
	)
)

(instance crispinBottom of Prop
	(properties
		x 140
		y 124
		view 764
		cel 1
	)
	
	(method (doit)
		(super doit:)
		(if local4
			(self
				priority: (+ (trunk priority?) 1)
				signal: (| (self signal?) $0010)
			)
		)
	)
)

(instance trunk of Prop
	(properties
		x 219
		y 118
		view 1101
	)
	
	(method (doit)
		(super doit:)
		(self
			setCel:
				(cond 
					(local3 7)
					((== (crispin loop?) 4)
						(switch (crispin cel?)
							(5 0)
							(6 1)
							(7 2)
							(8 4)
							(9 5)
							(10
								6
								(= local3 1)
								(self addToPic:)
							)
						)
					)
				)
		)
	)
)

(instance owl of Actor
	(properties
		x 90
		y 109
		view 768
		priority 8
	)
)

(instance owlHead of Prop
	(properties
		x 92
		y 102
		view 768
		loop 1
		priority 8
	)
	
	(method (init)
		(super init:)
		(self setPri: 8)
	)
)

(instance owlMouth of Prop
	(properties
		x 94
		y 94
		view 768
		loop 3
		priority 8
	)
	
	(method (init)
		(super init:)
		(self setPri: 8)
	)
	
	(method (doit)
		(super doit:)
		(if (== (owl loop?) 6)
			(self x: 96 y: 94)
		else
			(self x: 94 y: 94)
		)
	)
)

(instance owlWing of Prop
	(properties
		x 86
		y 100
		view 768
		loop 4
		priority 12
	)
	
	(method (init)
		(super init:)
		(self setPri: 8)
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(not script)
				(not (& (owlWing signal?) $0008))
				local6
			)
			(switch (Random 1 10)
				(1
					(self setScript: (bodyScript new:))
				)
			)
		)
	)
)

(instance theMouth of MonoAudioProp
	(properties
		x 133
		y 88
		view 783
		loop 1
	)
	
	(method (changeMouth param1)
		(self z: 0)
		(= local6 0)
		(switch param1
			(0
				(theMouth view: 783 loop: 1 x: 133 y: 88 setPri: 15)
			)
			(1
				(theMouth view: 764 loop: 6 x: 135 y: 107)
			)
			(2
				(theMouth view: 783 setLoop: 8 posn: 119 88 setPri: 15)
			)
			(3
				(theMouth view: 765 setLoop: 10 setPri: 15 posn: 212 87)
			)
			(4
				(theMouth view: 767 setLoop: 7 posn: (ego x?) (ego y?))
			)
			(5
				(theMouth view: 766 loop: 3 x: 172 y: 125 z: 36)
			)
			(6
				(theMouth
					view: 768
					loop: 3
					posn: (owlMouth x?) (owlMouth y?)
					setPri: 8
				)
				(= local6 1)
			)
		)
	)
)

(instance owlDrinkingTea of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(owlHead hide:)
				(owlMouth hide:)
				(owlWing hide:)
				(owl setLoop: 2 setCycle: EndLoop self)
			)
			(1 (= seconds 2))
			(2 (owl setCycle: BegLoop self))
			(3
				(owlHead show:)
				(owlMouth show:)
				(owlWing show:)
				(owl setLoop: 0 cel: 0)
				(client setScript: 0)
			)
		)
	)
)
