;;; Sierra Script 1.0 - (do not remove this comment)
(script# 290)
(include sci.sh)
(use Main)
(use TellerIcon)
(use EgoDead)
(use OccasionalCycle)
(use Vendor)
(use GloryTalker)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm290 0
	salimTalker 1
)

(local
	local0 =  10
	local1
	local2
	local3
	local4
	local5 =  150
	local6 =  150
	theVendor
	local8
	[local9 14] = [0 -68 -69 -1 2 -70 79 74 75 76 78 77 -110 999]
	[local23 4]
	[local27 21] = [0 -49 -65 -54 -60 -66 -33 -35 -37 -39 -41 -43 -5 -29 -111 -30 85 -81 -57 -47 999]
	[local48 6] = [0 45 80 -44 46 999]
	[local54 3] = [0 -20 999]
	[local57 4] = [0 21 22 999]
	[local61 3] = [0 -112 999]
	[local64 3] = [0 -25 999]
	[local67 5] = [0 -24 26 -27 999]
	[local72 3] = [0 71 999]
	[local75 3] = [0 28 999]
	[local78 3] = [0 -34 999]
	[local81 3] = [0 103 999]
	[local84 3] = [0 36 999]
	[local87 3] = [0 38 999]
	[local90 3] = [0 40 999]
	[local93 3] = [0 42 999]
	[local96 4] = [0 50 51 999]
	[local100 3] = [0 67 999]
	[local103 4] = [0 55 56 999]
	[local107 3] = [0 -61 999]
	[local110 5] = [0 63 62 64 999]
	[local115 3] = [0 53 999]
	[local118 4] = [0 -58 59 999]
	[local122 3] = [0 -19 999]
	[local125 3] = [0 48 999]
	[local128 28]
	[local156 26] = [0 -49 -65 -54 -60 -61 -66 -33 -34 -35 -37 -39 -41 -43 -44 -20 -5 -29 -25 -24 -27 -111 -57 -58 -47 999]
)
(procedure (localproc_054c param1)
	(= global395 (| global395 param1))
)

(procedure (localproc_0556 param1)
	(= global395 (& global395 (~ param1)))
)

(procedure (localproc_0561 param1)
	(return (& global395 param1))
)

(procedure (localproc_0568 param1 &tmp temp0)
	(return (== ((= temp0 (inventory at: param1)) state?) curRoom))
)

