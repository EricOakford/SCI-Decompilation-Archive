;;; Sierra Script 1.0 - (do not remove this comment)
(script# 630)
(include sci.sh)
(use Main)
(use GloryWindow)
(use TellerIcon)
(use EgoDead)
(use OccasionalCycle)
(use GloryControls)
(use JumpX)
(use IconBar)
(use Feature)
(use LoadMany)
(use DPath)
(use Reverse)
(use GControl)
(use Window)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm630 0
)

(local
	local0
	[local1 5]
	[local6 5]
	local11
	local12 =  1
	local13 =  1
	local14
	local15
	local16
	[local17 2]
	local19 =  7
	local20 =  160
	local21 =  3
	local22 =  1
	local23
	local24 =  -4
	local25
	local26 =  10
	local27 =  12
	local28
	[local29 5] = [0 20 21 24 999]
	[local34 3]
	[local37 6] = [0 -2 15 1 16 999]
	[local43 4] = [0 17 6 999]
	[local47 6]
	[local53 3] = [0 -2 999]
)
(procedure (localproc_07dd)
	(bridgeControls disable: iconJump iconDrop iconL iconR)
	(iconJump show:)
	(iconDrop show:)
	(iconL show:)
	(iconR show:)
)

(procedure (localproc_081c)
	(bridgeControls
		enable: iconJump iconDrop (if (== prevRoomNum 640) iconR else iconL)
	)
	(iconJump show:)
	(iconDrop show:)
	(iconR show:)
	(iconL show:)
)

(procedure (localproc_0864 &tmp temp0)
	(= temp0 0)
	(if (> (Random 1 11) local26)
		(cond 
			((== prevRoomNum 640) (if (> (ego x?) 215) (= local26 local27) (= temp0 1)))
			((< (ego x?) 258) (= local26 local27) (= temp0 1))
		)
	else
		(-- local26)
	)
	(return temp0)
)

(procedure (localproc_2563 &tmp theTheCursor)
	(= theTheCursor theCursor)
	(Message msgGET 630 2 6 3 2 @local1)
	(Message msgGET 630 2 6 3 3 @local6)
	(quest init: show: dispose:)
	(theGame setCursor: theTheCursor)
	(return local0)
)

(class JumpOver of JumpX
	(properties
		client 0
		caller 0
		x 0
		y 0
		dx 0
		dy 0
		b-moveCnt 0
		b-i1 0
		b-i2 0
		b-di 0
		b-xAxis 0
		b-incr 0
		completed 0
		xLast 0
		yLast 0
		desX 0
		desY 0
		xInc 0
		yInc 0
		zStep 0
		level 0
		velocity 0
		gravity 10
		height 0
		peak 0
	)
	
	(method (chkTarget)
		(leopard setPri: 15)
	)
)

(class Attack of JumpOver
	(properties
		client 0
		caller 0
		x 0
		y 0
		dx 0
		dy 0
		b-moveCnt 0
		b-i1 0
		b-i2 0
		b-di 0
		b-xAxis 0
		b-incr 0
		completed 0
		xLast 0
		yLast 0
		desX 0
		desY 0
		xInc 0
		yInc 0
		zStep 0
		level 0
		velocity 0
		gravity 10
		height 0
		peak 0
	)
	
	(method (chkTarget &tmp temp0)
		(= temp0 0)
		(super chkTarget:)
		(cond 
			((== (ego view?) 29) (if (< (ego cel?) 3) (= temp0 1) else (return)))
			((== (ego view?) 30)
				(if (or (< (ego cel?) 1) (> (ego cel?) 5))
					(= temp0 1)
				else
					(return)
				)
			)
			(else (= temp0 1))
		)
		(if temp0
			(leopardAttack register: 1)
			(ego setScript: 0)
			(curRoom setScript: egoFell 0 0)
		)
	)
)

(class DDCPath of DPath
	(properties
		client 0
		caller 0
		x 0
		y 0
		dx 0
		dy 0
		b-moveCnt 0
		b-i1 0
		b-i2 0
		b-di 0
		b-xAxis 0
		b-incr 0
		completed 0
		xLast 0
		yLast 0
		points 0
		value 0
		cDir 1
		nPoints 0
		comp 0
	)
	
	(method (init)
		(if (not nPoints) (self nPoints: (- argc 2)))
		(super init: &rest)
	)
	
	(method (moveDone)
		(cond 
			((self comp?)
				(self comp: 0)
				(if (self cDir: (Random 0 1))
					(self value: 0)
				else
					(self value: (self nPoints?))
				)
			)
			((== (self value?) -1)
				(if (self cDir: (Random 0 1))
					(self value: 0)
				else
					(self value: (self nPoints?))
				)
				(self init:)
			)
			((== (points at: value) -32768) (self value: 0) (self comp: 1) (self init:))
			(else (self init:))
		)
	)
	
	(method (setTarget)
		(if (self cDir?)
			(= x (points at: value))
			(= y (points at: (++ value)))
			(++ value)
		else
			(= y (points at: value))
			(= x (points at: (-- value)))
			(-- value)
		)
	)
)

