;;; Sierra Script 1.0 - (do not remove this comment)
(script# 12)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room12 0
)

(local
	[treeArm 13]
	grabbedLoop
	i
	aEye1
	aEye2
	aEye3
	aEye4
	aEye5
	treeBlock1
	treeBlock2
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

(instance Room12 of Room
	(properties
		picture 12
	)
	
	(method (init)
		(= north 6)
		(= south 18)
		(= west 11)
		(= horizon 80)
		(= isIndoors FALSE)
		(Load SOUND 65)
		(ego edgeHit: 0)
		(super init:)
		(if isNightTime
			(curRoom overlay: 112)
		)
		(= aEye1 (Prop new:))
		(= aEye2 (Prop new:))
		(= aEye3 (Prop new:))
		(= aEye4 (Prop new:))
		(= aEye5 (Prop new:))
		(aEye1
			isExtra: TRUE
			view: 688
			loop: 0
			cel: 3
			posn: 265 97
			setPri: 15
			setCycle: Forward
			cycleSpeed: 2
			ignoreActors:
			init:
		)
		(aEye2
			isExtra: TRUE
			view: 688
			loop: 1
			cel: 4
			posn: 12 59
			setPri: 8
			setCycle: Forward
			cycleSpeed: 2
			ignoreActors:
			init:
		)
		(aEye3
			isExtra: TRUE
			view: 688
			loop: 2
			cel: 1
			posn: 210 64
			setPri: 10
			setCycle: Forward
			cycleSpeed: 2
			ignoreActors:
			init:
		)
		(aEye4
			isExtra: TRUE
			view: 688
			loop: 3
			cel: 4
			posn: 154 86
			setPri: 7
			setCycle: Forward
			cycleSpeed: 2
			ignoreActors:
			init:
		)
		(aEye5
			isExtra: TRUE
			view: 688
			loop: 4
			cel: 4
			posn: 98 107
			setPri: 15
			setCycle: Forward
			cycleSpeed: 2
			ignoreActors:
			init:
		)
		(self setRegions: SCARY_FOREST MOUNTAIN)
		(for ((= i 1)) (< i 13) ((++ i))
			(= [treeArm i] (Prop new:))
		)
		([treeArm 1]
			view: 695
			loop: 0
			cel: 0
			posn: 29 53
			setPri: 2
			cycleSpeed: 1
			ignoreActors:
			init:
			stopUpd:
		)
		([treeArm 2]
			view: 695
			loop: 1
			cel: 0
			posn: 57 61
			setPri: 2
			cycleSpeed: 1
			ignoreActors:
			init:
			stopUpd:
		)
		([treeArm 3]
			view: 695
			loop: 2
			cel: 0
			posn: 77 60
			setPri: 7
			cycleSpeed: 1
			ignoreActors:
			init:
			stopUpd:
		)
		([treeArm 4]
			view: 695
			loop: 3
			cel: 0
			posn: 147 85
			setPri: 7
			cycleSpeed: 1
			ignoreActors:
			init:
			stopUpd:
		)
		([treeArm 5]
			view: 695
			loop: 4
			cel: 0
			posn: 161 87
			setPri: 7
			cycleSpeed: 1
			ignoreActors:
			init:
			stopUpd:
		)
		([treeArm 6]
			view: 692
			loop: 4
			cel: 0
			posn: 121 69
			setPri: 6
			cycleSpeed: 1
			ignoreActors:
			init:
			stopUpd:
		)
		([treeArm 7]
			view: 696
			loop: 0
			cel: 0
			posn: 206 75
			setPri: 7
			cycleSpeed: 1
			ignoreActors:
			init:
			stopUpd:
		)
		([treeArm 8]
			view: 696
			loop: 1
			cel: 0
			posn: 21 63
			setPri: 7
			cycleSpeed: 1
			ignoreActors:
			init:
			stopUpd:
		)
		([treeArm 9]
			view: 696
			loop: 2
			cel: 0
			posn: 98 124
			setPri: 7
			cycleSpeed: 1
			ignoreActors:
			init:
			stopUpd:
		)
		([treeArm 10]
			view: 694
			loop: 1
			cel: 0
			posn: 231 82
			setPri: 12
			cycleSpeed: 1
			ignoreActors:
			init:
			stopUpd:
		)
		([treeArm 11]
			view: 693
			loop: 2
			cel: 0
			posn: 6 85
			setPri: 12
			cycleSpeed: 1
			ignoreActors:
			init:
			stopUpd:
		)
		([treeArm 12]
			view: 690
			loop: 0
			cel: 0
			posn: 222 93
			setPri: 8
			cycleSpeed: 1
			ignoreActors:
			init:
			stopUpd:
		)
		(switch prevRoomNum
			(west
				(if (<= (ego y?) horizon)
					(ego posn: 1 (+ horizon (ego yStep?) 1))
				else
					(ego posn: 1 (ego y?))
				)
			)
			(north
				(if (> (ego x?) 211)
					(ego posn: 210 (+ horizon 2))
				else
					(ego posn: (ego x?) (+ horizon 2))
				)
			)
			(south
				(if (> (ego x?) 77)
					(ego posn: 189 188)
				else
					(ego posn: 32 188)
				)
			)
			(0 (ego x: 290 y: 160))
		)
		(walkingMusic play:)
		(ego init: view: 2)
		(= treeBlock1 (Block new:))
		(= treeBlock2 (Block new:))
		(treeBlock1 left: 194 top: 115 right: 230 bottom: 118)
		(treeBlock2 left: 170 top: 106 right: 183 bottom: 110)
		(ego observeBlocks: treeBlock1 treeBlock2)
	)
	
	(method (doit)
		(super doit:)
		(if (and (not choppedScaryTree) (== (curRoom script?) 0))
			(cond 
				((& (ego onControl: 0) cLGREY)
					(= grabbedLoop 1)
					(= i 2)
					(curRoom setScript: grabbed)
				)
				((& (ego onControl: 0) cLMAGENTA)
					(= grabbedLoop 1)
					(= i 3)
					(curRoom setScript: grabbed)	;EO: this was missing
				)
				((& (ego onControl: 0) cBROWN)
					(= grabbedLoop 1)
					(= i 6)
					(curRoom setScript: grabbed)
				)
				((& (ego onControl: 0) cMAGENTA)
					(= grabbedLoop 1)
					(= i 5)
					(curRoom setScript: grabbed)
				)
				((& (ego onControl: 0) cGREY)
					(= grabbedLoop 2)
					(= i 1)
					(curRoom setScript: grabbed)
				)
				((& (ego onControl: 0) cLRED)
					(= grabbedLoop 2)
					(= i 4)
					(curRoom setScript: grabbed)
				)
				((& (ego onControl: 0) cRED)
					(= grabbedLoop 2)
					(= i 7)
					(curRoom setScript: grabbed)
				)
				((& (ego onControl: 0) cLBLUE)
					(= grabbedLoop 2)
					(= i 8)
					(curRoom setScript: grabbed)
				)
				((& (ego onControl: 0) cLCYAN)
					(= grabbedLoop 2)
					(= i 9)
					(curRoom setScript: grabbed)
				)
				((& (ego onControl: 0) cGREEN)
					(= grabbedLoop 2)
					(= i 10)
					(curRoom setScript: grabbed)
				)
				((& (ego onControl: 0) cLGREEN)
					(= grabbedLoop 2)
					(= i 11)
					(curRoom setScript: grabbed)
				)
				((& (ego onControl: 0) cCYAN)
					(= grabbedLoop 3)
					(= i 12)
					(curRoom setScript: grabbed)
				)
			)
		)
	)
	
	(method (dispose)
		(if (IsObject treeBlock1)
			(treeBlock1 dispose:)
			(treeBlock2 dispose:)
		)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if
				(and
					(== (event type?) saidEvent)
					(Said 'look[<around,at][/forest,forest,room]')
				)
				(Print 12 0)
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
				([treeArm i]
					startUpd:
					setCycle: CycleTo grabbedLoop 1 self
				)
			)
			(1
				(ego dispose:)
				([treeArm i] setCycle: EndLoop self)
			)
			(2
				(switch (Random 1 6)
					(1 (Print 12 1))
					(2 (Print 12 2))
					(3 (Print 12 3))
					(4 (Print 12 4))
					(5 (Print 12 5))
					(6 (Print 12 6))
				)
				(= seconds 5)
			)
			(3
				(= dead TRUE)
			)
		)
	)
)