(instance rm290 of Rm
	(properties
		noun 25
		picture 290
		vanishingY -100
	)
	
	(method (init)
		(cSound hold: 0 holdVal: 0 setVol: 127)
		(HandsOff)
		(if (localproc_0561 16384) (localproc_0556 16384))
		(cond 
			((not global395) (localproc_054c 1) (salim loop: 0 cel: 0) (Book show:))
			((localproc_0561 1)
				(localproc_0556 1)
				(localproc_054c 2)
				(salim loop: 2 cel: 0)
				(Book hide:)
			)
			((localproc_0561 2) (localproc_0556 2) (localproc_054c 4))
			((and (localproc_0561 4) (localproc_0561 4096)) (localproc_0556 4) (localproc_054c 8))
		)
		(ego
			init:
			x: 135
			y: 192
			normalize:
			setScale: Scaler 113 87 150 110
			noun: 2
		)
		(self setScript: sEnter)
		(super init:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						173
						170
						181
						170
						203
						158
						212
						141
						207
						141
						187
						143
						176
						144
						163
						144
						140
						144
						129
						147
						110
						147
						106
						139
						114
						129
						125
						121
						152
						124
						153
						118
						139
						117
						140
						114
						123
						114
						116
						121
						107
						127
						98
						133
						83
						148
						77
						165
						95
						171
						95
						189
						173
						189
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 123 148 150 146 173 146 171 157 162 160 155 161 131 160 125 156
					yourself:
				)
		)
		(salim init:)
		(salimVendor init:)
		(= [local23 0] @local9)
		(= [local128 0] @local27)
		(= [local128 1] @local96)
		(= [local128 2] @local100)
		(= [local128 3] @local103)
		(= [local128 4] @local107)
		(= [local128 5] @local110)
		(= [local128 6] @local115)
		(= [local128 7] @local78)
		(= [local128 8] @local81)
		(= [local128 9] @local84)
		(= [local128 10] @local87)
		(= [local128 11] @local90)
		(= [local128 12] @local93)
		(= [local128 13] @local48)
		(= [local128 14] @local54)
		(= [local128 15] @local57)
		(= [local128 16] @local61)
		(= [local128 17] @local64)
		(= [local128 18] @local67)
		(= [local128 19] @local72)
		(= [local128 20] @local75)
		(= [local128 21] @local67)
		(= [local128 22] @local118)
		(= [local128 23] @local122)
		(= [local128 24] @local125)
		(egoTell init: ego @local9 @local23)
		(salimTell init: salim @local27 @local128 @local156)
		(Cushion1 init: setScale: addToPic:)
		(Cushion2 init: setScale: approachVerbs: 4 stopUpd:)
		(Book init: setScale: stopUpd:)
		(pipe init:)
		(spiderplant init:)
		(pile-o-books init:)
		(up-left-blue-bottle init:)
		(up-left-plant init:)
		(low-left-blue-bottle init:)
		(left-mid-bottles init:)
		(table init:)
		(greenvase init:)
		(beadeddoor init:)
		(giant-red-pot init:)
		(roundpot init:)
		(low-hang-plat init:)
		(small-spider-plant init:)
		(rightpots init:)
		(plantshelf init:)
		(upper-shelf init:)
		(brownbottles init:)
		(morebottles init:)
		(bottles-over-door init:)
		(hookedbottle init:)
		(walkHandler addToFront: curRoom)
		(cond 
			((localproc_0568 30) (= global405 9999))
			((not global405) (= global405 4))
		)
	)
	
	(method (doit)
		(cond 
			(script 0)
			((and (ego mover?) (== (ego view?) 40))
				(if (IsObject (ego looper?)) ((ego looper?) dispose:))
				(ego setMotion: 0 setScript: stand)
			)
			((> (ego y?) 170) (HandsOff) (self setScript: sExit))
		)
		(super doit:)
	)
	
	(method (dispose)
		(walkHandler delete: curRoom)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3 (egoActions doVerb: 3))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance sExit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 135 185 self)
			)
			(1
				(ego setMotion: MoveTo 135 225 self)
			)
			(2 (curRoom newRoom: 270))
		)
	)
)