(class Backward of Rev
	(properties
		client 0
		caller 0
		cycleDir -1
		cycleCnt 0
		completed 0
	)
	
	(method (doit)
		(if (not (client isStopped:)) (super doit:))
	)
)

(class xDPath of DDCPath
	(properties
		client 0
		caller 0
		x 0
		y 0
		dx 0
		dy 0
		b-moveCnt 0
		b-i1 0
		b-i2 0
		b-di 0
		b-xAxis 0
		b-incr 0
		completed 0
		xLast 0
		yLast 0
		points 0
		value 0
		cDir 1
		nPoints 0
		comp 0
	)
	
	(method (setTarget &tmp theX theY [temp2 30])
		(= theX x)
		(= theY y)
		(super setTarget:)
		(client loop: 4)
		(switch (GetAngle theX theY x y)
			(46 (client cel: 0))
			(65 (client cel: 3))
			(112 (client cel: 2))
			(134 (client cel: 0))
			(226 (client cel: 2))
			(245 (client cel: 1))
			(292 (client cel: 1))
			(314 (client cel: 3))
		)
		(Wait 0)
		(Wait 5)
		(Animate (cast elements?) 0)
		(Wait 5)
	)
)

(instance rm630 of Rm
	(properties
		noun 4
		picture 630
		vanishingY -420
	)
	
	(method (init)
		(HandsOff)
		(LoadMany 128 8 2 11 632 29 30)
		((ScriptID 36 0) x: 5 y: 2 textX: 20 textY: 150)
		(= [local34 0] @local29)
		(= [local47 0] @local37)
		(= [local47 1] @local43)
		(leopard init: setScale: 170)
		(skull init:)
		(doorway init:)
		(treehouse init:)
		(tree init:)
		(cSound number: 630 setLoop: -1 play: 127)
		(if (== prevRoomNum 640)
			(ego
				x: 121
				y: 89
				z: 30
				setScale: 420
				origStep: 16386
				init:
				get: 6
				changeGait: 2 0
			)
			(leopardLady setScale: 420 init:)
			(rope cel: 3 cycleSpeed: 3 init:)
			(super init: &rest)
			(= local22 0)
			(= local24 4)
			(leopard show:)
			(self setScript: backFence)
		else
			(ego
				view: 4
				loop: 3
				cel: 4
				x: 301
				y: 101
				z: 30
				noun: 5
				setPri: 2
				setScale: 420
				init:
				hide:
			)
			(leopardLady setScale: 420 init: hide:)
			(theHut init:)
			(egoTell init: ego @local29 @local34)
			(johariTell init: leopardLady @local37 @local47 @local53)
			(walkHandler addToFront: self)
			(super init: &rest)
			(Bclr 66)
			(self setScript: enterFence)
		)
		(theGame save: 1)
		(HandsOff)
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(!= prevRoomNum 640)
				(> (ego x?) 280)
				(ego mover?)
				(not script)
			)
			(if local16
				(HandsOff)
				(self setScript: getCross)
			else
				(self setScript: promptUser)
			)
		)
	)
	
	(method (dispose)
		(ego z: 0)
		(walkHandler delete: self)
		(UnLoad 128 8)
		(UnLoad 128 2)
		(UnLoad 128 11)
		(UnLoad 128 632)
		(UnLoad 128 29)
		(UnLoad 128 30)
		(LoadMany 0 36 964 57)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(if local16
					(HandsOff)
					(self setScript: getCross)
				else
					(self setScript: promptUser)
				)
			)
			(80
				(Bset 66)
				(super doVerb: 80)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance promptUser of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= register 0)
				(if (localproc_2563)
					(= register 1)
					(messager say: 1 6 14)
				)
				(ego x: 302 setMotion: 0)
				(= cycles 5)
			)
			(1
				(if register
					(curRoom newRoom: 170)
				else
					(self dispose:)
				)
			)
		)
	)
)

(instance throwFood of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 9
					y: (- (ego y?) 3)
					setLoop: 1
					setCycle: End self
				)
			)
			(1
				(ego y: (+ (ego y?) 3) normalize: z: 30)
				(= cycles 5)
			)
			(2 (messager say: 2 6 8 0 self))
			(3
				(leopard
					moveSpeed: 3
					cycleSpeed: 3
					setMotion: MoveTo 197 142 self
				)
			)
			(4
				(leopard view: 632)
				(if (== (leopard loop?) 3)
					(leopard setLoop: 3)
				else
					(leopard setLoop: 2)
				)
				(leopard
					cycleSpeed: 10
					setCycle: OccasionalCycle self 1 10 60
					setMotion: 0
				)
				(= cycles 1)
			)
			(5 (HandsOn) (self dispose:))
		)
	)
)

