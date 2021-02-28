;;; Sierra Script 1.0 - (do not remove this comment)
(script# 260)
(include sci.sh)
(use Main)
(use TellerIcon)
(use Vendor)
(use Bazaar)
(use GloryTalker)
(use PolyPath)
(use Polygon)
(use Feature)
(use Timer)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm260 0
	kattaTalker 1
	meatTalker 2
	amuletTalker 3
	clothTalker 4
)

(local
	theVendor
	local1
	local2
	local3
	local4 =  1
	local5
	local6
	local7
	local8
	local9
	local10
	theEgoStats
	[theGTheEgoStats 17] = [0 -80 -81 -10 -82 43 -84 -38 -39 -40 -41 -42 -47 68 73 -74 999]
	[local29 2]
	[local31 7] = [0 8 -85 9 -78 23 999]
	[local38 2]
	[local40 6] = [0 -47 9 -78 -87 999]
	[local46 3] = [0 48 999]
	[local49 3] = [0 -47 999]
	[local52 3]
	[aMeatSellerInit_3 6] = [0 59 9 -78 -88 999]
	[local76 2]
	[aMeatSellerInit 6] = [0 -75 9 78 79 999]
	[theAMeatSellerInit_2 4] = [0 76 77 999]
	[aMeatSellerInit_2 3] = [0 -75 999]
	[local76_3 3]
	[aMeatSellerInit_3_2 3]
)
(procedure (localproc_06a7)
	(if (> (++ local3) 2)
		(if
			(or
				(== (meatGreet state?) -1)
				(== (meatGreet state?) 5)
			)
			(= local3 0)
		else
			(= local3 1)
		)
	)
	(= local4 0)
	([aMeatSellerInit_3_2 local3]
		cel: 0
		setCycle: End aMeatSeller
	)
)