(instance sEnter of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 165 160 self)
			)
			(1
				(cond 
					((localproc_0561 1) (salim setLoop: 0) (Book show:) (= cycles 5))
					((localproc_0561 2) (salim setLoop: 2) (= cycles 5))
					((localproc_0561 4)
						(if (mod dispelPotionDay 2)
							(Book hide:)
							(salim setLoop: 0)
							(= cycles 5)
						else
							(Book hide:)
							(salim setLoop: 2)
							(= cycles 5)
						)
					)
					((localproc_0561 8) (Book show:) (salim setLoop: 0) (= cycles 5))
					(else (Book hide:) (salim setLoop: 2) (= cycles 5))
				)
			)
			(2
				(cond 
					((localproc_0561 1) (messager say: 1 6 10))
					((localproc_0561 2)
						(if (localproc_0561 256)
							(messager say: 1 6 2)
						else
							(messager say: 1 6 11)
						)
						(= dispelPotionDay 1)
					)
					((localproc_0561 4)
						(cond 
							((== dispelPotionDay 1) (messager say: 1 6 12) (++ dispelPotionDay))
							((== dispelPotionDay 2) (messager say: 1 6 13) (++ dispelPotionDay))
							((== dispelPotionDay 3) (messager say: 1 6 14) (++ dispelPotionDay))
							((== dispelPotionDay 4) (messager say: 1 6 15) (++ dispelPotionDay))
							(else (messager say: 1 6 11) (= dispelPotionDay 1))
						)
					)
					((localproc_0561 8)
						(if (== dispelPotionDay Day)
							(messager say: 1 6 18)
						else
							(messager say: 1 6 17)
							(= dispelPotionDay 0)
						)
					)
					((== dispelPotionDay 0) (messager say: 1 6 16) (++ dispelPotionDay))
					((== dispelPotionDay 1) (messager say: 1 6 11) (++ dispelPotionDay))
					((== dispelPotionDay 2) (messager say: 1 6 12) (++ dispelPotionDay))
					((== dispelPotionDay 3) (messager say: 1 6 13) (++ dispelPotionDay))
					((== dispelPotionDay 4) (messager say: 1 6 14) (++ dispelPotionDay))
					((== dispelPotionDay 5) (messager say: 1 6 14) (= dispelPotionDay 0))
				)
				(self cue:)
			)
			(3
				(salim setCycle: OccasionalCycle self 1 60 150)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance lookUp of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 20])
		(switch (= state newState)
			(0
				(HandsOff)
				(if (== (salim loop?) 3)
					(salim setCycle: Beg self)
				else
					(self cue:)
				)
			)
			(1
				(messager say: 1 6 32 0 self)
			)
			(2
				(Book hide:)
				(salim cycleSpeed: 5 loop: 1 setCycle: Fwd)
				(globalSound number: 928 setLoop: -1 play:)
				(= seconds 5)
			)
			(3
				(globalSound stop:)
				(messager say: 1 6 4 0 self)
			)
			(4
				(salim
					cycleSpeed: 18
					cel: 0
					loop: 2
					setCycle: OccasionalCycle self 1 60 150
				)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance tokeDeath of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(sounds eachElementDo: #fade 0 1 15 1)
				(DrawPic 0 -32761 TRUE)
				(cast eachElementDo: #dispose)
				(= cycles 5)
			)
			(1
				(messager say: 3 6 101 0 self)
			)
			(2 (EgoDead 97 0 294 Fwd 291))
		)
	)
)

(instance freakOut of Script
	(properties)
	
	(method (doit)
		(if local1
			(cond 
				((< local0 20) (++ local0) (Palette palANIMATE 1 71 1 73 235 1))
				((< local0 40) (++ local0) (Palette palANIMATE 1 71 1 73 235 -1))
				(else (= local0 0))
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (== local4 1)
					(messager say: 3 4 96 0 self)
				else
					(self cue:)
				)
			)
			(1
				(cSound pause: 1)
				(sFx number: 291 play:)
				(= local3 (Palette palSAVE))
				(= local1 1)
				(= ticks 270)
			)
			(2
				(cSound pause: 0)
				(= local1 0)
				(Palette palRESTORE local3)
				(switch local4
					(1
						(messager say: 3 4 98 0 self)
					)
					(2
						(messager say: 3 4 99 0 self)
					)
					(3 (self setScript: tokeDeath))
				)
			)
			(3 (HandsOn) (self dispose:))
		)
	)
)

(instance sit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 158 150 self)
			)
			(1
				(ego view: 40 cel: 0 setLoop: 2 setCycle: End self)
				(if (ego looper?)
					((ego looper?) dispose:)
					(ego looper: 0)
				)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance stand of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setCycle: Beg self)
			)
			(1
				(ego
					normalize: 6
					cel: 0
					setMotion: PolyPath (ego x?) (- (ego y?) 10) self
				)
			)
			(2
				(ego setMotion: PolyPath local5 local6)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance standUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: 2 5 1 0 self)
			)
			(1 (messager say: 1 6 1 0 self))
			(2
				(Book show:)
				(salim cel: 0 loop: 3 setCycle: End self)
			)
			(3 (HandsOn) (self dispose:))
		)
	)
)