(instance ropeThrow of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 8
					x: 305
					y: 99
					drop: 6
					setLoop: 1
					setCel: 0
					setCycle: CT 5 1 self
				)
			)
			(1
				(ego setCycle: CT 6 1)
				(rope init: cycleSpeed: 3 setCycle: End self)
			)
			(2
				(rope stopUpd:)
				(ego view: 0 x: 305 y: 103 z: 30 setScale:)
				(= local16 1)
				(if (Btst 66)
					(curRoom setScript: getCross)
				else
					(self setScript: leopardApproach self)
				)
			)
			(3
				(leopard
					view: 632
					cycleSpeed: 6
					setLoop: 1
					setPri: 1
					setCycle: End self
				)
			)
			(4
				(leopard
					view: 632
					setLoop: 0
					y: 116
					moveSpeed: 4
					cycleSpeed: 4
					setCycle: JumpCycler 3 6
					setMotion: JumpOver 265 140 50 self
				)
			)
			(5
				(HandsOn)
				(leopard
					view: 631
					y: 156
					moveSpeed: 6
					cycleSpeed: 6
					setLoop: -1
					setPri: -1
					setCycle: Walk
					setMotion: xDPath 259 182 281 161 240 121 179 149
				)
				(self dispose:)
			)
		)
	)
)

(instance getCross of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					view: 12
					x: 295
					setPri: 15
					origStep: 257
					setLoop: 1
					setCel: 0
					setCycle: Walk
					setMotion: MoveTo (+ (ego x?) local24) (ego y?) self
				)
			)
			(1
				(cond 
					((and (== arcadeDifficulty 1) (Btst 66))
						(if (== (ego trySkill: 2 175) -1)
							(= local21 2)
							(LoadMany 128 2 11)
							(leopard setScript: leopardApproach 0 1)
							(curRoom setScript: unbalance 0 0)
						else
							(curRoom setScript: ropeCrossing 0 1)
						)
					)
					(
					(or (== arcadeDifficulty 2) (== arcadeDifficulty 3)) (curRoom setScript: mediumCross))
					(else
						(LoadMany 128 2 11 6)
						(leopard setScript: leopardApproach 0 1)
						(curRoom setScript: unbalance 0 1)
					)
				)
				(self dispose:)
			)
		)
	)
)

(instance mediumCross of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (== arcadeDifficulty 3) (= local20 180))
				(cond 
					((== prevRoomNum 640) (ego code: backCheck))
					((== (ego trySkill: 2 175) -1) (ego code: stepCheck))
					(else (ego code: crossCheck))
				)
				(if (not (Btst 66))
					(self setScript: leopardApproach self 0)
				else
					(= cycles 1)
				)
			)
			(1
				(bridgeControls init: show: dispose:)
			)
			(2 (self dispose:))
		)
	)
)

(instance ropeCrossing of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if register
					(ego setMotion: MoveTo 150 103 self)
				else
					(ego code: 0)
					(= cycles 1)
				)
			)
			(1
				(ego
					view: 2
					setLoop: -1
					setPri: -1
					yStep: 1
					xStep: 2
					setCycle: Walk
					setMotion: DPath 121 98 121 97 self
				)
			)
			(2 (messager say: 1 6 7 0 self))
			(3 (curRoom newRoom: 640))
		)
	)
)

(instance leopardApproach of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(leopard
					view: 631
					cycleSpeed: 2
					moveSpeed: 2
					setCycle: Walk
					setLoop: -1
				)
				(cond 
					((== prevRoomNum 640)
						(if (> (leopardAttack state?) 0)
							(leopard setMotion: MoveTo 200 100 self)
						else
							(leopard setMotion: MoveTo 175 103 self)
						)
					)
					((> (leopardAttack state?) 0) (leopard setMotion: MoveTo 220 132 self))
					(else (leopard setMotion: MoveTo 225 132 self))
				)
			)
			(1
				(leopard setLoop: 0)
				(unbalance cue:)
				(= cycles 3)
			)
			(2
				(if local28 (localproc_081c))
				(if register (client setScript: leopardStart))
				(self dispose:)
			)
		)
	)
)

(instance leopardStart of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(leopard view: 632 setLoop: 1 setCycle: CT 3 1 self)
			)
			(1 0)
			(2
				(leopard setCycle: CT 5 1 self)
			)
			(3
				(leopard
					y: 116
					setLoop: 0
					cycleSpeed: 4
					moveSpeed: 4
					setCycle: JumpCycler 3 6
					setMotion: JumpOver 265 140 60 self
				)
			)
			(4 (client setScript: getEgo))
		)
	)
)

(instance getEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(leopard
					view: 631
					cel: 0
					y: (+ (leopard y?) 6)
					setLoop: 6
					setCycle: CT 1 1 self
				)
			)
			(1
				(leopard
					setCycle: Walk
					setLoop: 1
					setMotion: MoveTo (+ (ego x?) 5) 152 self
				)
			)
			(2
				((curRoom script?) cue:)
				(self dispose:)
			)
		)
	)
)

(instance unbalance of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo (- (ego x?) 30) (ego y?) self)
			)
			(1 0)
			(2
				(if (leopard script?) (leopardStart cue:))
				(ego setMotion: MoveTo (- (ego x?) 5) (ego y?) self)
			)
			(3
				(ego
					view: 11
					setLoop: 3
					setCycle: End self
					setMotion: MoveTo (- (ego x?) 3) (- (ego y?) 2)
				)
			)
			(4
				(curRoom setScript: egoFell 0 0)
			)
		)
	)
)

