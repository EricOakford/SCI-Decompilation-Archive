;;; Sierra Script 1.0 - (do not remove this comment)
(script# 290)
(include game.sh) (include "290.shm")
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
	cycleTime =  10
	paletteCycling
	pipeLit
	savePalette
	numTokes
	toX =  150
	toY =  150
	theVendor
	local8
	local9 = [0 -68 -69 -1 2 -70 79 74 75 76 78 77 -110 999]
	[local23 4]
	local27 = [0 -49 -65 -54 -60 -66 -33 -35 -37 -39 -41 -43 -5 -29 -111 -30 85 -81 -57 -47 999]
	local48 = [0 45 80 -44 46 999]
	local54 = [0 -20 999]
	local57 = [0 21 22 999]
	local61 = [0 -112 999]
	local64 = [0 -25 999]
	local67 = [0 -24 26 -27 999]
	local72 = [0 71 999]
	local75 = [0 28 999]
	local78 = [0 -34 999]
	local81 = [0 103 999]
	local84 = [0 36 999]
	local87 = [0 38 999]
	local90 = [0 40 999]
	local93 = [0 42 999]
	local96 = [0 50 51 999]
	local100 = [0 67 999]
	local103 = [0 55 56 999]
	local107 = [0 -61 999]
	local110 = [0 63 62 64 999]
	local115 = [0 53 999]
	local118 = [0 -58 59 999]
	local122 = [0 -19 999]
	local125 = [0 48 999]
	[local128 28]
	local156 = [0 -49 -65 -54 -60 -61 -66 -33 -34 -35 -37 -39 -41 -43 -44 -20 -5 -29 -25 -24 -27 -111 -57 -58 -47 999]
)
(procedure (ApothSet flagEnum)
	(|= apothFlags flagEnum)
)

(procedure (ApothClr flagEnum)
	(&= apothFlags (~ flagEnum))
)

(procedure (ApothTst flagEnum)
	(return (& apothFlags flagEnum))
)

(procedure (SalimHas what &tmp index)
	(return (== ((= index (inventory at: what)) state?) curRoom))
)

;Apothecary flags
(define FIRST_TIME			$0001)
(define SECOND_TIME			$0002)
(define THIRD_TIME			$0004)
(define DISPEL_MADE			$0008)
;$0010 is unused
(define GOT_DISPEL			$0020)
(define SAID_HELLO			$0040)
(define SAID_GOODBYE		$0080)
(define TOLD_ABOUT_JULANAR	$0100)
(define TOLD_ABOUT_SHAPEIR	$0200)
(define ASKED_ABOUT_DISPEL	$0400)
(define DISPELLED_LEOPARD	$0800)
(define GOT_ALL_INGREDIENTS	$1000)
(define ASKED_ABOUT_DREAM	$2000)
(define SALIM_STANDING		$4000)
(define DISPEL_DONE			$8000)