(instance egoTell of Teller
	(properties)
	
	(method (showDialog)
		(super
			showDialog:
				-1
				(if
				(and (localproc_0561 8192) (not (localproc_0561 256)))
					(localproc_0561 3)
				else
					0
				)
				2
				(if
				(and (localproc_0561 256) (not (localproc_0561 16384)))
					(localproc_0561 3)
				else
					0
				)
				-70
				(not
					(if (localproc_0561 8192) else (localproc_0561 512))
				)
				79
				(localproc_0561 2048)
				74
				(ego has: 30)
				75
				(ego has: 35)
				76
				(ego has: 36)
				78
				(ego has: 38)
				77
				(ego has: 37)
		)
	)
	
	(method (doChild param1 &tmp temp0)
		(cond 
			((== param1 -1)
				(if (not (localproc_0561 256))
					(ego solvePuzzle: 246 10 addHonor: 40)
					(localproc_054c 256)
					(localproc_054c 16384)
					(salim setScript: standUp)
					(return 0)
				)
			)
			((== param1 -70) (if (not (localproc_0561 8192)) (localproc_054c 512)))
			((== param1 -68)
				(if (not (localproc_0561 64))
					(localproc_054c 64)
					(ego addHonor: 2)
				)
			)
			((== param1 -69)
				(if (not (localproc_0561 128))
					(localproc_054c 128)
					(ego addHonor: 2)
				)
			)
			((== param1 -110)
				(salimTell doVerb: ((inventory at: 0) message?))
				(return 0)
			)
		)
		(return 1)
	)
)