(instance egoFell of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if
				(or (== arcadeDifficulty 3) (== arcadeDifficulty 2))
					(bridgeControls
						state: (& (bridgeControls state?) $ffdf)
					)
				)
				(ego
					code: 0
					view: 6
					loop: local22
					cel: 0
					setPri: -1
					moveSpeed: 5
					setCycle: 0
					setMotion: JumpX -55 self
				)
			)
			(1
				(ego setCycle: End self)
				(sFx number: 920 play: 127)
				(ShakeScreen 1)
			)
			(2
				(HandsOff)
				(if register (self cue:))
			)
			(3
				(switch local21
					(0 (EgoDead 9 630 633 End))
					(1 (EgoDead 10 630 633 End))
					(2 (EgoDead 11 630 633 End))
					(else  (EgoDead 6 630 633 End))
				)
			)
		)
	)
)

(instance enterFence of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 5))
			(1 (messager say: 1 6 1 0 self))
			(2
				(ego show:)
				(leopard show:)
				(leopardLady show:)
				(= cycles 2)
			)
			(3
				(leopard
					setMotion: xDPath 259 182 281 161 240 121 179 149
				)
				(messager say: 1 6 2 0 self)
			)
			(4
				(ego setPri: -1 setCycle: Beg self)
			)
			(5 (HandsOn) (self dispose:))
		)
	)
)

(instance backFence of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo (ego x?) (+ (ego y?) 6) self)
				(leopard
					setMotion: xDPath 259 182 281 161 240 121 179 149
				)
			)
			(1 (messager say: 1 6 4 0 self))
			(2
				(HandsOff)
				(ego setMotion: DPath 119 99 154 101 self)
			)
			(3
				(ego
					view: 12
					y: 103
					setCycle: Walk
					setMotion: MoveTo (+ (ego x?) 15) 103 self
				)
			)
			(4
				(if
				(or (== arcadeDifficulty 2) (== arcadeDifficulty 3))
					(ego
						view: 12
						setLoop: 0
						setMotion: MoveTo (- (ego x?) 3) (ego y?)
					)
					(client setScript: mediumCross)
				else
					(self cue:)
				)
			)
			(5
				(ego origStep: 257 setMotion: MoveTo 303 103 self)
			)
			(6
				(curRoom setScript: egoDown self)
			)
		)
	)
)

(instance egoDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(moveStraight dispose:)
				(ego view: 4 setLoop: 3 setCel: 0 code: 0)
				(= cycles 5)
			)
			(1
				(ego cycleSpeed: 10 setCycle: End self)
			)
			(2
				(rope setCycle: CT 0 -1 self)
			)
			(3
				(rope dispose:)
				(= cycles 5)
			)
			(4
				(if (ego has: 46)
					(curRoom setScript: trans)
				else
					(self cue:)
				)
			)
			(5
				(cast eachElementDo: #hide)
				(curRoom drawPic: 0)
				(= cycles 5)
			)
			(6 (= seconds 1))
			(7 (messager say: 2 6 5 0 self))
			(8 (curRoom newRoom: 420))
		)
	)
)

(instance trans of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cast eachElementDo: #hide)
				(curRoom drawPic: 0)
				(= cycles 5)
			)
			(1 (= seconds 1))
			(2
				(messager say: 2 6 26 0 self)
			)
			(3
				(++ Day)
				(= Clock 3000)
				(curRoom newRoom: 620)
			)
		)
	)
)

(instance moveForward of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_07dd)
				(ego
					setMotion: MoveTo (+ (ego x?) local24) (ego y?) self
				)
			)
			(1
				(= local19 (+ local19 6))
				(if (not (leopard script?))
					(cond 
						((< local25 2)
							(cond 
								((localproc_0864)
									(++ local25)
									(localproc_081c)
									(leopard setScript: leopardAttack)
								)
								((and (not register) (< (ego x?) 258)) (leopard setScript: leopardChase))
							)
						)
						((not local14)
							(leopard
								moveSpeed: 6
								cycleSpeed: 6
								setCycle: Walk
								setMotion: MoveTo 259 182 leopard
							)
							(= local14 1)
						)
					)
				)
				(= cycles 1)
			)
			(2
				(localproc_081c)
				(self dispose:)
			)
		)
	)
)

(instance leopardChase of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== prevRoomNum 640)
					(leopard
						setCycle: Walk
						setMotion: MoveTo (+ (leopard x?) 3) (+ (leopard y?) 2) self
					)
				else
					(leopard
						setCycle: Backward
						setMotion: MoveTo (- (leopard x?) 3) (- (leopard y?) 2) self
					)
				)
			)
			(1
				(leopard setCycle: 0)
				(self dispose:)
			)
		)
	)
)

(instance moveStraight of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_07dd)
				(ego
					setMotion: MoveTo (+ (ego x?) local24) (ego y?) self
				)
			)
			(1
				(localproc_081c)
				(self dispose:)
			)
		)
	)
)

