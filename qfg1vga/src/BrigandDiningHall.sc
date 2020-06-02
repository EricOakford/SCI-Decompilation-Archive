;;; Sierra Script 1.0 - (do not remove this comment)
(script# 95)
(include game.sh) (include "95.shm")
(use Main)
(use Procs)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm95 0
)

(local
	blockedDoor
	frontDoorBarred
	candelabraPushed
	local3
	local4
	local5
	local6
	egoOnTable
	local8
	local9
	local10
	local11
	local12
	local13
	stoogesTrapped
	oldSortedFeatures
	local16
)
(instance rm95 of Room
	(properties
		noun 10
		picture 95
		style DISSOLVE
		horizon 80
	)
	
	(method (init)
		(LoadMany VIEW vBrigand 195 95 4)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						319 163
						278 133
						278 113
						291 113
						283 93
						279 85
						224 85
						224 104
						83 104
						84 88
						65 88
						64 0
						319 0
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						0 189
						0 0
						51 0
						51 86
						42 87
						18 103
						25 103
						23 114
						43 116
						60 112
						135 112
						110 165
						163 168
						162 158
						178 158
						161 118
						171 113
						245 113
						260 130
						292 189
					yourself:
				)
		)
		(super init:)
		(= oldSortedFeatures useSortedFeatures)
		(= useSortedFeatures FALSE)
		(self setFeatures: table ceiling floor1 floor2 floor3)
		(floor1 init:)
		(floor2 init:)
		(floor3 init:)
		(SolvePuzzle f95EnterDiningRoom 8)
		(NormalEgo)
		(ChangeGait MOVE_WALK 0)
		(chairs init: setPri: 6 addToPic:)
		(rDoor init: stopUpd:)
		(lDoor init: ignoreActors: 1 stopUpd:)
		(theCandelabra init: stopUpd:)
		(drunk
			init:
			setPri: 12
			addToPic:	;this makes it impossible to interact with him
			;stopUpd:	;Mac bug fix
		)
		(chandelier init: setPri: 5 stopUpd:)
		(rope init: setPri: 3)
		(flames init: setPri: 6 setCycle: Forward)
		(chairBlockDoor init: stopUpd:)
		(brig1 init: stopUpd: hide:)
		(moe init:)
		(curly init:)
		(larry init:)
		(bDoor init: setPri: 1 stopUpd:)
		(fDoor setCel: 3 init: setPri: 14 stopUpd:)
		(ego posn: 305 230 init: setScript: egoEntersSouth)
		(self setScript: controllingScript)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(= useSortedFeatures oldSortedFeatures)
		(super dispose:)
	)
)

(instance chairs of View
	(properties
		x 169
		y 77
		view 95
	)
	
	(method (init)
		(= nightPalette 195)
		(PalVary PALVARYTARGET 195)
		(kernel_128 95)
		(super init:)
	)
)