(instance rm260 of Rm
	(properties
		noun 31
		picture 260
		horizon -300
	)
	
	(method (init)
		(self setRegions: 51)
		(ego noun: 2 normalize: edgeHit: 0 init:)
		(HandsOn)
		(switch prevRoomNum
			(250
				(= style -32759)
				(self setScript: from250)
			)
			(else  (ego x: 112 y: 88))
		)
		(super init:)
		(if (or Night (Btst 135))
			(curRoom
				addObstacle:
					((Polygon new:)
						type: 2
						init: 319 0 319 84 267 68 225 62 190 54 124 64 59 21 149 0
						yourself:
					)
					((Polygon new:)
						type: 2
						init: 0 189 0 68 141 161 125 165 99 177 93 186 319 185 318 189
						yourself:
					)
			)
		else
			(cSound fade: 127 10 5 0)
			(curRoom
				addObstacle:
					((Polygon new:)
						type: 2
						init: 150 183 150 144 171 144 171 127 213 127 225 153 219 174 193 183
						yourself:
					)
					((Polygon new:)
						type: 2
						init:
							319
							0
							319
							189
							0
							189
							0
							66
							138
							159
							99
							187
							295
							187
							295
							111
							241
							79
							266
							73
							263
							68
							234
							71
							214
							59
							193
							63
							201
							73
							152
							84
							109
							74
							103
							58
							80
							60
							36
							34
							64
							27
							65
							0
						yourself:
					)
			)
			(= [local29 0] @theGTheEgoStats)
			(egoTeller init: ego @theGTheEgoStats @local29)
			(if (not (Btst 141))
				(bowl approachVerbs: 4 1 10 59 init: addToPic:)
			)
			(kattaVendor
				init:
				goods:
					((List new:)
						add: ((Class_47_1 new: 1)
							price: 5
							quantity: (if (Btst 169) 0 else 1)
						)
					)
			)
			(= [local38 0] @local31)
			(kattaTeller init: aKattaMerchant @local31 @local38)
			(aKattaMerchant
				init:
				actions: kattaTeller
				approachVerbs: 2 59 10
				stopUpd:
			)
			(meatVendor
				init:
				goods:
					((List new:)
						add: ((Class_47_1 new: 3)
							price: 27
							denomination: 1
							quantity: 999
						)
					)
			)
			(= [local52 0] @local40)
			(= [local52 1] @local46)
			(meatTeller init: aMeatSeller @local40 @local52 @local49)
			(aMeatSeller
				actions: meatTeller
				approachVerbs: 2 59 10
				stopUpd:
			)
			(= [local76 0] @aMeatSellerInit_3)
			(amuletTeller
				init: aAmuletSeller @aMeatSellerInit_3 @local76
			)
			(aAmuletUpper
				actions: amuletTeller
				approachVerbs: 2 59 10
				stopUpd:
			)
			(aAmuletSeller
				actions: amuletTeller
				approachVerbs: 2 59 10
				init:
				stopUpd:
			)
			(clothVendor
				init:
				goods:
					((List new:)
						add:
							((Class_47_1 new: 5)
								price: 10
								quantity: (if (Btst 170) 0 else 1)
							)
					)
			)
			(= [local76_3 0] @aMeatSellerInit)
			(= [local76_3 1] @theAMeatSellerInit_2)
			(clothTeller
				init: aClothSeller @aMeatSellerInit @local76_3 @aMeatSellerInit_2
			)
			(aClothSeller
				actions: clothTeller
				approachVerbs: 2 59 10
				stopUpd:
			)
			(aDrummer
				init:
				approachVerbs: 4 2 59 10
				setScript: drummerScript
			)
			(= [aMeatSellerInit_3_2 0] (aMeatSeller init:))
			(= [aMeatSellerInit_3_2 1] (aAmuletUpper init:))
			(= [aMeatSellerInit_3_2 2] (aClothSeller init:))
		)
		(if (and (not Night) (not (Btst 135)))
			(musical_sticks init:)
			(upper_katta_carvings init:)
			(lower_katta_carvings init:)
			(kattarug init:)
			(animalcarcass init:)
			(hotdogs init:)
			(tabletop init:)
			(deadducks init:)
			(ham init:)
			(back_meat_table init:)
			(meatstand init:)
			(stairs init:)
			(amuletrack init:)
			(amuletbeads init:)
			(amuletstand init:)
			(clothracks init:)
			(cloth_on_tent init:)
			(clothstand init:)
			(morecloth init:)
			(purpletents init:)
		)
		(water init:)
		(if (and (not Night) (not (Btst 135)))
			(ego code: dayCode)
			(meatStuff init: addToPic:)
			(clothStuff init: addToPic:)
			(drumsUpper init: addToPic:)
			(drumsLower init: addToPic:)
			(kattaStuff init: addToPic:)
		else
			(ego code: nightCode)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(messager
					say: 31 1 0 (if (or (Btst 135) Night) 0 else 1)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance dayCode of Code
	(properties)
	
	(method (doit)
		(if local4 (localproc_06a7))
		(cond 
			((curRoom script?) 0)
			((< (ego x?) 5) (curRoom setScript: sExit))
			((< (ego y?) 35) (curRoom setScript: sExit))
			((& (ego onControl: 1) $0004)
				(if (not local7)
					(curRoom setScript: kattaGreet)
					(= local7 1)
				)
			)
			((& (ego onControl: 1) $0008)
				(if (not local8)
					(curRoom setScript: meatGreet)
					(= local8 1)
				)
			)
			((& (ego onControl: 1) $0010)
				(if (not local9)
					(curRoom setScript: amuletGreet)
					(= local9 1)
				)
			)
			(
			(and (& (ego onControl: 1) $0020) (not local10)) (curRoom setScript: clothGreet) (= local10 1))
		)
	)
)

(instance nightCode of Code
	(properties)
	
	(method (doit)
		(cond 
			((curRoom script?) 0)
			((< (ego x?) 5) (curRoom setScript: sExit))
			((< (ego y?) 35) (curRoom setScript: sExit))
		)
	)
)

(instance giveMoney of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego view: 4 cel: 0 solvePuzzle: 236 3 setCycle: End self)
			)
			(1
				(cond 
					((== register 4)
						(cond 
							((and [oldStats 8] (not (Btst 141)))
								(cond 
									((== ((inventory at: 0) message?) 59) (messager say: 6 6 86 0 self))
									(
									(and (== ((inventory at: 0) amount?) 0) (not commons)) (messager say: 6 6 90 0 self))
									(else
										(if commons
											(= commons (- commons 1))
										else
											((inventory at: 0)
												amount: (- ((inventory at: 0) amount?) 1)
											)
											(= commons (+ commons 99))
										)
										(sFx number: 260 play:)
										(if (< (ego trySkill: 8 150) 0)
											(messager say: 6 6 94 0 self)
										else
											((inventory at: 0)
												amount: (+ ((inventory at: 0) amount?) 1)
											)
											(Bset 141)
											(messager say: 6 6 95 0 self)
										)
									)
								)
							)
							((and [oldStats 8] (Btst 141)) (messager say: 6 6 97 0 self))
							((== ((inventory at: 0) message?) 59) (messager say: 6 6 86 0 self))
							(else
								(= register 10)
								(self changeState: (= state (- state 1)))
								(self cue:)
							)
						)
					)
					((== ((inventory at: 0) amount?) 0)
						(if (< commons 11)
							(messager say: 6 6 90 0 self)
						else
							(= commons (- commons 10))
							(sFx number: 260 play:)
							(messager say: 6 6 24 0 self)
							(ego addHonor: 5)
						)
					)
					(else
						(sFx number: 260 play:)
						(if (< commons 11)
							((inventory at: 0)
								amount: (- ((inventory at: 0) amount?) 1)
							)
							(= commons (+ commons 90))
						else
							(= commons (- commons 10))
						)
						(messager say: 6 6 24 0 self)
						(ego addHonor: 5)
					)
				)
			)
			(2 (ego setCycle: Beg self))
			(3
				(ego normalize:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance clothGreet of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 263 84 self)
			)
			(1
				(Face ego aClothSeller)
				(= cycles 15)
			)
			(2
				(messager say: 5 6 80 0 self)
			)
			(3 (HandsOn) (self dispose:))
		)
	)
)