(instance leopardAttack of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(leopard view: 632 setLoop: 1 setCycle: End self)
				(sFx number: 909 play:)
			)
			(1
				(= local12 0)
				(if (== arcadeDifficulty 2)
					(= seconds 2)
				else
					(self cue:)
				)
			)
			(2
				(= local12 1)
				(= seconds 0)
				(leopard
					y: (- (leopard y?) 6)
					setLoop: 0
					cycleSpeed: 4
					moveSpeed: 4
					setCycle: JumpCycler 3 6
					setMotion: Attack (+ (ego x?) 13) 142 68 self
				)
			)
			(3
				(if register
					(client setScript: getEgo)
				else
					(if (ego script?) ((ego script?) cue:))
					(self cue:)
				)
			)
			(4
				(leopard
					view: 631
					y: (+ (leopard y?) 6)
					cel: 0
					setPri: -1
					setLoop: 6
					setCycle: End self
				)
			)
			(5
				(leopard setScript: leopardApproach 0 0)
				(self dispose:)
			)
		)
	)
)

(instance assistDuck of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_07dd)
				(if (== (leopardAttack state?) 1) (leopardAttack cue:))
				(if (== (ego trySkill: 2 (+ local20 20)) -1)
					(= local13 1)
					(= local21 0)
				else
					(= local20 (+ local20 5))
					(= local13 0)
				)
				(ego view: 29 cel: 0 loop: local22 setCycle: CT 3 1 self)
			)
			(1 0)
			(2
				(ego cycleSpeed: 12 setCycle: CT 9 1 self)
			)
			(3
				(ego
					view: 12
					cycleSpeed: 6
					setLoop: local22
					setCycle: Walk
				)
				(self dispose:)
			)
		)
	)
)

(instance duckOnly of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_07dd)
				(ego view: 29 cel: 0 loop: local22 setCycle: End self)
			)
			(1
				(ego
					view: 12
					cycleSpeed: 6
					setLoop: local22
					origStep: 257
					setCycle: Walk
				)
				(localproc_081c)
				(self dispose:)
			)
		)
	)
)

(instance duckDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_07dd)
				(if (== (ego trySkill: 2 (+ local20 20)) -1)
					(= local13 1)
					(= local21 0)
				else
					(= local20 (+ local20 5))
					(= local13 0)
				)
				(ego view: 29 cel: 0 loop: local22 setCycle: CT 3 1 self)
			)
			(1 0)
			(2
				(ego cycleSpeed: 12 setCycle: CT 9 1 self)
			)
			(3
				(ego
					view: 12
					cycleSpeed: 6
					setLoop: local22
					origStep: 257
					setCycle: Walk
				)
				(self dispose:)
			)
		)
	)
)

(instance assistJump of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_07dd)
				(if (== (leopardAttack state?) 1) (leopardAttack cue:))
				(= cycles 1)
			)
			(1 (= cycles 10))
			(2
				(if (== (ego trySkill: 2 (+ local20 20)) -1)
					(= local13 1)
					(= local21 0)
				else
					(= local20 (+ local20 5))
					(= local13 0)
				)
				(globalSound number: 250 setLoop: 1 play:)
				(ego
					view: 30
					cel: 0
					cycleSpeed: 5
					moveSpeed: 5
					setLoop: local22
					setCycle: JumpCycler 1 5
					setMotion: JumpX 53 self
				)
			)
			(3
				(ego
					view: 12
					moveSpeed: 6
					cycleSpeed: 6
					setLoop: local22
					setCycle: Walk
				)
				(self dispose:)
			)
		)
	)
)

(instance jumpUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_07dd)
				(if (== (ego trySkill: 2 (+ local20 20)) -1)
					(= local13 1)
					(= local21 0)
				else
					(= local20 (+ local20 5))
					(= local13 0)
				)
				(globalSound number: 250 setLoop: 1 play:)
				(ego
					view: 30
					cel: 0
					cycleSpeed: 5
					moveSpeed: 5
					setLoop: local22
					setCycle: JumpCycler 1 5
					setMotion: JumpX 53 self
				)
			)
			(1
				(ego
					view: 12
					moveSpeed: 6
					cycleSpeed: 6
					setLoop: local22
					setCycle: Walk
				)
				(localproc_081c)
				(self dispose:)
			)
		)
	)
)

(instance preCrossing of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 151 (ego y?) self)
			)
			(1
				(curRoom setScript: ropeCrossing 0 0)
			)
		)
	)
)

(instance crossCheck of Code
	(properties)
	
	(method (doit)
		(if (< (ego x?) 166)
			((curRoom script?) register: 0)
			(bridgeControls
				state: (& (bridgeControls state?) $ffdf)
			)
			(HandsOff)
			(ego solvePuzzle: 301 8 4)
			(moveForward dispose:)
			(leopardAttack dispose:)
			(if (and (not local14) (not (Btst 66)))
				(leopard setCycle: Walk)
				(leopard cue:)
				(leopard cue:)
			)
			(curRoom setScript: preCrossing)
		)
	)
)