(instance drunk of View
	(properties
		x 202
		y 146
		noun N_DRUNK
		view 547
		loop 2
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_DO
				(ego setScript: handOnDrunk)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance rDoor of Prop
	(properties
		x 288
		y 85
		view 95
		loop 1
	)
)

(instance bDoor of Prop
	(properties
		x 44
		y 83
		noun N_BACKDOOR
		view 95
		loop 2
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_DO
				(cond 
					(stoogesTrapped
						(ego setScript: openBDoor)
					)
					((and frontDoorBarred (!= (brig1 script?) brigandsSouth))
						(curRoom setScript: 0)
						(ego setScript: exitTooSoon)
					)
					(frontDoorBarred
						(brig1 setScript: exitTooSoon)
					)
					(egoOnTable
						(HandsOff)
					)
					(else
						(super doVerb: theVerb theItem &rest)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance lDoor of Prop
	(properties
		x 14
		y 96
		view 95
		loop 3
	)
)

(instance fDoor of Prop
	(properties
		x 272
		y 189
		noun N_FRONTDOOR
		view 95
		loop 8
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_DO
				(if (not frontDoorBarred)
					(ego setScript: barFDoor)
				else
					(messager say: N_FRONTDOOR V_DO)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance theCandelabra of Prop
	(properties
		x 236
		y 86
		noun N_CANDELABRA
		view 95
		loop 4
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_DO
				(cond 
					(candelabraPushed
						(messager say: N_CANDELABRA V_DO C_CANTREACHIT)
					)
					(egoOnTable
						(HandsOff)
					)
					(else
						(ego setScript: pushCan)
						(if
							(and
								(not blockedDoor)
								(!= (brig1 script?) brigandsEast)
							)
							(curRoom setScript: 0)
							(brig1 setScript: candTooSoon)
						)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance chandelier of Prop
	(properties
		x 135
		y 18
		noun N_CHANDELIER
		view 95
		loop 5
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_DO
				(cond 
					(stoogesTrapped
						(messager say: N_CHANDELIER V_DO)
					)
					(egoOnTable
						(ego setScript: egoSwings)
					)
					(else
						(messager say: N_CHANDELIER V_DO C_CANTREACHIT)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance flames of Prop
	(properties
		x 72
		y 16
		view 95
		loop 6
		priority 2
		signal fixPriOn
	)
)

(instance rope of Prop
	(properties
		x 182
		y 15
		noun N_ROPE
		view 195
		loop 7
		priority 7
		signal fixPriOn
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_DO
				(cond 
					(stoogesTrapped
						(messager say: N_CHANDELIER V_DO)
					)
					(egoOnTable
						(ego setScript: egoSwings)
					)
					(else
						(messager say: N_ROPE V_DO)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance chairBlockDoor of Prop
	(properties
		x 308
		y 102
		view 95
		loop 7
		signal ignrAct
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_DO
				(cond 
					(blockedDoor
						(messager say: N_CHAIR V_DO)
					)
					((!= (brig1 script?) brigandsEast)
						(ego setScript: pushChair)
					)
					(else
						(super doVerb: theVerb theItem &rest)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance stars of Prop
	(properties
		view 295
		loop 2
	)
)

(instance brig1 of Actor
	(properties
		noun N_BRIGAND
		view vBrigand
	)
)

(instance brig2 of Actor
	(properties
		noun N_BRIGAND
		view vBrigand
	)
)

(instance brig3 of Actor
	(properties
		noun N_BRIGAND
		view vBrigand
	)
)

(instance curly of Actor
	(properties
		view 188
	)
)

(instance moe of Actor
	(properties
		view 187
	)
)

(instance larry of Actor
	(properties
		view 186
	)
)

(instance ceiling of Feature
	(properties
		x 165
		y 106
		z 99
		noun N_CEILING
		nsTop 1
		nsLeft 20
		nsBottom 14
		nsRight 311
	)
)

(instance table of Feature
	(properties
		x 159
		y 102
		z 15
		noun N_TABLE
		nsTop 75
		nsLeft 96
		nsBottom 99
		nsRight 223
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_DO
				(if stoogesTrapped
					(messager say: N_CHANDELIER V_DO)
				else
					(ego setScript: climb)
					(if
						(and
							(not blockedDoor)
							(!= (brig1 script?) brigandsEast)
						)
						(curRoom setScript: 0)
						(brig1 setScript: candTooSoon)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance floor1 of Feature
	(properties
		x 55
		y 95
		noun N_FLOOR
	)
	
	(method (init)
		((= onMeCheck (Polygon new:))
			type: PTotalAccess
			init:
				33 88
				84 88
				83 99
				69 110
				27 109
			yourself:
		)
		(super init:)
	)
	
	(method (dispose)
		(onMeCheck dispose:)
		(super dispose:)
	)
)

(instance floor2 of Feature
	(properties
		x 55
		y 95
		noun N_FLOOR
	)
	
	(method (init)
		((= onMeCheck (Polygon new:))
			type: PTotalAccess
			init:
				114 103
				184 103
				186 113
				169 115
				181 159
				107 159
				130 111
				111 111
			yourself:
		)
		(super init:)
	)
	
	(method (dispose)
		(onMeCheck dispose:)
		(super dispose:)
	)
)

(instance floor3 of Feature
	(properties
		x 55
		y 95
		noun N_FLOOR
	)
	
	(method (init)
		((= onMeCheck (Polygon new:))
			type: PTotalAccess
			init:
				228 95
				279 94
				293 112
				271 113
				288 129
				263 130
				243 109
				227 109
				226 101
			yourself:
		)
		(super init:)
	)
	
	(method (dispose)
		(onMeCheck dispose:)
		(super dispose:)
	)
)

(instance stoogeSound of Sound
	(properties
		number 120
	)
)

(instance egoEntersSouth of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 305 160 self)
			)
			(1
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance openBDoor of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(bDoor setCycle: EndLoop self)
			)
			(1
				(ego
					setPri: 2
					illegalBits: 0
					setMotion: MoveTo 60 86 self
				)
			)
			(2
				(NormalEgo)
				(curRoom newRoom: 96)
			)
		)
	)
)

(instance barFDoor of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 295 175 self)
			)
			(1
				(fDoor setPri: 15 ignoreActors: 1 setCycle: BegLoop self)
			)
			(2
				(messager say: N_ROOM NULL C_CLOSEDOOR)
				(fDoor addToPic:)
				(NormalEgo)
				(= frontDoorBarred TRUE)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance finalEntry of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(lDoor setCycle: EndLoop self)
			)
			(1
				(brig1
					show:
					setPri: 3
					posn: 31 90
					ignoreActors: TRUE
					setCycle: Walk
					setMotion: MoveTo 55 90 self
				)
				(brig2
					init:
					setPri: 3
					ignoreActors: TRUE
					posn: 20 90
					setCycle: Walk
					setMotion: MoveTo 46 92
				)
				(brig3
					init:
					setPri: 3
					ignoreActors: TRUE
					posn: 10 90
					setCycle: Walk
					setMotion: MoveTo 40 94
				)
			)
			(2
				(EgoDead C_DIE_EXIT_CAFETERIA_TOO_LATE C_DIE_CAFETERIA_EXIT_TOO_LATE_TITLE)
			)
		)
	)
)

(instance chairTooSoon of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= ticks 240)
			)
			(1
				(if (< (ego x?) 100) (ego setMotion: PolyPath 100 100))
				(= ticks 120)
			)
			(2
				(lDoor setCycle: EndLoop self)
			)
			(3
				(brig1
					show:
					setPri: 3
					posn: 31 90
					ignoreActors: 1
					setCycle: Walk
					setMotion: MoveTo 55 90 self
				)
				(brig2
					init:
					setPri: 3
					ignoreActors: 1
					posn: 20 90
					setCycle: Walk
					setMotion: MoveTo 46 92
				)
				(brig3
					init:
					setPri: 3
					ignoreActors: 1
					posn: 10 90
					setCycle: Walk
					setMotion: MoveTo 40 94
				)
			)
			(4
				(EgoDead C_DIE_BLOCK_DOOR_TOO_EARLY C_DIE_BLOCK_DOOR_TOO_EARLY_TITLE)
			)
		)
	)
)

(instance exitTooSoon of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (== (ego script?) exitTooSoon)
					(curRoom setScript: 0)
					(ego setMotion: PolyPath 60 100)
					(brig1 setScript: 0)
				)
				(if local16
					(brig1 setMotion: MoveTo (brig1 x?) 240 self)
					(brig2 setMotion: MoveTo (brig2 x?) 240)
					(brig3 setMotion: MoveTo (brig2 x?) 240)
				else
					(self cue:)
				)
			)
			(1 (lDoor setCycle: EndLoop self))
			(2
				(brig1
					show:
					setPri: 3
					posn: 31 90
					ignoreActors: 1
					setCycle: Walk
					setMotion: PolyPath 60 90 self
				)
				(brig2
					init:
					setPri: 3
					ignoreActors: 1
					posn: 20 90
					setCycle: Walk
					setMotion: MoveTo 46 92
				)
				(brig3
					init:
					setPri: 3
					ignoreActors: 1
					posn: 10 90
					setCycle: Walk
					setMotion: MoveTo 40 90
				)
			)
			(3
				(EgoDead C_DIE_EXIT_CAFETERIA_TOO_EARLY C_DIE_EXIT_CAFETERIA_TOO_EARLY_TITLE)
			)
		)
	)
)

(instance candTooSoon of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(rDoor setPri: 4 setCel: 1 addToPic: dispose:)
				(= ticks 60)
			)
			(1
				(if (== (moe script?) 0)
					(curRoom setScript: 0)
					(larry
						init:
						view: vBrigand
						loop: 1
						setPri: 5
						ignoreActors: 1
						setCycle: Walk
						posn: 300 90
						setMotion: MoveTo 295 90 self
					)
				else
					(self cue:)
				)
			)
			(2
				(EgoDead C_DIE_BLOCK_DOOR C_DIE_BLOCK_DOOR_TITLE)
			)
		)
	)
)

(instance climb of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 237 102 self)
			)
			(1
				(= local8 1)
				(ego
					view: 195
					setLoop: 0
					setCel: 0
					setPri: 7
					illegalBits: 0
					posn: 220 80
					setCycle: CycleTo 7 1 self
				)
			)
			(2
				(= egoOnTable TRUE)
				(if (== (brig1 script?) 0)
					(HandsOn)
					(theIconBar disable: 1)
				)
				(ego
					view: 4
					loop: 3
					setCel: 0
					posn: (- (ego x?) 11) (- (ego y?) 2)
				)
				(self dispose:)
			)
		)
	)
)