(instance rm290 of Room
	(properties
		noun N_ROOM
		picture 290
		vanishingY -100
	)
	
	(method (init)
		(cSound hold: 0 holdVal: 0 setVol: 127)
		(HandsOff)
		(if (ApothTst SALIM_STANDING)
			(ApothClr SALIM_STANDING)
		)
		(cond 
			((not apothFlags)
				(ApothSet FIRST_TIME)
				(salim loop: 0 cel: 0)
				(Book show:)
			)
			((ApothTst FIRST_TIME)
				(ApothClr FIRST_TIME)
				(ApothSet SECOND_TIME)
				(salim loop: 2 cel: 0)
				(Book hide:)
			)
			((ApothTst SECOND_TIME)
				(ApothClr SECOND_TIME)
				(ApothSet THIRD_TIME)
			)
			((and (ApothTst THIRD_TIME) (ApothTst GOT_ALL_INGREDIENTS))
				(ApothClr THIRD_TIME)
				(ApothSet DISPEL_MADE)
			)
		)
		(ego
			init:
			x: 135
			y: 192
			normalize:
			setScale: Scaler 113 87 150 110
			noun: N_EGO_TELL
		)
		(self setScript: sEnter)
		(super init:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PContainedAccess
					init:
						173 170
						181 170
						203 158
						212 141
						207 141
						187 143
						176 144
						163 144
						140 144
						129 147
						110 147
						106 139
						114 129
						125 121
						152 124
						153 118
						139 117
						140 114
						123 114
						116 121
						107 127
						98 133
						83 148
						77 165
						95 171
						95 189
						173 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						123 148
						150 146
						173 146
						171 157
						162 160
						155 161
						131 160
						125 156
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
		(Cushion2 init: setScale: approachVerbs: V_DO stopUpd:)
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
			((SalimHas iFeather)
				(= healingPillsInStock 9999)
			)
			((not healingPillsInStock)
				(= healingPillsInStock 4)
			)
		)
	)
	
	(method (doit)
		(cond 
			(script 0)
			((and (ego mover?) (== (ego view?) 40))
				(if (IsObject (ego looper?)) ((ego looper?) dispose:))
				(ego setMotion: 0 setScript: stand)
			)
			((> (ego y?) 170)
				(HandsOff)
				(self setScript: sExit)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(walkHandler delete: curRoom)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_WALK
				(egoActions doVerb: V_WALK)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance sExit of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 135 185 self)
			)
			(1
				(ego setMotion: MoveTo 135 225 self)
			)
			(2
				(curRoom newRoom: 270)
			)
		)
	)
)