(instance meatGreet of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 181 79 self)
			)
			(1
				(if (aMeatSeller cycler?) (= local4 1))
				(aMeatSeller setCel: 8 setLoop: 1 setCycle: CT 0 -1 self)
			)
			(2 (Face ego aMeatSeller self))
			(3
				(aMeatSeller cel: 0 setLoop: 2 setCycle: End self)
			)
			(4
				(switch (mod Day 6)
					(0 (messager say: 3 6 2 0 self))
					(1 (messager say: 3 6 3 0 self))
					(2 (messager say: 3 6 4 0 self))
					(3 (messager say: 3 6 5 0 self))
					(4 (messager say: 3 6 6 0 self))
					(5 (messager say: 3 6 7 0 self))
				)
			)
			(5
				(HandsOn)
				(aMeatSeller setLoop: 1 stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance kattaGreet of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 20])
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 66 52 self)
			)
			(1
				(if
					(and
						(not (aKattaMerchant loop?))
						(not (aKattaMerchant cel?))
					)
					(aKattaMerchant setCycle: End self)
				else
					(= cycles 15)
				)
				(Face ego aKattaMerchant)
			)
			(2
				(switch (mod Day 6)
					(0 (messager say: 1 6 2 0 self))
					(1 (messager say: 1 6 3 0 self))
					(2 (messager say: 1 6 4 0 self))
					(3 (messager say: 1 6 5 0 self))
					(4 (messager say: 1 6 6 0 self))
					(5 (messager say: 1 6 7 0 self))
				)
			)
			(3
				(aKattaMerchant setCycle: CT 0 -1 self)
			)
			(4
				(HandsOn)
				(aKattaMerchant stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance amuletGreet of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 259 69 self)
			)
			(1
				(Face ego aAmuletSeller)
				(= cycles 15)
			)
			(2
				(switch (mod Day 6)
					(0 (messager say: 4 6 2 0 self))
					(1 (messager say: 4 6 3 0 self))
					(2 (messager say: 4 6 4 0 self))
					(3 (messager say: 4 6 5 0 self))
					(4 (messager say: 4 6 6 0 self))
					(5 (messager say: 4 6 7 0 self))
				)
			)
			(3 (HandsOn) (self dispose:))
		)
	)
)