(instance egoSwings of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 195
					setLoop: 0
					setCel: 7
					posn: 220 80
					cycleSpeed: 3
					setCycle: EndLoop self
				)
				(rope setLoop: 3 setCel: 5 posn: 180 7)
			)
			(1
				(ego setLoop: 1 cel: 0 cycleSpeed: 3 setPri: 12)
				(rope setLoop: 4 setCel: 0 cycleSpeed: 3 setPri: 11)
			)
			(2
				(ego setCycle: EndLoop)
				(rope setCycle: EndLoop self)
				(lDoor setCycle: EndLoop)
			)
			(3
				(ego posn: 52 91 setLoop: 2 cel: 0 setCycle: CycleTo 2 1 self)
				(rope setLoop: 5 cel: 0 setCycle: EndLoop)
			)
			(4
				(lDoor setCycle: BegLoop)
				(= ticks 15)
			)
			(5
				(ego cel: 3)
				(= ticks 15)
			)
			(6
				(lDoor stopUpd:)
				(ego setCycle: EndLoop)
				(rope stopUpd: hide:)
				(HandsOn)
				(NormalEgo)
				(self dispose:)
			)
		)
	)
)

(instance pushChair of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= blockedDoor 2)
				(ChangeGait MOVE_WALK 0)
				(ego setMotion: PolyPath 278 106 self)
			)
			(1
				(messager say: N_ROOM NULL C_CHAIRBLOCK)
				(rDoor addToPic:)
				(ego setPri: 7 setMotion: MoveTo 307 108 self)
			)
			(2
				(chairBlockDoor posn: 303 98)
				(ego setLoop: 3 setMotion: MoveTo 298 100 self)
			)
			(3
				(chairBlockDoor posn: 297 94)
				(ego setMotion: MoveTo 293 96 self)
			)
			(4
				(chairBlockDoor posn: 291 92)
				(ego setMotion: MoveTo 288 94 self)
			)
			(5
				(chairBlockDoor
					setPri: 5
					setCel: 1
					posn: 285 92
					ignoreActors: FALSE
					stopUpd:
				)
				(ego setLoop: -1 setMotion: MoveTo 270 103 self)
			)
			(6
				(chairBlockDoor addToPic:)
				(= blockedDoor TRUE)
				(NormalEgo)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance pushCan of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (> (ego y?) 120)
					(ego setMotion: PolyPath 247 120 self)
				else
					(self cue:)
				)
			)
			(1
				(ego setPri: 6 setMotion: PolyPath 247 95 self)
			)
			(2
				(messager say: N_CANDELABRA V_DO NULL 0 self)
			)
			(3
				(ego setLoop: 1 setCel: 3)
				(= ticks 6)
			)
			(4
				(ego
					view: 547
					setLoop: 1
					setCel: 0
					setPri: 6
					posn: 246 91
				)
				(= ticks 6)
			)
			(5
				(ego setCel: 1 posn: 244 91)
				(= ticks 6)
			)
			(6
				(ego setCel: 2 posn: 242 91)
				(= ticks 6)
			)
			(7
				(ego setCel: 1 posn: 240 91)
				(theCandelabra setCel: 1)
				(= ticks 6)
			)
			(8
				(ego setCel: 0 posn: 238 91)
				(theCandelabra setCel: 2)
				(= ticks 6)
			)
			(9
				(ego view: 4 setLoop: 1 setCel: 0)
				(theCandelabra setCycle: EndLoop self)
			)
			(10
				(theCandelabra addToPic:)
				(NormalEgo)
				(= candelabraPushed TRUE)
				(if (!= (ego script?) handOnDrunk)
					(HandsOn)
					(self dispose:)
				)
			)
		)
	)
)