(instance salimTell of Teller
	(properties)
	
	(method (showDialog)
		(super
			showDialog:
				-33
				(localproc_0561 1)
				-35
				(localproc_0561 2)
				-37
				(localproc_0561 4)
				-39
				(localproc_0561 8)
				-41
				(localproc_0561 32)
				-5
				(if (localproc_0561 7)
					(not (localproc_0561 1024))
				else
					0
				)
				-24
				(not (localproc_0568 37))
				-27
				(not (localproc_0568 35))
				26
				(not (localproc_0568 38))
				-81
				(if (localproc_0561 512) (localproc_0561 3) else 0)
				-29
				(if (localproc_0561 3) (localproc_0561 1024) else 0)
				-111
				(if (and (localproc_0561 1024) (localproc_0561 4))
					(not (localproc_0561 4096))
				else
					0
				)
				-30
				(if
				(and (localproc_0561 4096) (not (localproc_0561 32)))
					(== dispelPotionDay Day)
				else
					0
				)
				85
				(if (localproc_0561 8) (!= dispelPotionDay Day) else 0)
				-44
				(not (localproc_0568 30))
				80
				(localproc_0568 30)
				-49
				(localproc_0561 1)
				-65
				(localproc_0561 2)
				-54
				(localproc_0561 4)
				-60
				(localproc_0561 8)
				-66
				(localproc_0561 32)
				-57
				(localproc_0561 3)
				-47
				(localproc_0561 1)
				-58
				(localproc_0561 1)
		)
	)
	
	(method (doChild param1 &tmp temp0)
		(return
			(cond 
				((== param1 -20)
					(if (not (Btst 83)) (Bset 83))
					(if (localproc_0561 1)
						(super doChild: param1)
					else
						(return 1)
					)
				)
				((== param1 -57)
					(if (localproc_0561 1)
						(super doChild: param1)
					else
						(return 1)
					)
				)
				((== param1 -19) (localproc_054c 8192) (return 1))
				((== param1 -81) (localproc_054c 8192) (return 1))
				((== param1 -112)
					(if (not (Btst 58))
						(localproc_054c 1024)
						(Bset 58)
						(salimTell stuffArray: @local27 0)
						(curRoom setScript: lookUp)
					)
					(return 0)
				)
				((== param1 -29)
					(if (localproc_0561 4096)
						(return 1)
					else
						(super doChild: param1)
					)
				)
				((== param1 -30) (return 1))
				((== param1 -44)
					(if (localproc_0561 32)
						(return 1)
					else
						(super doChild: param1)
					)
				)
				(else (super doChild: param1))
			)
		)
	)
	
	(method (doVerb theVerb &tmp [temp0 20] temp20)
		(return
			(cond 
				((== theVerb 59) (messager say: 1 59 31))
				((== theVerb 10)
					(if (not (salimVendor goods?))
						(if (< global405 0)
							(= temp20 0)
						else
							(= temp20 global405)
						)
						(if (not (localproc_0561 -32768))
							(if (localproc_0561 8)
								(if (== heroType 1)
									(= global471 3)
								else
									(= global471 2)
								)
							else
								(= global471 1)
							)
						)
						(salimVendor
							goods:
								((List new:)
									add:
										((Class_47_1 new: 29) price: 10 quantity: temp20)
										((Class_47_1 new: 27) price: 20 quantity: 9999)
										((Class_47_1 new: 28) price: 20 quantity: 9999)
										((Class_47_1 new: 30) price: 30 quantity: global471)
								)
						)
					)
					(salimVendor purchase:)
					(return 1)
				)
				((== theVerb 48)
					(if (Btst 58)
						(if (Btst 243)
							(messager say: 1 6 113)
							(return 1)
						else
							(ego drop: 37 1 solvePuzzle: 243 3)
							((inventory at: 37) state: curRoom)
							(messager say: 1 48 23)
							(cond 
								((and (localproc_0568 35) (localproc_0568 38))
									(localproc_0556 3)
									(localproc_054c 4)
									(localproc_054c 4096)
									(= dispelPotionDay Day)
									(salimTell stuffArray: @local27 0)
									(messager say: 1 6 82)
								)
								((== (salimTell curArray?) @local72) (salimTell stuffArray: @local67 0))
							)
							(return 1)
						)
					else
						(super doVerb: theVerb &rest)
					)
				)
				((== theVerb 46)
					(if (Btst 58)
						(if (Btst 242)
							(messager say: 1 6 113)
							(return 1)
						else
							(ego drop: 35 1 solvePuzzle: 242 3)
							((inventory at: 35) state: curRoom)
							(messager say: 1 46 23)
							(cond 
								((and (localproc_0568 37) (localproc_0568 38))
									(localproc_0556 3)
									(localproc_054c 4)
									(localproc_054c 4096)
									(= dispelPotionDay Day)
									(salimTell stuffArray: @local27 0)
									(messager say: 1 6 82)
								)
								((== (salimTell curArray?) @local75) (salimTell stuffArray: @local67 0))
							)
							(return 1)
						)
					else
						(super doVerb: theVerb &rest)
					)
				)
				((== theVerb 49)
					(if (Btst 58)
						(if (Btst 244)
							(messager say: 1 6 113)
							(return 1)
						else
							(ego drop: 38 1 solvePuzzle: 244 3)
							((inventory at: 38) state: curRoom)
							(messager say: 1 49 23)
							(if (and (localproc_0568 35) (localproc_0568 37))
								(localproc_054c 4096)
								(localproc_0556 3)
								(localproc_054c 4)
								(= dispelPotionDay Day)
								(messager say: 1 6 82)
								(salimTell stuffArray: @local27 0)
							)
							(return 1)
						)
					else
						(super doVerb: theVerb &rest)
					)
				)
				((== theVerb 41)
					(ego drop: 30 get: 12 3 solvePuzzle: 241 3)
					((inventory at: 30) state: curRoom)
					(messager say: 1 41 23)
					(if (not (Btst 241)) (ego addHonor: 10))
					(return 1)
				)
				(else (super doVerb: theVerb &rest))
			)
		)
	)
)

