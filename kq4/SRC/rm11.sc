;;; Sierra Script 1.0 - (do not remove this comment)
(script# 11)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room11 0
)

(local
	[treeArm 12]
	i
	grabbedLoop
	aEye1
	aEye2
	aEye3
	aEye4
	aEye5
	treeBlock
)
(instance walkingMusic of Sound
	(properties
		number 64
	)
)

(instance caughtMusic of Sound
	(properties
		number 65
	)
)

(instance Room11 of Room
	(properties
		picture 11
	)
	
	(method (init)
		(= north 5)
		(= south 17)
		(= east 12)
		(= west 10)
		(= horizon 86)
		(= isIndoors FALSE)
		(Load SOUND 65)
		(ego edgeHit: 0)
		(super init:)
		(if isNightTime
			(curRoom overlay: 111)
		)
		(self setRegions: SCARY_FOREST)
		(for ((= i 1)) (<= i 11) ((++ i))
			(= [treeArm i] (Prop new:))
		)
		([treeArm 1]
			view: 692
			loop: 0
			cel: 0
			posn: 145 76
			setPri: 4
			cycleSpeed: 1
			ignoreActors:
			init:
			stopUpd:
		)
		([treeArm 2]
			view: 692
			loop: 1
			cel: 0
			posn: 152 83
			setPri: 7
			cycleSpeed: 1
			ignoreActors:
			init:
			stopUpd:
		)
		([treeArm 3]
			view: 692
			loop: 2
			cel: 0
			posn: 167 83
			setPri: 7
			cycleSpeed: 1
			ignoreActors:
			init:
			stopUpd:
		)
		([treeArm 4]
			view: 692
			loop: 3
			cel: 0
			posn: 196 70
			setPri: 7
			cycleSpeed: 1
			ignoreActors:
			init:
			stopUpd:
		)
		([treeArm 5]
			view: 692
			loop: 4
			cel: 0
			posn: 213 66
			setPri: 7
			cycleSpeed: 1
			ignoreActors:
			init:
			stopUpd:
		)
		([treeArm 6]
			view: 692
			loop: 5
			cel: 0
			posn: 258 76
			setPri: 7
			cycleSpeed: 1
			ignoreActors:
			init:
			stopUpd:
		)
		([treeArm 7]
			view: 692
			loop: 6
			cel: 0
			posn: 305 70
			setPri: 7
			cycleSpeed: 1
			ignoreActors:
			init:
			stopUpd:
		)
		([treeArm 8]
			view: 693
			loop: 0
			cel: 0
			posn: 81 105
			setPri: 11
			cycleSpeed: 1
			ignoreActors:
			init:
			stopUpd:
		)
		([treeArm 9]
			view: 693
			loop: 1
			cel: 0
			posn: 242 86
			setPri: 11
			cycleSpeed: 1
			ignoreActors:
			init:
			stopUpd:
		)
		([treeArm 10]
			view: 693
			loop: 2
			cel: 0
			posn: 256 85
			setPri: 11
			cycleSpeed: 1
			ignoreActors:
			init:
			stopUpd:
		)
		([treeArm 11]
			view: 694
			loop: 0
			cel: 0
			posn: 281 65
			setPri: 11
			cycleSpeed: 1
			ignoreActors:
			init:
			stopUpd:
		)
		(= aEye1 (Prop new:))
		(= aEye2 (Prop new:))
		(= aEye3 (Prop new:))
		(= aEye4 (Prop new:))
		(= aEye5 (Prop new:))
		(aEye1
			isExtra: TRUE
			view: 689
			loop: 2
			cel: 2
			posn: 105 62
			setPri: 10
			setCycle: Forward
			cycleSpeed: 2
			ignoreActors:
			init:
		)
		(aEye2
			isExtra: TRUE
			view: 689
			loop: 3
			cel: 3
			posn: 160 83
			setPri: 7
			setCycle: Forward
			cycleSpeed: 2
			ignoreActors:
			init:
		)
		(aEye3
			isExtra: TRUE
			view: 689
			loop: 4
			cel: 2
			posn: 249 83
			setPri: 10
			setCycle: Forward
			cycleSpeed: 2
			ignoreActors:
			init:
		)
		(aEye4
			isExtra: TRUE
			view: 689
			loop: 5
			cel: 3
			posn: 280 52
			setPri: 10
			setCycle: Forward
			cycleSpeed: 2
			ignoreActors:
			init:
		)
		(aEye5
			isExtra: TRUE
			view: 689
			loop: 6
			cel: 0
			posn: 314 48
			setPri: 10
			setCycle: Forward
			cycleSpeed: 2
			ignoreActors:
			init:
		)
		(switch prevRoomNum
			(10
				(if (<= (ego y?) horizon)
					(ego posn: 1 (+ horizon 2))
				else
					(ego posn: 1 (ego y?))
				)
			)
			(12
				(ego posn: 318 (ego y?))
			)
			(17
				(cond 
					((>= (ego x?) 277)
						(ego x: 277 y: 188)
					)
					((<= (ego x?) 103)
						(ego x: 103 y: 188)
					)
					(else
						(ego y: 188)
					)
				)
			)
			(5
				(ego posn: 148 (+ horizon 2))
			)
			(0
				(ego x: 318 y: 160)
			)
		)
		(walkingMusic play:)
		(ego view: 2 init:)
		((= treeBlock (Block new:))
			left: 212
			top: 103
			right: 224
			bottom: 106
		)
		(ego observeBlocks: treeBlock)
	)
	
	(method (doit)
		(super doit:)
		(if (and (not choppedScaryTree) (== (curRoom script?) 0))
			(cond 
				((& (ego onControl: 0) cBLUE)
					(= i 8)
					(= grabbedLoop 3)
					(curRoom setScript: grabbed)
				)
				((& (ego onControl: 0) cGREEN)
					(= i 1)
					(= grabbedLoop 1)
					(curRoom setScript: grabbed)
				)
				((& (ego onControl: 0) cCYAN)
					(= i 2)
					(curRoom setScript: grabbed)
					(= grabbedLoop 2)
					(curRoom setScript: grabbed)
				)
				((& (ego onControl: 0) cRED)
					(= i 3)
					(= grabbedLoop 1)
					(curRoom setScript: grabbed)
				)
				((& (ego onControl: 0) cMAGENTA)
					(= i 4)
					(= grabbedLoop 1)
					(curRoom setScript: grabbed)
				)
				((& (ego onControl: 0) cBROWN)
					(= i 5)
					(= grabbedLoop 1)
					(curRoom setScript: grabbed)
				)
				((& (ego onControl: 0) cLGREY)
					(= i 9)
					(= grabbedLoop 1)
					(curRoom setScript: grabbed)
				)
				((& (ego onControl: 0) cLMAGENTA)
					(= i 6)
					(= grabbedLoop 2)
					(curRoom setScript: grabbed)
				)
				((& (ego onControl: 0) cLGREEN)
					(= i 10)
					(= grabbedLoop 2)
					(curRoom setScript: grabbed)
				)
				((& (ego onControl: 0) cLRED)
					(= i 11)
					(= grabbedLoop 3)
					(curRoom setScript: grabbed)
				)
				((& (ego onControl: 0) cLCYAN)
					(= i 7)
					(= grabbedLoop 2)
					(curRoom setScript: grabbed)
				)
			)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if
				(and
					(== (event type?) saidEvent)
					(or
						(Said 'look/around')
						(Said 'look/room')
						(Said 'look[<around][/noword]')
					)
				)
				(Print 11 0)
			else
				FALSE
			)
		)
	)
)

(instance grabbed of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(walkingMusic dispose:)
				(caughtMusic play:)
				(HandsOff)
				([treeArm i] startUpd: setCycle: CycleTo grabbedLoop 1 self)
			)
			(1
				(ego dispose:)
				([treeArm i] setCycle: EndLoop self)
			)
			(2
				(switch (Random 1 6)
					(1 (Print 11 1))
					(2 (Print 11 2))
					(3 (Print 11 3))
					(4 (Print 11 4))
					(5 (Print 11 5))
					(6 (Print 11 6))
				)
				(= seconds 5)
			)
			(3
				(= dead TRUE)
			)
		)
	)
)