(instance brigandsSouth of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks 120))
			(1
				(if (< (ego y?) 133)
					(self changeState: 7)
				else
					(= ticks 90)
				)
			)
			(2
				(if (< (ego y?) 133)
					(self changeState: 7)
				else
					(= ticks 90)
				)
			)
			(3
				(if (< (ego y?) 133)
					(self changeState: 7)
				else
					(= ticks 90)
				)
			)
			(4
				(if (< (ego y?) 133)
					(self changeState: 7)
				else
					(= ticks 90)
				)
			)
			(5
				(if (< (ego y?) 133)
					(self changeState: 7)
				else
					(= ticks 90)
				)
			)
			(6
				(if (< (ego y?) 133)
					(self changeState: 7)
				else
					(= ticks 90)
				)
			)
			(7
				(= local16 1)
				(brig1
					show:
					posn: 185 232
					setPri: 15
					ignoreActors: TRUE
					setCycle: Walk
					setMotion: MoveTo 239 215 self
				)
			)
			(8
				(brig2
					init:
					posn: 350 200
					setPri: 15
					ignoreActors: TRUE
					setCycle: Walk
					setMotion: MoveTo 300 200
				)
				(brig3
					init:
					posn: 295 244
					setPri: 15
					ignoreActors: TRUE
					setCycle: Walk
					setMotion: MoveTo 295 208 self
				)
				(brig1 setMotion: MoveTo 286 200)
			)
			(9
				(brig1 loop: 3)
				(brig2 loop: 3)
				(= ticks 36)
			)
			(10
				(if frontDoorBarred
					(brig2 loop: 1)
					(brig1 loop: 0)
					(= ticks 120)
				else
					(brig1 setPri: 14 setMotion: MoveTo 304 165)
					(brig2 setPri: 14 setMotion: MoveTo 310 163)
					(brig3 setPri: 14 setMotion: MoveTo 307 161)
					(if (ego inRect: 50 108 230 124)
						(HandsOff)
						(ego loop: 0)
						(self changeState: 13)
					else
						(self changeState: 14)
					)
				)
			)
			(11
				(if blockedDoor
					(brig1 setMotion: MoveTo 260 250)
					(brig2 setMotion: MoveTo 304 250)
					(brig3 setMotion: MoveTo 240 250 self)
					(= local3 1)
				else
					(brig1 setMotion: MoveTo 340 204)
					(brig2 setMotion: MoveTo 340 204)
					(brig3 setMotion: MoveTo 307 250 self)
				)
			)
			(12
				(= local16 0)
				(brig1 stopUpd: hide:)
				(brig2 stopUpd: dispose:)
				(brig3 stopUpd: dispose:)
				(if local3
					(client setScript: chairTooSoon)
				else
					(= local3 1)
					(client setScript: 0)
				)
				(self dispose:)
			)
			(13
				(Face ego brig1)
				(brig1
					setPri: 13
					ignoreActors: 0
					setMotion: MoveTo 280 165
				)
				(brig2 ignoreActors: 0 setMotion: MoveTo 310 150)
				(= ticks 120)
			)
			(14
				(EgoDead C_DIE_BLOCK_DOOR C_DIE_BLOCK_DOOR_TITLE)
			)
		)
	)
)