(instance backCheck of Code
	(properties)
	
	(method (doit)
		(if (> (ego x?) 302)
			(bridgeControls
				state: (& (bridgeControls state?) $ffdf)
			)
			(moveForward dispose:)
			(leopardAttack dispose:)
			(curRoom setScript: egoDown)
		)
	)
)

(instance stepCheck of Code
	(properties)
	
	(method (doit)
		(if (< (ego x?) 270)
			(moveForward dispose:)
			(leopardAttack dispose:)
			(= local21 2)
			(curRoom setScript: egoFell 0 1)
		)
	)
)

(instance leopard of Actor
	(properties
		x 259
		y 182
		noun 7
		view 631
		loop 1
		cel 7
		signal $4000
	)
	
	(method (init)
		(super init: &rest)
		(self yStep: 1 setCycle: Walk hide:)
	)
	
	(method (doVerb theVerb &tmp temp0)
		(cond 
			((OneOf theVerb 24 28 27)
				(= temp0
					(switch theVerb
						(28 18)
						(27 17)
						(24 14)
					))
				(if (not (Btst 66))
					(Bset 66)
					(ego drop: temp0 1 solvePuzzle: 300 5 4 addHonor: 20)
					(curRoom setScript: throwFood)
				else
					(ego drop: temp0 1)
					(messager say: 11 6 27)
				)
			)
			((OneOf theVerb 20 33 16) (ego addHonor: -30) (messager say: 1 6 12))
			(else (super doVerb: theVerb))
		)
	)
	
	(method (cue)
		(leopard
			setLoop: -1
			setMotion: xDPath 259 182 281 161 240 121 179 149
		)
	)
	
	(method (setHeading h)
		(if (not (& signal $0800))
			(= h (+ h 45))
			(super setHeading: h &rest)
			(= loop
				(switch loop
					(0 2)
					(1 1)
					(2 0)
					(3 3)
				))
		)
	)
)

(instance leopardLady of View
	(properties
		x 292
		y 119
		z 30
		noun 1
		view 974
		loop 5
		priority 1
		signal $0010
	)
)