(instance purchaseLeopard of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 2 5 46 0 self)
			)
			(1
				(kattaVendor purchase:)
				(= cycles 1)
			)
			(2 (self dispose:))
		)
	)
)

(instance from250 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego x: 20 y: 0 edgeHit: 0 setMotion: PolyPath 40 53 self)
			)
			(1 (ego setHeading: 135 self))
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance sExit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(cond 
					((<= (ego x?) 5) (= register 250) (ego setMotion: PolyPath 0 0 self))
					((<= (ego y?) 35)
						(= register 250)
						(ego setMotion: PolyPath (ego x?) 0 self)
					)
				)
			)
			(1
				(curRoom newRoom: register)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance drummerScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(aDrummer setLoop: (Random 0 1) setCycle: Fwd)
				(= seconds (Random 3 8))
			)
			(1
				(if (Random 0 3)
					(self init:)
				else
					(cSound stop:)
					(aDrummer setLoop: 0 setCycle: 0 cel: 4)
					(= seconds 15)
				)
			)
			(2 (cSound play:) (self init:))
		)
	)
)

(instance egoTeller of Teller
	(properties)
	
	(method (respond)
		(return
			(if (not local2)
				(super respond:)
			else
				(= local2 0)
				(cond 
					((not query) (return 1))
					((== query -999) (return 1))
					((== query 999) (self doParent:) (return 0))
					((and (< query 0) (not (self doChild: query))) (return 1))
				)
				(if (< query 0) (= query (- query)))
				(messager say: (client noun?) 5 query 0)
				(return 1)
			)
		)
	)
	
	(method (showDialog &tmp temp0)
		(= local1 (proc51_1))
		(= temp0 (ego distanceTo: local1))
		(switch local1
			(aKattaMerchant
				(if (> temp0 35) (messager say: 6 6 83) (return -999))
			)
			(aMeatSeller
				(if (> temp0 35) (messager say: 6 6 83) (return -999))
			)
			(aClothSeller
				(if (> temp0 25) (messager say: 6 6 83) (return -999))
			)
			(else 
				(if (> temp0 15) (messager say: 6 6 83) (return -999))
			)
		)
		(if
			(!=
				(ego heading?)
				(GetAngle (ego x?) (ego y?) (local1 x?) (local1 y?))
			)
			(Face ego local1)
		)
		((Timer new:) setCycle: self (+ (ego cycleSpeed?) 10))
		(= iconValue 0)
		(return -999)
	)
	
	(method (doChild &tmp [temp0 2])
		(return
			(switch query
				(-80
					(cond 
						((== local1 aKattaMerchant) (= query 36))
						((== local1 aMeatSeller) (= query 56))
						((== local1 aAmuletUpper) (= query 66))
						((== local1 aClothSeller) (= query 70))
					)
				)
				(-81
					(cond 
						((== local1 aKattaMerchant) (= query 37))
						((== local1 aMeatSeller) (= query 57))
						((== local1 aAmuletUpper) (= query 67))
						((== local1 aClothSeller) (= query 71))
					)
				)
				(-10
					(if (not (Btst 233)) (ego addHonor: 25))
					(ego solvePuzzle: 233 3)
					(if (== local6 6)
						(ego addHonor: 5)
						(messager say: 2 5 40)
						(return 0)
					else
						(return query)
					)
				)
				(-84
					(cond 
						((== local1 aKattaMerchant) (= query 35))
						((== local1 aMeatSeller) (= query 55))
						((== local1 aAmuletUpper) (= query 69))
						((== local1 aClothSeller) (= query 72))
					)
				)
				(-82
					(kattaTeller doVerb: 10)
					(return 0)
				)
				(-38
					(ego addHonor: 10)
					(return 1)
				)
				(-39
					(ego addHonor: 10)
					(return 1)
				)
				(-40
					(ego addHonor: 5)
					(return 1)
				)
				(-41
					(ego addHonor: 5)
					(return 1)
				)
				(-42
					(ego addHonor: 5)
					(return 1)
				)
				(-47
					(if (== ((inventory at: 0) message?) 59)
						(messager say: 3 6 86)
					else
						(meatVendor purchase:)
					)
					(return 0)
				)
				(-74
					(if (== ((inventory at: 0) message?) 59)
						(messager say: 5 6 86)
					else
						(clothVendor purchase:)
					)
					(return 0)
				)
			)
		)
	)
	
	(method (cue)
		(= query
			(super
				showDialog:
					-80
					-81
					-10
					(if
					(and (!= local6 2) (!= local6 4) (!= local6 6))
						(== local1 aKattaMerchant)
					else
						0
					)
					-38
					(if (== local1 aKattaMerchant) (== local6 2) else 0)
					-39
					(if (== local1 aKattaMerchant) (== local6 4) else 0)
					-40
					(if (== local1 aKattaMerchant) (== local6 6) else 0)
					-41
					(if (== local1 aKattaMerchant) (== local5 3) else 0)
					-42
					(if (== local1 aKattaMerchant) (== local5 5) else 0)
					-82
					(== local1 aKattaMerchant)
					43
					(if (and (== local1 aKattaMerchant) (Btst 38))
						(not (Btst 146))
					else
						0
					)
					-84
					(== heroType 2)
					-47
					(== local1 aMeatSeller)
					68
					(== local1 aAmuletSeller)
					73
					(== local1 aClothSeller)
					-74
					(if (== local1 aClothSeller) (not (Btst 114)) else 0)
			)
		)
		(= local2 1)
		(if iconValue (= query iconValue))
		(egoTeller respond:)
	)
)