(instance brigandsEast of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(rDoor setPri: 4 setCel: 1 addToPic: dispose:)
				(brig1
					show:
					loop: 1
					setPri: 5
					ignoreActors: TRUE
					setCycle: Walk
					posn: 330 90
					setMotion: MoveTo 295 90 self
				)
			)
			(1
				(if (< (ego distanceTo: brig1) 50)
					(self changeState: 5)
				else
					(brig1 setMotion: PolyPath 277 100 self)
				)
				(Face ego brig1)
			)
			(2
				(brig1 setMotion: PolyPath 270 113 self)
				(brig2
					setPri: 5
					loop: 1
					ignoreActors: TRUE
					setCycle: Walk
					posn: 330 90
					init:
					setMotion: MoveTo 295 90
				)
			)
			(3
				(if (< (ego distanceTo: brig1) 50)
					(self changeState: 5)
				else
					(brig2 setMotion: PolyPath 277 100 self)
				)
			)
			(4
				(if (ego inRect: 50 108 230 124)
					(ego loop: 0)
					(brig1 setPri: -1 setMotion: MoveTo 224 113)
					(brig2 setMotion: MoveTo 270 113 self)
				else
					(self changeState: 5)
				)
			)
			(5
				(EgoDead C_DIE_BLOCK_DOOR C_DIE_BLOCK_DOOR_TITLE)
			)
		)
	)
)