(instance sEnter of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 165 160 self)
			)
			(1
				(cond 
					((ApothTst FIRST_TIME)
						(salim setLoop: 0)
						(Book show:)
						(= cycles 5)
					)
					((ApothTst SECOND_TIME)
						(salim setLoop: 2)
						(= cycles 5)
					)
					((ApothTst THIRD_TIME)
						(if (mod apothDay 2)
							(Book hide:)
							(salim setLoop: 0)
							(= cycles 5)
						else
							(Book hide:)
							(salim setLoop: 2)
							(= cycles 5)
						)
					)
					((ApothTst DISPEL_MADE)
						(Book show:)
						(salim setLoop: 0)
						(= cycles 5)
					)
					(else
						(Book hide:)
						(salim setLoop: 2)
						(= cycles 5)
					)
				)
			)
			(2
				(cond 
					((ApothTst FIRST_TIME)
						(messager say: N_SALIM V_DOIT C_FIRST_TIME)
					)
					((ApothTst SECOND_TIME)
						(if (ApothTst TOLD_ABOUT_JULANAR)
							(messager say: N_SALIM V_DOIT C_JULANAR2)
						else
							(messager say: N_SALIM V_DOIT C_HELLO1)
						)
						(= apothDay 1)
					)
					((ApothTst THIRD_TIME)
						(cond 
							((== apothDay 1)
								(messager say: N_SALIM V_DOIT C_HELLO2)
								(++ apothDay)
							)
							((== apothDay 2)
								(messager say: N_SALIM V_DOIT C_HELLO3)
								(++ apothDay)
							)
							((== apothDay 3)
								(messager say: N_SALIM V_DOIT C_HELLO4)
								(++ apothDay)
							)
							((== apothDay 4)
								(messager say: N_SALIM V_DOIT C_HELLO5)
								(++ apothDay)
							)
							(else
								(messager say: N_SALIM V_DOIT C_HELLO1)
								(= apothDay 1)
							)
						)
					)
					((ApothTst DISPEL_MADE)
						(if (== apothDay Day)
							(messager say: N_SALIM V_DOIT C_WAIT_A_DAY)
						else
							(messager say: N_SALIM V_DOIT C_DISPEL_READY)
							(= apothDay 0)
						)
					)
					((== apothDay 0)
						(messager say: N_SALIM V_DOIT C_HELLO_DISPEL)
						(++ apothDay)
					)
					((== apothDay 1)
						(messager say: N_SALIM V_DOIT C_HELLO1)
						(++ apothDay)
					)
					((== apothDay 2)
						(messager say: N_SALIM V_DOIT C_HELLO2)
						(++ apothDay)
					)
					((== apothDay 3)
						(messager say: N_SALIM V_DOIT C_HELLO3)
						(++ apothDay)
					)
					((== apothDay 4)
						(messager say: N_SALIM V_DOIT C_HELLO4)
						(++ apothDay)
					)
					((== apothDay 5)
						(messager say: N_SALIM V_DOIT C_HELLO5)
						(= apothDay 0)
					)
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
	
	(method (changeState newState &tmp [temp0 20])
		(switch (= state newState)
			(0
				(HandsOff)
				(if (== (salim loop?) 3)
					(salim setCycle: BegLoop self)
				else
					(self cue:)
				)
			)
			(1
				(messager say: N_SALIM V_DOIT C_LOOK_IT_UP 0 self)
			)
			(2
				(Book hide:)
				(salim cycleSpeed: 5 loop: 1 setCycle: Forward)
				(globalSound number: 928 setLoop: -1 play:)
				(= seconds 5)
			)
			(3
				(globalSound stop:)
				(messager say: N_SALIM V_DOIT C_DISPEL_INGREDIENTS 0 self)
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
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(sounds eachElementDo: #fade 0 1 15 1)
				(DrawPic 0 -32761 TRUE)
				(cast eachElementDo: #dispose)
				(= cycles 5)
			)
			(1
				(messager say: N_PIPE V_DOIT C_TOKE_DEATH 0 self)
			)
			(2
				(EgoDead C_DEATH_ADDICT 0 294 Forward 291)
			)
		)
	)
)

(instance freakOut of Script
	
	(method (doit)
		(if paletteCycling
			(cond 
				((< cycleTime 20)
					(++ cycleTime)
					(Palette PALCycle 1 71 1 73 235 1)
				)
				((< cycleTime 40)
					(++ cycleTime)
					(Palette PALCycle 1 71 1 73 235 -1)
				)
				(else
					(= cycleTime 0)
				)
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (== numTokes 1)
					(messager say: N_PIPE V_DO C_FREAK_OUT 0 self)
				else
					(self cue:)
				)
			)
			(1
				(cSound pause: TRUE)
				(sFx number: 291 play:)
				(= savePalette (Palette PALSave))
				(= paletteCycling TRUE)
				(= ticks 270)
			)
			(2
				(cSound pause: FALSE)
				(= paletteCycling 0)
				(Palette PALRestore savePalette)
				(switch numTokes
					(1
						(messager say: N_PIPE V_DO C_TOKE1 0 self)
					)
					(2
						(messager say: N_PIPE V_DO C_TOKE2 0 self)
					)
					(3
						(self setScript: tokeDeath)
					)
				)
			)
			(3
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sit of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 158 150 self)
			)
			(1
				(ego view: 40 cel: 0 setLoop: 2 setCycle: EndLoop self)
				(if (ego looper?)
					((ego looper?) dispose:)
					(ego looper: 0)
				)
			)
			(2
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance stand of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setCycle: BegLoop self)
			)
			(1
				(ego
					normalize: 6
					cel: 0
					setMotion: PolyPath (ego x?) (- (ego y?) 10) self
				)
			)
			(2
				(ego setMotion: PolyPath toX toY)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance standUp of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: N_EGO_TELL V_TELL C_JULANAR1 0 self)
			)
			(1
				(messager say: N_SALIM V_DOIT C_JULANAR1 0 self)
			)
			(2
				(Book show:)
				(salim cel: 0 loop: 3 setCycle: EndLoop self)
			)
			(3
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance egoTell of Teller
	
	(method (showDialog)
		(super
			showDialog:
				-1
				(if (and (ApothTst ASKED_ABOUT_DREAM) (not (ApothTst TOLD_ABOUT_JULANAR)))
					(ApothTst (| FIRST_TIME SECOND_TIME))
				else
					0
				)
				2
				(if (and (ApothTst TOLD_ABOUT_JULANAR) (not (ApothTst SALIM_STANDING)))
					(ApothTst (| FIRST_TIME SECOND_TIME))
				else
					0
				)
				-70
				(not
					(if (ApothTst ASKED_ABOUT_DREAM) else (ApothTst TOLD_ABOUT_SHAPEIR))
				)
				79 (ApothTst DISPELLED_LEOPARD)
				74 (ego has: iFeather)
				75 (ego has: iVineFruit)
				76 (ego has: iGem)
				78 (ego has: iHeartGift)
				77 (ego has: iPeaceWater)
		)
	)
	
	(method (doChild param1 &tmp temp0)
		(cond 
			((== param1 -1)
				(if (not (ApothTst TOLD_ABOUT_JULANAR))
					(ego solvePuzzle: fTellAboutJulanar 10 addHonor: 40)
					(ApothSet TOLD_ABOUT_JULANAR)
					(ApothSet SALIM_STANDING)
					(salim setScript: standUp)
					(return FALSE)
				)
			)
			((== param1 -70)
				(if (not (ApothTst ASKED_ABOUT_DREAM))
					(ApothSet TOLD_ABOUT_SHAPEIR)
				)
			)
			((== param1 -68)
				(if (not (ApothTst SAID_HELLO))
					(ApothSet SAID_HELLO)
					(ego addHonor: 2)
				)
			)
			((== param1 -69)
				(if (not (ApothTst SAID_GOODBYE))
					(ApothSet SAID_GOODBYE)
					(ego addHonor: 2)
				)
			)
			((== param1 -110)
				(salimTell doVerb: ((inventory at: iRoyals) message?))
				(return FALSE)
			)
		)
		(return TRUE)
	)
)

(instance salimTell of Teller
	
	(method (showDialog)
		(super
			showDialog:
				-33 (ApothTst FIRST_TIME)
				-35 (ApothTst SECOND_TIME)
				-37 (ApothTst THIRD_TIME)
				-39 (ApothTst DISPEL_MADE)
				-41 (ApothTst GOT_DISPEL)
				-5
				(if (ApothTst (| FIRST_TIME SECOND_TIME THIRD_TIME))
					(not (ApothTst ASKED_ABOUT_DISPEL))
				else
					0
				)
				-24 (not (SalimHas iPeaceWater))
				-27 (not (SalimHas iVineFruit))
				26 (not (SalimHas iHeartGift))
				-81
				(if (ApothTst TOLD_ABOUT_SHAPEIR) (ApothTst (| FIRST_TIME SECOND_TIME)) else 0)
				-29 (if (ApothTst (| FIRST_TIME SECOND_TIME)) (ApothTst ASKED_ABOUT_DISPEL) else 0)
				-111
				(if (and (ApothTst ASKED_ABOUT_DISPEL) (ApothTst THIRD_TIME))
					(not (ApothTst GOT_ALL_INGREDIENTS))
				else
					0
				)
				-30
				(if (and (ApothTst GOT_ALL_INGREDIENTS) (not (ApothTst GOT_DISPEL)))
					(== apothDay Day)
				else
					0
				)
				85 (if (ApothTst 8) (!= apothDay Day) else 0)
				-44 (not (SalimHas 30))
				80 (SalimHas 30)
				-49 (ApothTst FIRST_TIME)
				-65 (ApothTst SECOND_TIME)
				-54 (ApothTst THIRD_TIME)
				-60 (ApothTst DISPEL_MADE)
				-66 (ApothTst GOT_DISPEL)
				-57 (ApothTst (| FIRST_TIME SECOND_TIME))
				-47 (ApothTst FIRST_TIME)
				-58 (ApothTst FIRST_TIME)
		)
	)
	
	(method (doChild param1 &tmp temp0)
		(return
			(cond 
				((== param1 -20)
					(if (not (Btst fHoneyBirdHinted))
						(Bset fHoneyBirdHinted)
					)
					(if (ApothTst FIRST_TIME)
						(super doChild: param1)
					else
						(return TRUE)
					)
				)
				((== param1 -57)
					(if (ApothTst FIRST_TIME)
						(super doChild: param1)
					else
						(return TRUE)
					)
				)
				((== param1 -19)
					(ApothSet ASKED_ABOUT_DREAM)
					(return TRUE)
				)
				((== param1 -81)
					(ApothSet ASKED_ABOUT_DREAM)
					(return TRUE)
				)
				((== param1 -112)
					(if (not (Btst fAskedAboutDispel))
						(ApothSet ASKED_ABOUT_DISPEL)
						(Bset fAskedAboutDispel)
						(salimTell stuffArray: @local27 0)
						(curRoom setScript: lookUp)
					)
					(return 0)
				)
				((== param1 -29)
					(if (ApothTst GOT_ALL_INGREDIENTS)
						(return TRUE)
					else
						(super doChild: param1)
					)
				)
				((== param1 -30)
					(return TRUE)
				)
				((== param1 -44)
					(if (ApothTst GOT_DISPEL)
						(return TRUE)
					else
						(super doChild: param1)
					)
				)
				(else
					(super doChild: param1)
				)
			)
		)
	)
	
	(method (doVerb theVerb &tmp [temp0 20] numPills)
		(return
			(cond 
				((== theVerb V_DINARS)
					(messager say: N_SALIM V_DINARS C_WRONG_MONEY)
				)
				((== theVerb V_ROYALS)
					(if (not (salimVendor goods?))
						(if (< healingPillsInStock 0)
							(= numPills 0)
						else
							(= numPills healingPillsInStock)
						)
						(if (not (ApothTst DISPEL_DONE))
							(if (ApothTst DISPEL_MADE)
								(if (== heroType MAGIC_USER)
									(= dispelPotionsInStock 3)
								else
									(= dispelPotionsInStock 2)
								)
							else
								(= dispelPotionsInStock 1)
							)
						)
						(salimVendor
							goods:
								((List new:)
									add:
										((Ware new: N_HEALING_PILLS) price: 10 quantity: numPills)
										((Ware new: N_MANA_PILLS) price: 20 quantity: 9999)
										((Ware new: N_CURE_PILLS) price: 20 quantity: 9999)
										((Ware new: N_DISPEL_POTION) price: 30 quantity: dispelPotionsInStock)
								)
						)
					)
					(salimVendor purchase:)
					(return TRUE)
				)
				((== theVerb V_PEACEWATER)
					(if (Btst fAskedAboutDispel)
						(if (Btst fGivePeaceWater)
							(messager say: N_SALIM V_DOIT C_DONT_NEED)
							(return TRUE)
						else
							(ego drop: iPeaceWater 1 solvePuzzle: fGivePeaceWater 3)
							((inventory at: iPeaceWater) state: curRoom)
							(messager say: N_SALIM V_PEACEWATER C_GIVE)
							(cond 
								((and (SalimHas iVineFruit) (SalimHas iHeartGift))
									(ApothClr (| FIRST_TIME SECOND_TIME))
									(ApothSet THIRD_TIME)
									(ApothSet GOT_ALL_INGREDIENTS)
									(= apothDay Day)
									(salimTell stuffArray: @local27 0)
									(messager say: N_SALIM V_DOIT C_DISPEL_START)
								)
								((== (salimTell curArray?) @local72)
									(salimTell stuffArray: @local67 0)
								)
							)
							(return TRUE)
						)
					else
						(super doVerb: theVerb &rest)
					)
				)
				((== theVerb V_VINEFRUIT)
					(if (Btst fAskedAboutDispel)
						(if (Btst fGiveVineFruit)
							(messager say: N_SALIM V_DOIT C_DONT_NEED)
							(return TRUE)
						else
							(ego drop: iVineFruit 1 solvePuzzle: fGiveVineFruit 3)
							((inventory at: iVineFruit) state: curRoom)
							(messager say: N_SALIM V_VINEFRUIT C_GIVE)
							(cond 
								((and (SalimHas iPeaceWater) (SalimHas iHeartGift))
									(ApothClr (| FIRST_TIME SECOND_TIME))
									(ApothSet THIRD_TIME)
									(ApothSet GOT_ALL_INGREDIENTS)
									(= apothDay Day)
									(salimTell stuffArray: @local27 0)
									(messager say: N_SALIM V_DOIT C_DISPEL_START)
								)
								((== (salimTell curArray?) @local75)
									(salimTell stuffArray: @local67 0)
								)
							)
							(return TRUE)
						)
					else
						(super doVerb: theVerb &rest)
					)
				)
				((== theVerb V_HEARTGIFT)
					(if (Btst fAskedAboutDispel)
						(if (Btst fGiveHeartGift)
							(messager say: N_SALIM V_DOIT C_DONT_NEED)
							(return TRUE)
						else
							(ego drop: iHeartGift 1 solvePuzzle: fGiveHeartGift 3)
							((inventory at: iHeartGift) state: curRoom)
							(messager say: N_SALIM V_HEARTGIFT C_GIVE)
							(if (and (SalimHas iVineFruit) (SalimHas iPeaceWater))
								(ApothSet GOT_ALL_INGREDIENTS)
								(ApothClr (| FIRST_TIME SECOND_TIME))
								(ApothSet THIRD_TIME)
								(= apothDay Day)
								(messager say: N_SALIM V_DOIT C_DISPEL_START)
								(salimTell stuffArray: @local27 0)
							)
							(return TRUE)
						)
					else
						(super doVerb: theVerb &rest)
					)
				)
				((== theVerb V_FEATHER)
					(ego
						drop: iFeather
						get: iHealPills 3
						solvePuzzle: fGiveFeather 3)
					((inventory at: iFeather) state: curRoom)
					(messager say: N_SALIM V_FEATHER C_GIVE)
					(if (not (Btst fGiveFeather))
						(ego addHonor: 10)
					)
					(return TRUE)
				)
				(else
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance egoActions of Actions

	(method (doVerb theVerb)
		(return
			(switch theVerb
				(V_WALK
					(cond 
						((curRoom script?) 0)
						((== (ego view?) 40)
							(= toX ((User curEvent?) x?))
							(= toY ((User curEvent?) y?))
							(curRoom setScript: stand)
							(return TRUE)
						)
					)
				)
				(else
					(super doVerb: theVerb)
				)
			)
		)
	)
)

(instance Cushion1 of View
	(properties
		x 186
		y 136
		noun N_CUSHION1
		view 290
		signal (| ignrAct fixPriOn)
		scaleX 132
		scaleY 132
	)
)

(instance Cushion2 of View
	(properties
		x 145
		y 153
		noun N_CUSHION2
		approachX 155
		approachY 160
		view 290
		priority 10
		signal (| ignrAct fixPriOn)
		scaleX 155
		scaleY 155
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(if (!= (ego view?) 40)
					(curRoom setScript: sit)
				else
					(super doVerb: theVerb)
				)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance Book of View
	(properties
		x 192
		y 133
		noun N_BOOK
		view 290
		loop 1
		priority 10
		signal (| ignrAct fixPriOn)
		scaleX 132
		scaleY 132
	)
)

(instance salim of Prop
	(properties
		x 186
		y 134
		noun N_SALIM
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
		signal fixPriOn
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
		signal fixPriOn
	)
)

(instance salimMouth of Prop
	(properties
		nsTop 47
		nsLeft 42
		view 293
		priority 15
		signal fixPriOn
	)
)

(instance salimVendor of Vendor
	(properties
		noun N_SALIM_VENDOR
	)
	
	(method (transact what howMany)
		(= theVendor self)
		(switch what
			(0
				(Buy what howMany)
				(if (not ((goods at: what) quantity?))
					(= healingPillsInStock -1)
					(ego get: iHealPills howMany)
					(messager say: N_SALIM V_DOIT C_OUT_OF_PILLS 0 self)
				else
					(if (not (> healingPillsInStock 4))
						(-= healingPillsInStock howMany)
					)
					(ego get: iHealPills howMany)
					(self cue:)
				)
			)
			(1
				(ego get: iManaPills howMany)
				(Buy what howMany)
				(self cue:)
			)
			(2
				(ego get: iCurePills howMany)
				(Buy what howMany)
				(self cue:)
			)
			(3
				(cond 
					((not (ApothTst ASKED_ABOUT_DISPEL))
						(ApothSet ASKED_ABOUT_DISPEL)
						(Bset fAskedAboutDispel)
						(salimTell stuffArray: @local27 0)
						(self setScript: lookUp self)
					)
					((not (ApothTst DISPEL_DONE))
						(cond 
							((not (ApothTst GOT_ALL_INGREDIENTS))
								(messager say: N_SALIM V_DOIT C_NEED_INGREDIENTS 0 self)
							)
							((== apothDay Day)
								(messager say: N_SALIM V_DOIT C_NEED_TO_WAIT 0 self)
							)
							(else
								(ApothClr DISPEL_MADE)
								(ApothSet GOT_DISPEL)
								(if (ApothTst TOLD_ABOUT_JULANAR)
									(ego
										get: iDispell dispelPotionsInStock
										solvePuzzle: fGetDispelPotions 6
									)
									(= dispelPotionsInStock 0)
									((goods at: what) quantity: 0)
									(ApothSet DISPEL_DONE)
									(messager say: N_SALIM V_DOIT C_GIFT_DISPEL 0 self)
								else
									(ego
										get: iDispell howMany
										solvePuzzle: fGetDispelPotions 6
									)
									(-= dispelPotionsInStock howMany)
									(Buy what howMany)
									(ApothSet DISPEL_DONE)
									(self cue:)
								)
							)
						)
					)
					(else
						(ego get: iDispell howMany)
						(-= dispelPotionsInStock howMany)
						(Buy what howMany)
						(self cue:)
					)
				)
			)
		)
	)
	
	(method (doBargain result)
		(switch result
			(bargainSUCCESS
				(messager say: N_SALIM V_DOIT C_BARGAIN_SUCCESS 0 self)
			)
			(bargainTRY1
				(messager say: N_SALIM V_DOIT C_BARGAIN_TRY1 0 self)
			)
			(bargainTRY2
				(messager say: N_SALIM V_DOIT C_BARGAIN_TRY3 0 self)
			)
			(bargainTRY3
				(messager say: N_SALIM V_DOIT C_BARGAIN_TRY2 0 self)
			)
			(bargainFAIL
				(messager say: N_SALIM V_DOIT C_BARGAIN_FAIL 0 self)
			)
			(bargainNODEAL
				(messager say: N_SALIM V_DOIT C_BARGAIN_NO_DEAL 0 self)
			)
			(else
				(self cue:)
			)
		)
	)
)

(instance pipe of Feature
	(properties
		x 143
		y 120
		noun N_PIPE
		nsTop 100
		nsLeft 130
		nsBottom 131
		nsRight 156
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_TINDERBOX
				(if (== (ego view?) 40)
					(if (not pipeLit)
						(= pipeLit TRUE)
						(messager say: N_PIPE V_TINDERBOX C_LIGHT_PIPE)
					else
						(messager say: N_PIPE V_TINDERBOX C_PIPE_LIT)
					)
				else
					(messager say: N_PIPE V_DOIT C_STANDING)
				)
			)
			(V_DO
				(if (== (ego view?) 40)
					(if pipeLit
						(++ numTokes)
						(rm290 setScript: freakOut)
					else
						(messager say: N_PIPE V_DO C_PIPE_UNLIT)
					)
				else
					(messager say: N_PIPE V_DOIT C_STANDING)
				)
			)
			(V_LOOK
				(if pipeLit
					(messager say: N_PIPE V_LOOK C_PIPE_LIT)
				else
					(messager say: N_PIPE V_LOOK C_PIPE_UNLIT)
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
		noun N_SPIDERPLANT
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
		noun N_BOOK_PILE
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
		noun N_ELIXIR
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
		noun N_CREEPER
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
		noun N_ANTIDOTE
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
		noun N_BOTTLES
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
		noun N_TABLE
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
		noun N_GREENVASE
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
		noun N_BEADED_DOOR
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
		noun N_REDPOT
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
		noun N_ROUNDPOT
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
		noun N_HOYA
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
		noun N_FERN
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
		noun N_RIGHTPOTS
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
		noun N_PLANTSHELF
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
		noun N_UPPER_SHELF
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
		noun N_BROWNBOTTLES
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
		noun N_MOREBOTTLES
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
		noun N_BOTTLES_OVER_DOOR
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
		noun N_HOOKEDBOTTLE
		nsTop 92
		nsLeft 61
		nsBottom 111
		nsRight 69
		sightAngle 180
	)
)

(instance sFx of Sound)