(instance kattaTeller of Teller
	(properties)
	
	(method (showDialog)
		(super showDialog: 23 (ego has: 28))
	)
	
	(method (doChild)
		(switch query
			(-78
				(if (Btst 38)
					(= query 27)
				else
					(switch (mod Day 6)
						(0 (= query 11) (= local5 1))
						(1 (= query 13) (= local5 2))
						(2 (= query 12) (= local5 3))
						(3 (= query 14) (= local5 4))
						(4 (= query 15) (= local5 5))
						(5 (= query 16) (= local5 6))
					)
				)
			)
			(-85
				(if (Btst 38)
					(= query 28)
				else
					(switch (mod Day 6)
						(0 (= query 17) (= local6 1))
						(1 (= query 18) (= local6 2))
						(2 (= query 19) (= local6 3))
						(3 (= query 20) (= local6 4))
						(4 (= query 21) (= local6 5))
						(5 (= query 22) (= local6 6))
					)
				)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(59 (messager say: 1 6 86))
			(10
				(cond 
					((and (Btst 108) (not (Btst 146)))
						(messager say: 2 5 45)
						(Bset 146)
						(ego get: 32 solvePuzzle: 232 2)
					)
					((not (Btst 146))
						(if (== ((inventory at: 0) message?) 59)
							(messager say: 1 6 86)
						else
							(curRoom setScript: purchaseLeopard)
						)
					)
					(else (messager say: 2 5 44))
				)
			)
			(54
				(Bset 108)
				(ego drop: 43 addHonor: 50 solvePuzzle: 234 5)
				(messager say: 1 54 0)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance meatTeller of Teller
	(properties)
	
	(method (doChild)
		(switch query
			(-78
				(switch (mod Day 6)
					(0 (= query 11))
					(1 (= query 13))
					(2 (= query 12))
					(3 (= query 14))
					(4 (= query 15))
					(5 (= query 16))
				)
			)
			(-87
				(switch (mod Day 6)
					(0 (= query 49))
					(1 (= query 50))
					(2 (= query 51))
					(3 (= query 52))
					(4 (= query 53))
					(5 (= query 54))
				)
			)
			(else  (super doChild: &rest))
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(59 (messager say: 3 6 86))
			(10 (meatVendor purchase:))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance amuletTeller of Teller
	(properties)
	
	(method (doChild)
		(switch query
			(-78
				(switch (mod Day 6)
					(0 (= query 11))
					(1 (= query 13))
					(2 (= query 12))
					(3 (= query 14))
					(4 (= query 15))
					(5 (= query 16))
				)
			)
			(-88
				(switch (mod Day 6)
					(0 (= query 60))
					(1 (= query 61))
					(2 (= query 62))
					(3 (= query 63))
					(4 (= query 64))
					(5 (= query 65))
				)
			)
		)
	)
	
	(method (doVerb theVerb)
		(if (or (== theVerb 59) (== theVerb 10))
			(messager say: 2 5 68)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance clothTeller of Teller
	(properties)
	
	(method (doVerb theVerb)
		(switch theVerb
			(59 (messager say: 5 6 86))
			(10
				(if (Btst 114)
					(messager say: 2 5 73)
				else
					(clothVendor purchase:)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance aKattaMerchant of Actor
	(properties
		x 76
		y 46
		noun 1
		approachX 63
		approachY 52
		view 252
		signal $0010
		detailLevel 3
	)
)

(instance aMeatSeller of Actor
	(properties
		x 175
		y 68
		noun 3
		approachX 181
		approachY 79
		view 264
		loop 1
		priority 3
		signal $4010
		cycleSpeed 8
		detailLevel 3
	)
	
	(method (init)
		(super init:)
		(return self)
	)
	
	(method (cue)
		([aMeatSellerInit_3_2 local3] stopUpd:)
		(= local4 1)
	)
)

(instance aAmuletUpper of Actor
	(properties
		x 247
		y 66
		z 30
		noun 4
		approachX 259
		approachY 69
		view 266
		priority 3
		signal $4010
		cycleSpeed 8
		detailLevel 3
	)
	
	(method (init)
		(super init:)
		(return self)
	)
)

(instance aAmuletSeller of Prop
	(properties
		x 247
		y 65
		noun 4
		approachX 259
		approachY 69
		view 266
		loop 1
		priority 3
		signal $4010
		cycleSpeed 8
		detailLevel 3
	)
)

(instance aClothSeller of Actor
	(properties
		x 281
		y 87
		noun 5
		approachX 263
		approachY 84
		view 248
		loop 1
		signal $5000
		cycleSpeed 8
		detailLevel 3
	)
	
	(method (init)
		(super init:)
		(return self)
	)
)

(instance aDrummer of Prop
	(properties
		x 185
		y 175
		noun 29
		approachX 138
		approachY 151
		view 256
		cel 5
		priority 11
		signal $4010
		cycleSpeed 9
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(59 (bowl doVerb: theVerb))
			(10 (bowl doVerb: theVerb))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance kattaTalker of GloryTalker
	(properties
		x 5
		y 90
		view 253
		loop 1
		talkWidth 140
		back 57
		textX 140
		textY 3
		backColor 12
	)
	
	(method (init)
		(super init: kattaBust kattaEyes kattaMouth &rest)
	)
)

(instance kattaMouth of Prop
	(properties
		nsTop 45
		nsLeft 42
		view 253
	)
)

(instance kattaEyes of Prop
	(properties
		nsTop 33
		nsLeft 47
		view 253
		loop 2
	)
)

(instance kattaBust of View
	(properties
		nsTop 23
		nsLeft 42
		view 253
		loop 3
	)
)

(instance meatTalker of GloryTalker
	(properties
		x 200
		y 90
		view 265
		loop 1
		talkWidth 140
		back 57
		textX -178
		textY 3
		backColor 29
	)
	
	(method (init)
		(super init: meatBust meatEyes meatMouth &rest)
	)
)

(instance meatMouth of Prop
	(properties
		nsTop 36
		nsLeft 39
		view 265
	)
)

(instance meatEyes of Prop
	(properties
		nsTop 29
		nsLeft 36
		view 265
		loop 2
	)
)

(instance meatBust of View
	(properties
		nsTop 24
		nsLeft 44
		view 265
		loop 3
	)
)

(instance amuletTalker of GloryTalker
	(properties
		x 5
		y 90
		view 267
		loop 1
		talkWidth 140
		back 57
		textX 145
		textY 3
		backColor 12
	)
	
	(method (init)
		(super init: amuletBust amuletEyes amuletMouth &rest)
	)
)

(instance amuletMouth of Prop
	(properties
		nsTop 42
		nsLeft 48
		view 267
	)
)

(instance amuletEyes of Prop
	(properties
		nsTop 33
		nsLeft 48
		view 267
		loop 2
	)
)

(instance amuletBust of View
	(properties
		nsTop 13
		nsLeft 43
		view 267
		loop 3
	)
)

(instance clothTalker of GloryTalker
	(properties
		x 200
		y 90
		view 249
		loop 1
		talkWidth 140
		back 57
		textX -175
		textY 3
		backColor 25
	)
	
	(method (init)
		(super init: clothBust clothEyes clothMouth &rest)
	)
)

(instance clothMouth of Prop
	(properties
		nsTop 48
		nsLeft 32
		view 249
	)
)

(instance clothEyes of Prop
	(properties
		nsTop 36
		nsLeft 29
		view 249
		loop 2
	)
)

(instance clothBust of View
	(properties
		nsTop 27
		nsLeft 27
		view 249
		loop 3
	)
)

(instance kattaVendor of Vendor
	(properties
		noun 1
	)
	
	(method (transact param1 param2)
		(= theVendor self)
		(Bset 146)
		(ego get: 32 param2 solvePuzzle: 232 2)
		(Buy param1 param2)
		(if param2 (messager say: 1 6 24 0 self))
		(Bset 169)
	)
	
	(method (doBargain param1)
		(switch param1
			(1
				(messager say: 1 6 31 0 self)
			)
			(2
				(messager say: 1 6 32 0 self)
			)
			(3
				(messager say: 1 6 33 0 self)
			)
			(4
				(messager say: 1 6 34 0 self)
			)
			(5
				(messager say: 1 6 29 0 self)
			)
			(6
				(messager say: 1 6 30 0 self)
			)
			(else  (self cue:))
		)
	)
)

(instance meatVendor of Vendor
	(properties
		noun 3
	)
	
	(method (bargain)
		(= theEgoStats [egoStats 13])
		(= [egoStats 13] 550)
		(super bargain: &rest)
	)
	
	(method (transact param1 param2)
		(= theVendor self)
		(ego get: 18 param2)
		(Buy param1 param2)
		(messager say: 3 6 24 0 self)
	)
	
	(method (doBargain)
		(= [egoStats 13] theEgoStats)
		(messager say: 3 6 31 0 self)
	)
)

(instance clothVendor of Vendor
	(properties
		noun 5
	)
	
	(method (transact param1 param2)
		(= theVendor self)
		(ego get: 40 param2 solvePuzzle: 235 2)
		(Buy param1 param2)
		(if param2
			(messager say: 5 6 24 0 self)
			(Bset 170)
			(Bset 114)
		)
	)
	
	(method (doBargain param1)
		(switch param1
			(1
				(messager say: 5 6 31 0 self)
			)
			(2
				(messager say: 5 6 32 0 self)
			)
			(3
				(messager say: 5 6 33 0 self)
			)
			(4
				(messager say: 5 6 34 0 self)
			)
			(5
				(messager say: 5 6 29 0 self)
			)
			(6
				(messager say: 5 6 30 0 self)
			)
			(else  (self cue:))
		)
	)
)

(instance bowl of View
	(properties
		x 154
		y 145
		noun 30
		approachX 138
		approachY 151
		view 260
		loop 1
		cel 2
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(59 (messager say: 6 6 86))
			(10
				(if (not (Btst 236)) (ego addHonor: 20))
				(curRoom setScript: giveMoney 0 theVerb)
			)
			(4
				(curRoom setScript: giveMoney 0 theVerb)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance meatStuff of View
	(properties
		x 48
		y 7
		view 260
		signal $4010
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance clothStuff of View
	(properties
		x 207
		y 7
		view 260
		cel 1
		priority 2
		signal $4010
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance drumsUpper of View
	(properties
		x 180
		y 112
		noun 8
		view 260
		loop 1
		cel 1
		priority 10
		signal $5010
	)
)

(instance drumsLower of View
	(properties
		x 156
		y 154
		noun 8
		view 260
		loop 1
		priority 12
		signal $5010
	)
)

(instance kattaStuff of View
	(properties
		x 73
		y 38
		view 260
		cel 2
		priority 1
		signal $4010
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance water of Feature
	(properties
		x 10
		y 150
		noun 7
		onMeCheck $0040
	)
)

(instance musical_sticks of Feature
	(properties
		x 189
		y 118
		noun 9
		nsTop 112
		nsLeft 176
		nsBottom 125
		nsRight 203
		sightAngle 180
	)
)

(instance upper_katta_carvings of Feature
	(properties
		x 60
		y 42
		noun 10
		nsTop 30
		nsLeft 53
		nsBottom 42
		nsRight 67
		sightAngle 180
	)
)

(instance lower_katta_carvings of Feature
	(properties
		x 81
		y 44
		noun 11
		nsTop 44
		nsLeft 72
		nsBottom 53
		nsRight 90
		sightAngle 180
	)
)

(instance kattarug of Feature
	(properties
		x 80
		y 34
		noun 12
		nsTop 33
		nsLeft 53
		nsBottom 51
		nsRight 108
		sightAngle 180
	)
)

(instance animalcarcass of Feature
	(properties
		x 141
		y 24
		noun 13
		nsTop 6
		nsLeft 133
		nsBottom 43
		nsRight 150
		sightAngle 180
	)
)

(instance hotdogs of Feature
	(properties
		x 153
		y 25
		noun 14
		nsTop 6
		nsLeft 150
		nsBottom 44
		nsRight 156
		sightAngle 180
	)
)

(instance tabletop of Feature
	(properties
		x 152
		y 53
		noun 15
		nsTop 49
		nsLeft 137
		nsBottom 58
		nsRight 167
		sightAngle 180
	)
)

(instance deadducks of Feature
	(properties
		x 183
		y 19
		noun 16
		nsTop 4
		nsLeft 175
		nsBottom 29
		nsRight 191
		sightAngle 180
	)
)

(instance ham of Feature
	(properties
		x 197
		y 22
		noun 17
		nsTop 12
		nsLeft 192
		nsBottom 32
		nsRight 202
		sightAngle 180
	)
)

(instance back_meat_table of Feature
	(properties
		x 192
		y 50
		noun 18
		nsTop 35
		nsLeft 179
		nsBottom 52
		nsRight 206
		sightAngle 180
	)
)

(instance meatstand of Feature
	(properties
		x 159
		y 15
		noun 19
		nsTop -1
		nsLeft 107
		nsBottom 59
		nsRight 211
		sightAngle 180
	)
)

(instance stairs of Feature
	(properties
		x 125
		y 173
		noun 20
		nsTop 158
		nsLeft 90
		nsBottom 189
		nsRight 161
		sightAngle 180
	)
)

(instance amuletrack of Feature
	(properties
		x 228
		y 46
		noun 21
		nsTop 33
		nsLeft 219
		nsBottom 59
		nsRight 237
		sightAngle 180
	)
)

(instance amuletbeads of Feature
	(properties
		x 224
		y 22
		noun 22
		nsTop 12
		nsLeft 211
		nsBottom 33
		nsRight 237
		sightAngle 180
	)
)

(instance amuletstand of Feature
	(properties
		x 243
		y 20
		noun 23
		nsTop 4
		nsLeft 213
		nsBottom 57
		nsRight 273
		sightAngle 180
	)
)

(instance clothracks of Feature
	(properties
		x 308
		y 81
		noun 24
		nsTop 69
		nsLeft 298
		nsBottom 94
		nsRight 319
		sightAngle 180
	)
)

(instance cloth_on_tent of Feature
	(properties
		x 304
		y 33
		noun 25
		nsTop 21
		nsLeft 289
		nsBottom 45
		nsRight 319
		sightAngle 180
	)
)

(instance clothstand of Feature
	(properties
		x 282
		y 73
		noun 26
		nsTop 61
		nsLeft 246
		nsBottom 85
		nsRight 319
		sightAngle 180
	)
)

(instance morecloth of Feature
	(properties
		x 297
		y 50
		noun 28
		nsTop 43
		nsLeft 276
		nsBottom 58
		nsRight 319
		sightAngle 180
	)
)

(instance purpletents of Feature
	(properties
		x 299
		y 153
		noun 27
		nsTop 119
		nsLeft 280
		nsBottom 188
		nsRight 319
		sightAngle 180
	)
)

(instance sFx of Sound
	(properties)
)