(instance brigandsWest of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= ticks (+ 60 (* 500 (- 3 howFast))))
			)
			(1
				(if egoOnTable
					(EgoDead C_DIE_STOOGES_CHANDELIER C_DIE_STOOGES_CHANDELIER_TITLE)
				else
					(self cue:)
				)
			)
			(2
				(lDoor setCel: 1)
				(moe
					setPri: 3
					posn: 31 90
					ignoreActors: TRUE
					setCycle: Walk
					setMotion: MoveTo 80 90
				)
				(curly
					setPri: 3
					ignoreActors: 1
					posn: 20 90
					setCycle: Walk
					setMotion: MoveTo 71 90
				)
				(larry
					setPri: 3
					ignoreActors: 1
					posn: 10 90
					setCycle: Walk
					setMotion: MoveTo 63 90 self
				)
				(if (< (ego x?) 200)
					(HandsOff)
					(= register 1)
					(if (< (ego x?) 100) (ego setMotion: PolyPath 120 103))
				)
			)
			(3
				(if (< (ego x?) 200)
					(HandsOff)
					(= register 1)
					(if (< (ego x?) 115)
						(ego setMotion: PolyPath 130 103)
					else
						(ego setMotion: 0 setCycle: 0)
					)
					(moe setMotion: MoveTo 110 100)
					(curly setMotion: MoveTo 100 90)
					(larry setMotion: MoveTo 90 90 self)
				else
					(moe setMotion: MoveTo 157 90)
					(curly setMotion: MoveTo 148 90)
					(larry setMotion: MoveTo 140 90 self)
				)
			)
			(4
				(if (< (ego x?) 200)
					(HandsOff)
					(= register 1)
					(if (< (ego x?) 115)
						(ego setMotion: PolyPath 130 103)
					else
						(ego setMotion: 0 setCycle: 0)
					)
					(curly setMotion: MoveTo 100 90)
					(larry setMotion: MoveTo 90 90 self)
				else
					(moe setMotion: MoveTo 194 90)
					(curly setMotion: MoveTo 185 90)
					(larry setMotion: MoveTo 177 90 self)
				)
			)
			(5
				(if register (EgoDead 29 30) else (self cue:))
			)
			(6
				(lDoor setCel: 0)
				(if candelabraPushed
					(client setScript: brigBlocked)
				else
					(EgoDead C_DIE_STOOGES_CHANDELIER C_DIE_STOOGES_CHANDELIER_TITLE)
				)
			)
		)
	)
)

(instance brigBlocked of Script
	
	(method (doit)
		(if
			(and
				(== (ego script?) 0)
				(not local8)
				(< (self state?) 8)
				(< (ego distanceTo: moe) 15)
			)
			(self changeState: 8)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(stoogeSound play:)
				(= ticks 20)
			)
			(1
				(stoogeSound play:)
				(= ticks 20)
			)
			(2
				(stoogeSound play:)
				(= ticks 20)
			)
			(3
				(if local8
					(self changeState: 4)
				else
					(messager say: 10 0 3)
					(moe setMotion: MoveTo 125 88 self)
					(curly setScript: curlyFollow)
					(larry setScript: larryFollow)
				)
			)
			(4
				(if local8
					(moe setMotion: MoveTo (ego x?) 88)
					(larry setMotion: MoveTo (ego x?) 88)
					(curly setMotion: MoveTo (ego x?) 88)
					(= ticks 60)
				else
					(moe setMotion: MoveTo 89 88 self)
				)
			)
			(5
				(if (and local8 (== (moe loop?) 0))
					(EgoDead C_DIE_STOOGES_TABLE C_DIE_STOOGES_TABLE_TITLE)
				else
					(moe setMotion: MoveTo 76 106 self)
				)
			)
			(6
				(moe setPri: 8 setMotion: MoveTo 130 115 self)
			)
			(7
				(moe setMotion: MoveTo 150 112 self)
			)
			(8
				(if local8
					(= local10 1)
					(self cue:)
				else
					(EgoDead C_DIE_NO_JUMP_TABLE C_DIE_NO_JUMP_TABLE_TITLE)
				)
			)
			(9
				(moe setMotion: MoveTo 170 110)
				(= local11 1)
			)
			(10
				(= ticks (* 5 (- 4 howFast)))
			)
			(11
				(moe view: 187 cel: 0 loop: 4 setCycle: EndLoop self)
			)
			(12
				(self dispose:)
			)
		)
	)
)

(instance curlyFollow of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(= ticks (* 30 (- 4 howFast)))
			)
			(1
				(curly setLoop: -1 setMotion: MoveTo 89 88 self)
			)
			(2
				(curly setMotion: MoveTo 76 116 self)
			)
			(3
				(curly setPri: -1 setMotion: MoveTo 100 115 self)
			)
			(4
				(curly setMotion: MoveTo 156 111)
				(= local12 1)
			)
			(5
				(= ticks (* 5 (- 4 howFast)))
			)
			(6
				(curly view: 188 cel: 0 loop: 4 setCycle: EndLoop self)
			)
			(7 (self dispose:))
		)
	)
)