(instance theHut of Feature
	(properties
		x 126
		y 27
		noun 3
		onMeCheck $0002
	)
	
	(method (doVerb theVerb)
		(if
			(and
				(== theVerb 16)
				((inventory at: 6) state?)
				(not local15)
			)
			(= local15 1)
			(ego solvePuzzle: 299 5 4)
			(curRoom setScript: ropeThrow)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance rope of Prop
	(properties
		x 310
		y 70
		view 630
		priority 10
		signal $4010
	)
)

(instance egoTell of Teller
	(properties)
)

(instance johariTell of Teller
	(properties)
)

(instance bridgeControls of GloryControls
	(properties)
	
	(method (init)
		(super init: &rest)
		(= local28 1)
		(= window bridgeWin)
		(User input: 1)
		(theGame setCursor: 999)
		(= icon1 iconJump)
		(= icon2 iconDrop)
		(= icon3 iconPush)
		(= icon4 iconPull)
		(= icon5 iconL)
		(= icon6 iconR)
		(= icon7 iconQuit)
		(self
			add: iconJump iconDrop iconPush iconPull iconL iconR iconQuit iconDummy
			eachElementDo: #lowlightColor -1
			eachElementDo: #highlightColor -1
			eachElementDo: #signal 129
		)
		(self disable: iconPush iconPull iconQuit)
		(if (== prevRoomNum 640)
			(self disable: iconL)
		else
			(self disable: iconR)
		)
		(SetCursor 121 43 182 127)
	)
	
	(method (dispose)
		(= local11 0)
		(super dispose:)
	)
	
	(method (show)
		(if local11
			(window open:)
			(self eachElementDo: #show)
		else
			(= local11 1)
			(super show:)
		)
	)
	
	(method (hide)
		(SetCursor -2)
		(super hide:)
	)
	
	(method (dispatchEvent event &tmp eventType eventMessage)
		(= gameTime (GetTime))
		(Animate (cast elements?) 1)
		(if doMotionCue
			(= doMotionCue 0)
			(cast eachElementDo: #motionCue)
		)
		(return
			(if (& (= eventType (event type?)) $0040)
				(= eventType (& eventType $ffbf))
				(switch (event message?)
					(JOY_UP (return 0))
					(JOY_DOWN (return 0))
					(else 
						(super dispatchEvent: event)
					)
				)
			else
				(= eventMessage (event message?))
				(if (== eventType evKEYBOARD)
					(cond 
						((== eventMessage KEY_8) (return 0))
						((== eventMessage KEY_2) (return 0))
						(else (super dispatchEvent: event))
					)
				else
					(super dispatchEvent: event)
				)
			)
		)
	)
)

(instance iconJump of IconI
	(properties
		view 470
		loop 1
		cel 0
		nsLeft 2
		nsTop 2
		maskView 470
		maskLoop 10
	)
	
	(method (select)
		(return
			(if (super select:)
				(if (not (if (ego script?) else (ego mover?)))
					(if (and (== arcadeDifficulty 2) (not local12))
						(ego setScript: assistJump)
					else
						(ego setScript: jumpUp)
					)
				)
				(return 1)
			else
				0
			)
		)
	)
	
	(method (highlight param1)
		(if (not (& signal $0020)) (return))
		(if param1
			(DrawCel view loop 2 nsLeft nsTop)
		else
			(DrawCel view loop 0 nsLeft nsTop)
		)
	)
	
	(method (onMe theObjOrX)
		(if
			(and
				(super onMe: theObjOrX)
				(not
					(IsItSkip
						view
						loop
						cel
						(- (theObjOrX y?) nsTop)
						(- (theObjOrX x?) nsLeft)
					)
				)
			)
			(self highlight: 1)
		else
			(self highlight: 0)
		)
	)
)

(instance iconR of IconI
	(properties
		view 470
		loop 9
		cel 0
		nsLeft 60
		nsTop 12
		maskView 470
		maskLoop 10
		maskCel 5
	)
	
	(method (select)
		(return
			(if
			(and (not (& signal $0004)) (super select: &rest))
				(cond 
					((Btst 66) (ego setScript: moveStraight))
					((not (if (ego script?) else (ego mover?))) (ego setScript: moveForward))
				)
				(return 1)
			else
				0
			)
		)
	)
	
	(method (highlight param1)
		(if param1
			(DrawCel view loop 2 nsLeft nsTop)
		else
			(DrawCel view loop 0 nsLeft nsTop)
		)
	)
	
	(method (onMe theObjOrX)
		(return
			(if (not (& signal $0004))
				(if
					(and
						(super onMe: theObjOrX)
						(not
							(IsItSkip
								view
								loop
								cel
								(- (theObjOrX y?) nsTop)
								(- (theObjOrX x?) nsLeft)
							)
						)
					)
					(self highlight: 1)
				else
					(self highlight: 0)
				)
				(return 1)
			else
				(return 0)
			)
		)
	)
)

(instance iconL of IconI
	(properties
		view 470
		loop 8
		cel 0
		nsLeft 3
		nsTop 12
		maskView 470
		maskLoop 10
		maskCel 4
	)
	
	(method (select)
		(return
			(if
			(and (not (& signal $0004)) (super select: &rest))
				(cond 
					((Btst 66) (ego setScript: moveStraight))
					((not (if (ego script?) else (ego mover?))) (ego setScript: moveForward))
				)
				(return 1)
			else
				0
			)
		)
	)
	
	(method (highlight param1)
		(if (not (& signal $0020)) (return))
		(if param1
			(DrawCel view loop 2 nsLeft nsTop)
		else
			(DrawCel view loop 0 nsLeft nsTop)
		)
	)
	
	(method (onMe theObjOrX)
		(return
			(if (not (& signal $0004))
				(if
					(and
						(super onMe: theObjOrX)
						(not
							(IsItSkip
								view
								loop
								cel
								(- (theObjOrX y?) nsTop)
								(- (theObjOrX x?) nsLeft)
							)
						)
					)
					(self highlight: 1)
				else
					(self highlight: 0)
				)
				(return 1)
			else
				(return 0)
			)
		)
	)
)

(instance iconPush of IconI
	(properties
		view 470
		loop 10
		cel 2
		nsLeft 2
		nsTop 34
		signal $0004
		maskView 470
		maskLoop 10
		maskCel 2
	)
	
	(method (onMe theObjOrX)
		(return
			(if
				(and
					(super onMe: theObjOrX)
					(not
						(IsItSkip
							view
							loop
							cel
							(- (theObjOrX y?) nsTop)
							(- (theObjOrX x?) nsLeft)
						)
					)
				)
				(return 1)
			else
				(return 0)
			)
		)
	)
)

(instance iconPull of IconI
	(properties
		view 470
		loop 10
		cel 3
		nsLeft 52
		nsTop 34
		signal $0004
		maskView 470
		maskLoop 10
		maskCel 3
	)
	
	(method (onMe theObjOrX)
		(return
			(if
				(and
					(super onMe: theObjOrX)
					(not
						(IsItSkip
							view
							loop
							cel
							(- (theObjOrX y?) nsTop)
							(- (theObjOrX x?) nsLeft)
						)
					)
				)
				(return 1)
			else
				(return 0)
			)
		)
	)
)

(instance iconDrop of IconI
	(properties
		view 470
		loop 2
		cel 0
		nsLeft 51
		nsTop 2
		maskView 470
		maskLoop 10
		maskCel 1
	)
	
	(method (select)
		(return
			(if (super select: &rest)
				(cond 
					((or (ego script?) (ego mover?)) (return 1))
					((or (Btst 66) local14) (ego setScript: duckOnly))
					((== arcadeDifficulty 2)
						(if (not local12)
							(ego setScript: assistDuck)
						else
							(ego setScript: duckOnly)
						)
					)
					((leopard script?) (ego setScript: duckDown))
					(else (ego setScript: duckOnly))
				)
				(return 1)
			else
				0
			)
		)
	)
	
	(method (highlight param1)
		(if param1
			(DrawCel view loop 2 nsLeft nsTop)
		else
			(DrawCel view loop 0 nsLeft nsTop)
		)
	)
	
	(method (onMe theObjOrX)
		(if
			(and
				(super onMe: theObjOrX)
				(not
					(IsItSkip
						view
						loop
						cel
						(- (theObjOrX y?) nsTop)
						(- (theObjOrX x?) nsLeft)
					)
				)
			)
			(self highlight: 1)
		else
			(self highlight: 0)
		)
	)
)

(instance iconQuit of IconI
	(properties
		view 470
		loop 7
		cel 0
		nsLeft 24
		nsTop 2
		signal $0004
		maskView 470
		maskLoop 7
		maskCel 2
	)
	
	(method (onMe theObjOrX)
		(return
			(if
				(and
					(super onMe: theObjOrX)
					(not
						(IsItSkip
							view
							loop
							cel
							(- (theObjOrX y?) nsTop)
							(- (theObjOrX x?) nsLeft)
						)
					)
				)
				(return 1)
			else
				(return 0)
			)
		)
	)
)

(instance iconDummy of IconI
	(properties)
	
	(method (show)
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance bridgeWin of Window
	(properties
		top 111
		left 45
		bottom 175
		right 125
		color 17
		back 17
	)
	
	(method (open)
		(super open: &rest)
		(DrawCel 470 0 0 0 0 -1)
	)
)

(instance skull of Feature
	(properties
		x 28
		y 112
		noun 6
		nsTop 81
		nsLeft 7
		nsBottom 143
		nsRight 50
	)
)

(instance doorway of Feature
	(properties
		x 119
		y 47
		noun 8
		nsTop 30
		nsLeft 108
		nsBottom 64
		nsRight 130
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 16)
			(theHut doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance treehouse of Feature
	(properties
		x 131
		y 38
		noun 9
		nsTop 11
		nsLeft 66
		nsBottom 65
		nsRight 197
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 16)
			(theHut doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance tree of Feature
	(properties
		x 132
		y 100
		noun 10
		nsTop 71
		nsLeft 83
		nsBottom 130
		nsRight 181
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 16)
			(theHut doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance sFx of Sound
	(properties)
)

(instance quest of GameControls
	(properties)
	
	(method (init)
		(theGame setCursor: 999)
		((= window (GloryWindow new:))
			top: 60
			left: 82
			bottom: 130
			right: 238
			priority: 15
			yourself:
		)
		(self add: titleIcon yesIcon noIcon)
		(super init: &rest)
	)
)

(instance titleIcon of IconI
	(properties
		view 935
		loop 2
		cel 0
		nsTop 0
		signal $0004
		maskView 361
		maskLoop 3
	)
	
	(method (show &tmp [temp0 50])
		(Message msgGET 630 2 6 3 1 @temp0)
		(Display @temp0 dsWIDTH 155 dsCOORD 5 3 dsCOLOR 17)
	)
)

(instance yesIcon of IconI
	(properties
		view 935
		loop 2
		cel 0
		nsTop 38
		signal $0101
		maskView 361
		maskLoop 3
	)
	
	(method (show)
		(= nsRight 80)
		(= nsBottom (+ nsTop 15))
		(DrawCel view loop cel nsLeft nsTop -1)
		(Display @local1 dsCOORD 20 (+ nsTop 3) dsCOLOR 17)
		(if (& signal $0004) (self mask:))
		(if (and pMouse (pMouse respondsTo: #stop))
			(pMouse stop:)
		)
	)
	
	(method (select)
		(= local0 1)
		(quest state: (& (quest state?) $ffdf))
	)
	
	(method (highlight param1 &tmp temp0)
		(if param1
			(DrawCel view loop 1 nsLeft nsTop -1)
			(= temp0 46)
		else
			(DrawCel view loop 0 nsLeft nsTop -1)
			(= temp0 17)
		)
		(Display @local1 dsCOORD 20 (+ nsTop 3) dsCOLOR temp0)
	)
)

(instance noIcon of IconI
	(properties
		view 935
		loop 2
		cel 0
		nsTop 53
		signal $0101
		maskView 361
		maskLoop 3
	)
	
	(method (show &tmp [temp0 15])
		(= nsRight 80)
		(= nsBottom (+ nsTop 15))
		(DrawCel view loop cel nsLeft nsTop -1)
		(Display @local6 dsCOORD 20 (+ nsTop 3) dsCOLOR 17)
		(if (& signal $0004) (self mask:))
		(if (and pMouse (pMouse respondsTo: #stop))
			(pMouse stop:)
		)
	)
	
	(method (select)
		(= local0 0)
		(quest state: (& (quest state?) $ffdf))
	)
	
	(method (highlight param1 &tmp temp0)
		(if param1
			(DrawCel view loop 1 nsLeft nsTop -1)
			(= temp0 46)
		else
			(DrawCel view loop 0 nsLeft nsTop -1)
			(= temp0 17)
		)
		(Display @local6 dsCOORD 20 (+ nsTop 3) dsCOLOR temp0)
	)
)