(instance egoActions of Actions
	(properties)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(3
					(cond 
						((curRoom script?) 0)
						((== (ego view?) 40)
							(= local5 ((User curEvent?) x?))
							(= local6 ((User curEvent?) y?))
							(curRoom setScript: stand)
							(return 1)
						)
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance Cushion1 of View
	(properties
		x 186
		y 136
		noun 32
		view 290
		signal $4010
		scaleX 132
		scaleY 132
	)
)

(instance Cushion2 of View
	(properties
		x 145
		y 153
		noun 31
		approachX 155
		approachY 160
		view 290
		priority 10
		signal $4010
		scaleX 155
		scaleY 155
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (!= (ego view?) 40)
					(curRoom setScript: sit)
				else
					(super doVerb: theVerb)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance Book of View
	(properties
		x 192
		y 133
		noun 15
		view 290
		loop 1
		priority 10
		signal $4010
		scaleX 132
		scaleY 132
	)
)

(instance salim of Prop
	(properties
		x 186
		y 134
		noun 1
		view 292
		cycleSpeed 18
	)
)

(instance salimTalker of GloryTalker
	(properties
		x 1
		view 293
		loop 1
		cel 1
		talkWidth 265
		back 57
		textX 25
		textY 95
		backColor 116
	)
	
	(method (init)
		(super init: salimBrow salimEyes salimMouth &rest)
	)
)

(instance salimEyes of Prop
	(properties
		nsTop 31
		nsLeft 39
		view 293
		loop 2
		priority 15
		signal $0010
	)
)

(instance salimBrow of Prop
	(properties
		x 50
		y 50
		nsTop 21
		nsLeft 31
		view 293
		loop 3
		priority 14
		signal $0010
	)
)

(instance salimMouth of Prop
	(properties
		nsTop 47
		nsLeft 42
		view 293
		priority 15
		signal $0010
	)
)

(instance salimVendor of Vendor
	(properties
		noun 26
	)
	
	(method (transact param1 param2)
		(= theVendor self)
		(switch param1
			(0
				(Buy param1 param2)
				(if (not ((goods at: param1) quantity?))
					(= global405 -1)
					(ego get: 12 param2)
					(messager say: 1 6 3 0 self)
				else
					(if (not (> global405 4))
						(= global405 (- global405 param2))
					)
					(ego get: 12 param2)
					(self cue:)
				)
			)
			(1
				(ego get: 13 param2)
				(Buy param1 param2)
				(self cue:)
			)
			(2
				(ego get: 11 param2)
				(Buy param1 param2)
				(self cue:)
			)
			(3
				(cond 
					((not (localproc_0561 1024))
						(localproc_054c 1024)
						(Bset 58)
						(salimTell stuffArray: @local27 0)
						(self setScript: lookUp self)
					)
					((not (localproc_0561 -32768))
						(cond 
							((not (localproc_0561 4096)) (messager say: 1 6 84 0 self))
							((== dispelPotionDay Day) (messager say: 1 6 114 0 self))
							(else
								(localproc_0556 8)
								(localproc_054c 32)
								(if (localproc_0561 256)
									(ego get: 16 global471 solvePuzzle: 245 6)
									(= global471 0)
									((goods at: param1) quantity: 0)
									(localproc_054c -32768)
									(messager say: 1 6 6 0 self)
								else
									(ego get: 16 param2 solvePuzzle: 245 6)
									(= global471 (- global471 param2))
									(Buy param1 param2)
									(localproc_054c -32768)
									(self cue:)
								)
							)
						)
					)
					(else
						(ego get: 16 param2)
						(= global471 (- global471 param2))
						(Buy param1 param2)
						(self cue:)
					)
				)
			)
		)
	)
	
	(method (doBargain param1)
		(switch param1
			(1
				(messager say: 1 6 87 0 self)
			)
			(2
				(messager say: 1 6 88 0 self)
			)
			(3
				(messager say: 1 6 90 0 self)
			)
			(4
				(messager say: 1 6 89 0 self)
			)
			(5
				(messager say: 1 6 91 0 self)
			)
			(6
				(messager say: 1 6 92 0 self)
			)
			(else  (self cue:))
		)
	)
)

(instance pipe of Feature
	(properties
		x 143
		y 120
		noun 3
		nsTop 100
		nsLeft 130
		nsBottom 131
		nsRight 156
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(19
				(if (== (ego view?) 40)
					(if (not local2)
						(= local2 1)
						(messager say: 3 19 93)
					else
						(messager say: 3 19 94)
					)
				else
					(messager say: 3 6 100)
				)
			)
			(4
				(if (== (ego view?) 40)
					(if local2
						(++ local4)
						(rm290 setScript: freakOut)
					else
						(messager say: 3 4 95)
					)
				else
					(messager say: 3 6 100)
				)
			)
			(1
				(if local2
					(messager say: 3 1 94)
				else
					(messager say: 3 1 95)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance spiderplant of Feature
	(properties
		x 86
		y 52
		noun 4
		nsTop 35
		nsLeft 67
		nsBottom 70
		nsRight 105
		sightAngle 180
	)
)

(instance pile-o-books of Feature
	(properties
		x 53
		y 155
		noun 5
		nsTop 146
		nsLeft 40
		nsBottom 164
		nsRight 66
		sightAngle 180
	)
)

(instance up-left-blue-bottle of Feature
	(properties
		x 39
		y 76
		noun 6
		nsTop 71
		nsLeft 35
		nsBottom 82
		nsRight 44
		sightAngle 180
	)
)

(instance up-left-plant of Feature
	(properties
		x 42
		y 33
		noun 7
		nsTop 18
		nsLeft 34
		nsBottom 48
		nsRight 51
		sightAngle 180
	)
)

(instance low-left-blue-bottle of Feature
	(properties
		x 38
		y 135
		noun 8
		nsTop 131
		nsLeft 35
		nsBottom 140
		nsRight 42
		sightAngle 180
	)
)

(instance left-mid-bottles of Feature
	(properties
		x 44
		y 99
		noun 9
		nsTop 91
		nsLeft 35
		nsBottom 107
		nsRight 53
		sightAngle 180
	)
)

(instance table of Feature
	(properties
		x 90
		y 114
		noun 10
		nsTop 100
		nsLeft 80
		nsBottom 128
		nsRight 100
		sightAngle 180
	)
)

(instance greenvase of Feature
	(properties
		x 73
		y 132
		noun 11
		nsTop 120
		nsLeft 68
		nsBottom 144
		nsRight 79
		sightAngle 180
	)
)

(instance beadeddoor of Feature
	(properties
		x 130
		y 86
		noun 12
		nsTop 63
		nsLeft 118
		nsBottom 109
		nsRight 143
		sightAngle 180
	)
)

(instance giant-red-pot of Feature
	(properties
		x 166
		y 95
		noun 13
		nsTop 77
		nsLeft 156
		nsBottom 113
		nsRight 176
		sightAngle 180
	)
)

(instance roundpot of Feature
	(properties
		x 199
		y 162
		noun 14
		nsTop 156
		nsLeft 190
		nsBottom 169
		nsRight 209
		sightAngle 180
	)
)

(instance low-hang-plat of Feature
	(properties
		x 216
		y 97
		noun 16
		nsTop 79
		nsLeft 204
		nsBottom 116
		nsRight 228
		sightAngle 180
	)
)

(instance small-spider-plant of Feature
	(properties
		x 162
		y 51
		noun 17
		nsTop 43
		nsLeft 148
		nsBottom 59
		nsRight 176
		sightAngle 180
	)
)

(instance rightpots of Feature
	(properties
		x 258
		y 112
		noun 18
		nsTop 98
		nsLeft 248
		nsBottom 126
		nsRight 269
		sightAngle 180
	)
)

(instance plantshelf of Feature
	(properties
		x 230
		y 46
		noun 19
		nsTop 40
		nsLeft 194
		nsBottom 52
		nsRight 266
		sightAngle 180
	)
)

(instance upper-shelf of Feature
	(properties
		x 189
		y 32
		noun 20
		nsTop 29
		nsLeft 164
		nsBottom 36
		nsRight 214
		sightAngle 180
	)
)

(instance brownbottles of Feature
	(properties
		x 231
		y 131
		noun 21
		nsTop 116
		nsLeft 221
		nsBottom 146
		nsRight 242
		sightAngle 180
	)
)

(instance morebottles of Feature
	(properties
		x 243
		y 78
		noun 22
		nsTop 68
		nsLeft 227
		nsBottom 88
		nsRight 259
		sightAngle 180
	)
)

(instance bottles-over-door of Feature
	(properties
		x 132
		y 48
		noun 23
		nsTop 44
		nsLeft 121
		nsBottom 52
		nsRight 143
		sightAngle 180
	)
)

(instance hookedbottle of Feature
	(properties
		x 65
		y 101
		noun 24
		nsTop 92
		nsLeft 61
		nsBottom 111
		nsRight 69
		sightAngle 180
	)
)

(instance sFx of Sound
	(properties)
)