(instance larryFollow of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= ticks (* 60 (- 4 howFast)))
			)
			(1
				(larry setMotion: MoveTo 82 88 self)
			)
			(2
				(larry setMotion: MoveTo 76 115 self)
			)
			(3
				(larry setPri: -1 setMotion: MoveTo 100 115 self)
			)
			(4
				(larry setMotion: MoveTo 142 109)
				(= local13 1)
			)
			(5
				(= ticks (* 5 (- 4 howFast)))
			)
			(6
				(larry view: 186 cel: 0 loop: 4 setCycle: EndLoop)
				(stars
					init:
					setPri: 10
					ignoreActors: 1
					posn: 156 69
					setCycle: Forward
				)
				(= ticks 60)
			)
			(7
				(moe cycleSpeed: 7 moveSpeed: 7 setCycle: BegLoop)
				(curly cycleSpeed: 7 moveSpeed: 7 setCycle: BegLoop)
				(larry cycleSpeed: 7 moveSpeed: 7 setCycle: BegLoop self)
			)
			(8
				(stars posn: 156 69)
				(moe cycleSpeed: 0 moveSpeed: 0 setLoop: 0 setCycle: Forward)
				(curly
					cycleSpeed: 0
					moveSpeed: 0
					setLoop: 2
					setCycle: Forward
				)
				(larry
					cycleSpeed: 0
					moveSpeed: 0
					setLoop: 1
					setCycle: Forward
				)
				(= ticks 60)
			)
			(9
				(stars hide:)
				(HandsOn)
				(flames dispose:)
				(rope view: 295 show: loop: 4 posn: 157 14 cel: 0)
				(chandelier
					view: 295
					setLoop: 3
					setCel: 0
					setPri: 9
					posn: 135 18
				)
				(= ticks 5)
			)
			(10
				(rope posn: 156 24 cel: 1)
				(chandelier setCel: 1 posn: 134 28)
				(= ticks 5)
			)
			(11
				(rope posn: 155 33 cel: 2)
				(chandelier setCel: 2 posn: 133 37)
				(= ticks 5)
			)
			(12
				(rope posn: 155 43 cel: 2)
				(chandelier setCel: 2 posn: 133 47)
				(= ticks 5)
			)
			(13
				(larry view: 295 setLoop: 1 setCel: 0)
				(curly dispose:)
				(moe dispose:)
				(rope cel: 3 posn: 154 57)
				(chandelier dispose:)
				(= ticks 5)
			)
			(14
				(rope dispose:)
				(larry ignoreActors: 0 setCel: 1)
				(stars show: posn: 156 85 setCycle: Forward)
				(= stoogesTrapped 1)
				(self dispose:)
			)
		)
	)
)

(instance candelabraTooSoon of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (< (moe x?) 32)
					(lDoor setCel: 1)
					(moe
						setPri: 3
						posn: 31 90
						ignoreActors: 1
						setCycle: Walk
						setMotion: MoveTo 65 90
					)
					(curly
						setPri: 3
						ignoreActors: 1
						posn: 20 90
						setCycle: Walk
						setMotion: MoveTo 56 90
					)
					(larry
						setPri: 3
						ignoreActors: 1
						posn: 10 90
						setCycle: Walk
						setMotion: MoveTo 46 90 self
					)
				else
					(= ticks 20)
				)
			)
			(1
				(stoogeSound play:)
				(= ticks 20)
			)
			(2
				(stoogeSound play:)
				(= ticks 20)
			)
			(3
				(stoogeSound play:)
				(= ticks 20)
			)
			(4
				(messager say: 10 0 3)
				(moe setMotion: MoveTo 80 88 self)
			)
			(5
				(moe setMotion: PolyPath 75 115 self)
				(curly setScript: curlyFollow)
			)
			(6
				(moe setPri: -1 setMotion: PolyPath 100 115 self)
			)
			(7 (EgoDead C_DIE_CHANDELIER_TOO_EARLY C_DIE_CHANDELIER_TOO_EARLY_TITLE))
		)
	)
)

(instance handOnDrunk of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if local13
					(self changeState: 4)
				else
					(ego setMotion: PolyPath 175 143 self)
				)
			)
			(1
				(messager say: N_ROOM NULL C_HANDONDRUNK)
				(self cue:)
			)
			(2 (= ticks 30))
			(3
				(cond 
					((== (brig1 script?) brigandsEast)
						(self changeState: 6)
					)
					((== (moe script?) brigandsWest)
						(self changeState: 6)
					)
					((== (brig1 script?) brigandsSouth)
						(self changeState: 2)
					)
					(else
						(self cue:)
					)
				)
			)
			(4
				(lDoor setCycle: EndLoop self)
			)
			(5
				(brig1
					show:
					setPri: 3
					posn: 31 90
					ignoreActors: TRUE
					setCycle: Walk
					setMotion: MoveTo 55 90 self
				)
				(brig2
					init:
					setPri: 3
					ignoreActors:
					posn: 20 90
					setCycle: Walk
					setMotion: MoveTo 46 92
				)
				(brig3
					init:
					setPri: 3
					ignoreActors:
					posn: 10 90
					setCycle: Walk
					setMotion: MoveTo 40 94
				)
			)
			(6 (= ticks 30))
			(7
				(EgoDead C_DIE_HAND_ON_DRUNK C_DIE_HAND_ON_DRUNK_TITLE)
			)
		)
	)
)

(instance controllingScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(++ register)
				(= ticks 60)
			)
			(1
				(cond 
					(stoogesTrapped
						(self changeState: 7)
					)
					((and local13 local11 local12)
						(self changeState: 6)
					)
					((or local13 local12 local11)
						(self changeState: 0)
					)
					(local5
						(self changeState: 5)
					)
					(local4
						(self changeState: 4)
					)
					(local3
						(self changeState: 3)
					)
					(else
						(self changeState: 2)
					)
				)
			)
			(2
				(cond 
					((== (brig1 script?) exitTooSoon) (rm95 setScript: 0))
					((== (ego script?) exitTooSoon) (rm95 setScript: 0))
					(
						(and
							(!= (brig1 script?) brigandsSouth)
							(or
								(> (ego x?) 180)
								(> (ego y?) 110)
								(>= register (+ 5 (* 3 (- 4 howFast))))
							)
						)
						(= register 1)
						(brig1 setScript: brigandsSouth)
						(self changeState: 0)
					)
					(
						(and
							(!= (brig1 script?) brigandsSouth)
							(!= (brig1 script?) exitTooSoon)
							(< (ego x?) 180)
							(< (ego y?) 110)
						)
						(brig1 setScript: exitTooSoon)
					)
					(else (self changeState: 0))
				)
			)
			(3
				(if
					(and
						(>= register (+ 29 (* 12 (- 3 howFast))))
						(not blockedDoor)
						(!= (ego script?) handOnDrunk)
						(not
							(OneOf
								(brig1 script?)
								brigandsSouth
								brigandsEast
								exitTooSoon
							)
						)
					)
					(brig1 setScript: brigandsEast)
					(= local4 1)
				)
				(if
					(and
						blockedDoor
						(!= (brig1 script?) brigandsSouth)
						(!= (brig1 script?) brigandsEast)
					)
					(= local4 (= register 1))
				)
				(self changeState: 0)
			)
			(4
				(cond 
					((== (brig1 script?) brigandsEast) (= register 1))
					(
						(and
							(!= (brig1 script?) chairTooSoon)
							(!= (brig1 script?) exitTooSoon)
							(or
								blockedDoor
								(>= register (+ 13 (* 8 (- 3 howFast))))
							)
						)
						(moe setScript: brigandsWest)
						(= local5 1)
					)
				)
				(self changeState: 0)
			)
			(5
				(if
					(and
						candelabraPushed
						(== (moe script?) brigandsWest)
						(< (moe x?) 125)
					)
					(if egoOnTable
						(EgoDead 23 24)
						(client setScript: 0)
						(self dispose:)
					else
						(moe setScript: candelabraTooSoon)
					)
				)
				(self changeState: 0)
			)
			(6
				(if
					(and
						(== (ego script?) egoSwings)
						(== (egoSwings state?) 1)
					)
					(egoSwings changeState: 2)
					(brigBlocked cue:)
					(larryFollow cue:)
					(curlyFollow cue:)
					(= local13 0)
					(= local12 0)
					(= local11 0)
					(= register 1)
					(self changeState: 0)
				else
					(EgoDead C_DIE_SWING_ROPE C_DIE_SWING_ROPE_TITLE)
				)
			)
			(7
				(if
					(and
						(>= register (+ 12 (* 5 (- 3 howFast))))
						(!= (ego script?) openBDoor)
						(!= (brig1 script?) finalEntry)
					)
					(brig1 setScript: finalEntry)
				)
				(self changeState: 0)
			)
		)
	)
)
