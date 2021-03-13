;;; Sierra Script 1.0 - (do not remove this comment)
(script# 260)
(include game.sh) (include "260.shm")
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
	theMerchant
	local2
	local3
	meatCycled =  1
	local5
	local6
	kattaGreeted
	meatGreeted
	amuletGreeted
	clothGreeted
	commStat
	egoTellTree = [0 -80 -81 -10 -82 43 -84 -38 -39 -40 -41 -42 -47 68 73 -74 999]
	[local29 2]
	local31 = [0 8 -85 9 -78 23 999]
	[local38 2]
	local40 = [0 -47 9 -78 -87 999]
	local46 = [0 48 999]
	local49 = [0 -47 999]
	[local52 3]
	aMeatSellerInit_3 = [0 59 9 -78 -88 999]
	[local76 2]
	aMeatSellerInit = [0 -75 9 78 79 999]
	theAMeatSellerInit_2 = [0 76 77 999]
	aMeatSellerInit_2 = [0 -75 999]
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
	(= meatCycled 0)
	([aMeatSellerInit_3_2 local3]
		cel: 0
		setCycle: EndLoop aMeatSeller
	)
)

(instance rm260 of Room
	(properties
		noun N_ROOM
		picture 260
		horizon -300
	)
	
	(method (init)
		(self setRegions: BAZAAR)
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
		(if (or Night (Btst fVisitedBazaar))
			(curRoom
				addObstacle:
					((Polygon new:)
						type: PBarredAccess
						init:
							319 0
							319 84
							267 68
							225 62
							190 54
							124 64
							59 21
							149 0
						yourself:
					)
					((Polygon new:)
						type: PBarredAccess
						init:
							0 189
							0 68
							141 161
							125 165
							99 177
							93 186
							319 185
							318 189
						yourself:
					)
			)
		else
			(cSound fade: 127 10 5 0)
			(curRoom
				addObstacle:
					((Polygon new:)
						type: PBarredAccess
						init:
							150 183
							150 144
							171 144
							171 127
							213 127
							225 153
							219 174
							193 183
						yourself:
					)
					((Polygon new:)
						type: PBarredAccess
						init:
							319 0
							319 189
							0 189
							0 66
							138 159
							99 187
							295 187
							295 111
							241 79
							266 73
							263 68
							234 71
							214 59
							193 63
							201 73
							152 84
							109 74
							103 58
							80 60
							36 34
							64 27
							65 0
						yourself:
					)
			)
			(= [local29 0] @egoTellTree)
			(egoTeller init: ego @egoTellTree @local29)
			(if (not (Btst fStoleFromDrummer))
				(bowl approachVerbs: V_DO V_LOOK V_ROYALS V_DINARS init: addToPic:)
			)
			(kattaVendor
				init:
				goods:
					((List new:)
						add: ((Ware new: N_CARVING)
							price: 5
							quantity: (if (Btst fGotCarvedLeopard) 0 else 1)
						)
					)
			)
			(= [local38 0] @local31)
			(kattaTeller init: aKattaMerchant @local31 @local38)
			(aKattaMerchant
				init:
				actions: kattaTeller
				approachVerbs: V_TALK V_DINARS V_ROYALS
				stopUpd:
			)
			(meatVendor
				init:
				goods:
					((List new:)
						add: ((Ware new: N_MEAT)
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
				approachVerbs: V_TALK V_DINARS V_ROYALS
				stopUpd:
			)
			(= [local76 0] @aMeatSellerInit_3)
			(amuletTeller
				init: aAmuletSeller @aMeatSellerInit_3 @local76
			)
			(aAmuletUpper
				actions: amuletTeller
				approachVerbs: V_TALK V_DINARS V_ROYALS
				stopUpd:
			)
			(aAmuletSeller
				actions: amuletTeller
				approachVerbs: V_TALK V_DINARS V_ROYALS
				init:
				stopUpd:
			)
			(clothVendor
				init:
				goods:
					((List new:)
						add:
							((Ware new: N_CLOTH)
								price: 10
								quantity: (if (Btst fGotRobe) 0 else 1)
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
				approachVerbs: V_TALK V_DINARS V_ROYALS
				stopUpd:
			)
			(aDrummer
				init:
				approachVerbs: V_DO V_TALK V_DINARS V_ROYALS
				setScript: drummerScript
			)
			(= [aMeatSellerInit_3_2 0] (aMeatSeller init:))
			(= [aMeatSellerInit_3_2 1] (aAmuletUpper init:))
			(= [aMeatSellerInit_3_2 2] (aClothSeller init:))
		)
		(if (and (not Night) (not (Btst fVisitedBazaar)))
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
		(if (and (not Night) (not (Btst fVisitedBazaar)))
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
			(V_LOOK
				(messager
					say: N_ROOM V_LOOK 0 (if (or (Btst fVisitedBazaar) Night) 0 else 1)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance dayCode of Code
	
	(method (doit)
		(if meatCycled
			(localproc_06a7)
		)
		(cond 
			((curRoom script?) 0)
			((< (ego x?) 5)
				(curRoom setScript: sExit)
			)
			((< (ego y?) 35)
				(curRoom setScript: sExit)
			)
			((& (ego onControl: origin) cGREEN)
				(if (not kattaGreeted)
					(curRoom setScript: kattaGreet)
					(= kattaGreeted TRUE)
				)
			)
			((& (ego onControl: origin) cCYAN)
				(if (not meatGreeted)
					(curRoom setScript: meatGreet)
					(= meatGreeted TRUE)
				)
			)
			((& (ego onControl: origin) cRED)
				(if (not amuletGreeted)
					(curRoom setScript: amuletGreet)
					(= amuletGreeted TRUE)
				)
			)
			((and (& (ego onControl: origin) cMAGENTA) (not clothGreeted))
				(curRoom setScript: clothGreet)
				(= clothGreeted TRUE)
			)
		)
	)
)

(instance nightCode of Code

	(method (doit)
		(cond 
			((curRoom script?) 0)
			((< (ego x?) 5)
				(curRoom setScript: sExit)
			)
			((< (ego y?) 35)
				(curRoom setScript: sExit)
			)
		)
	)
)

(instance giveMoney of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 4
					cel: 0
					solvePuzzle: fGiveMoneyToDrummer 3
					setCycle: EndLoop self
				)
			)
			(1
				(cond 
					((== register V_DO)
						(cond 
							((and [oldStats STEALTH] (not (Btst fStoleFromDrummer)))
								(cond 
									((== ((inventory at: iRoyals) message?) V_DINARS)
										(messager say: N_MERCHANTS V_DOIT C_WRONG_MONEY 0 self)
									)
									((and (== ((inventory at: iRoyals) amount?) 0) (not commons))
										(messager say: N_MERCHANTS V_DOIT C_NO_MONEY 0 self)
									)
									(else
										(if commons
											(-= commons 1)
										else
											((inventory at: iRoyals)
												amount: (- ((inventory at: iRoyals) amount?) 1)
											)
											(-= commons 99)
										)
										(sFx number: 260 play:)
										(if (< (ego trySkill: STEALTH 150) 0)
											(messager say: N_MERCHANTS V_DOIT C_STEAL_FAIL 0 self)
										else
											((inventory at: iRoyals)
												amount: (+ ((inventory at: iRoyals) amount?) 1)
											)
											(Bset fStoleFromDrummer)
											(messager say: N_MERCHANTS V_DOIT C_STEAL_SUCCESS 0 self)
										)
									)
								)
							)
							((and [oldStats STEALTH] (Btst fStoleFromDrummer))
								(messager say: N_MERCHANTS V_DOIT C_ALREADY_STOLE 0 self)
							)
							((== ((inventory at: iRoyals) message?) V_DINARS)
								(messager say: N_MERCHANTS V_DOIT C_WRONG_MONEY 0 self)
							)
							(else
								(= register V_ROYALS)
								(self changeState: (-= state 1))
								(self cue:)
							)
						)
					)
					((== ((inventory at: iRoyals) amount?) 0)
						(if (< commons 11)
							(messager say: N_MERCHANTS V_DOIT C_NO_MONEY 0 self)
						else
							(-= commons 10)
							(sFx number: 260 play:)
							(messager say: N_MERCHANTS V_DOIT C_DONE_DEAL 0 self)
							(ego addHonor: 5)
						)
					)
					(else
						(sFx number: 260 play:)
						(if (< commons 11)
							((inventory at: iRoyals)
								amount: (- ((inventory at: iRoyals) amount?) 1)
							)
							(-= commons 90)
						else
							(-= commons 10)
						)
						(messager say: N_MERCHANTS V_DOIT C_DONE_DEAL 0 self)
						(ego addHonor: 5)
					)
				)
			)
			(2
				(ego setCycle: BegLoop self)
			)
			(3
				(ego normalize:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance clothGreet of Script
	
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
				(messager say: N_CLOTH V_DOIT C_GREET 0 self)
			)
			(3
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance meatGreet of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 181 79 self)
			)
			(1
				(if (aMeatSeller cycler?)
					(= meatCycled TRUE)
				)
				(aMeatSeller setCel: 8 setLoop: 1 setCycle: CycleTo 0 -1 self)
			)
			(2
				(Face ego aMeatSeller self)
			)
			(3
				(aMeatSeller cel: 0 setLoop: 2 setCycle: EndLoop self)
			)
			(4
				(switch (mod Day 6)
					(0
						(messager say: N_MEAT V_DOIT C_GREET1 0 self)
					)
					(1
						(messager say: N_MEAT V_DOIT C_GREET2 0 self)
					)
					(2
						(messager say: N_MEAT V_DOIT C_GREET3 0 self)
					)
					(3
						(messager say: N_MEAT V_DOIT C_GREET4 0 self)
					)
					(4
						(messager say: N_MEAT V_DOIT C_GREET5 0 self)
					)
					(5
						(messager say: N_MEAT V_DOIT C_GREET6 0 self)
					)
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
					(aKattaMerchant setCycle: EndLoop self)
				else
					(= cycles 15)
				)
				(Face ego aKattaMerchant)
			)
			(2
				(switch (mod Day 6)
					(0
						(messager say: N_CARVING V_DOIT C_GREET1 0 self)
					)
					(1
						(messager say: N_CARVING V_DOIT C_GREET2 0 self)
					)
					(2
						(messager say: N_CARVING V_DOIT C_GREET3 0 self)
					)
					(3
						(messager say: N_CARVING V_DOIT C_GREET4 0 self)
					)
					(4
						(messager say: N_CARVING V_DOIT C_GREET5 0 self)
					)
					(5
						(messager say: N_CARVING V_DOIT C_GREET6 0 self)
					)
				)
			)
			(3
				(aKattaMerchant setCycle: CycleTo 0 -1 self)
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
					(0
						(messager say: N_AMULET V_DOIT C_GREET1 0 self)
					)
					(1
						(messager say: N_AMULET V_DOIT C_GREET2 0 self)
					)
					(2
						(messager say: N_AMULET V_DOIT C_GREET3 0 self)
					)
					(3
						(messager say: N_AMULET V_DOIT C_GREET4 0 self)
					)
					(4
						(messager say: N_AMULET V_DOIT C_GREET5 0 self)
					)
					(5
						(messager say: N_AMULET V_DOIT C_GREET6 0 self)
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

(instance purchaseLeopard of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: N_EGO_TELL V_TELL C_BUY_LEOPARD 0 self)
			)
			(1
				(kattaVendor purchase:)
				(= cycles 1)
			)
			(2
				(self dispose:)
			)
		)
	)
)

(instance from250 of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego x: 20 y: 0 edgeHit: 0 setMotion: PolyPath 40 53 self)
			)
			(1
				(ego setHeading: 135 self)
			)
			(2
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sExit of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(cond 
					((<= (ego x?) 5)
						(= register 250)
						(ego setMotion: PolyPath 0 0 self)
					)
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

	(method (changeState newState)
		(switch (= state newState)
			(0
				(aDrummer setLoop: (Random 0 1) setCycle: Forward)
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
			(2
				(cSound play:)
				(self init:)
			)
		)
	)
)

(instance egoTeller of Teller
	
	(method (respond)
		(return
			(if (not local2)
				(super respond:)
			else
				(= local2 0)
				(cond 
					((not query)
						(return TRUE)
					)
					((== query -999)
						(return TRUE)
					)
					((== query 999)
						(self doParent:)
						(return FALSE)
					)
					((and (< query 0) (not (self doChild: query)))
						(return TRUE)
					)
				)
				(if (< query 0)
					(= query (- query))
				)
				(messager say: (client noun?) V_TELL query 0)
				(return TRUE)
			)
		)
	)
	
	(method (showDialog &tmp dist)
		(= theMerchant (proc51_1))
		(= dist (ego distanceTo: theMerchant))
		(switch theMerchant
			(aKattaMerchant
				(if (> dist 35)
					(messager say: N_MERCHANTS V_DOIT C_WHICH_ONE)
					(return -999)
				)
			)
			(aMeatSeller
				(if (> dist 35)
					(messager say: N_MERCHANTS V_DOIT C_WHICH_ONE)
					(return -999)
				)
			)
			(aClothSeller
				(if (> dist 25)
					(messager say: N_MERCHANTS V_DOIT C_WHICH_ONE)
					(return -999)
				)
			)
			(else 
				(if (> dist 15)
					(messager say: N_MERCHANTS V_DOIT C_WHICH_ONE)
					(return -999)
				)
			)
		)
		(if
			(!=
				(ego heading?)
				(GetAngle (ego x?) (ego y?) (theMerchant x?) (theMerchant y?))
			)
			(Face ego theMerchant)
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
						((== theMerchant aKattaMerchant)
							(= query C_HELLO_KATTA)
						)
						((== theMerchant aMeatSeller)
							(= query C_HELLO_MEAT)
						)
						((== theMerchant aAmuletUpper)
							(= query C_HELLO_AMULET)
						)
						((== theMerchant aClothSeller)
							(= query C_HELLO_CLOTH)
						)
					)
				)
				(-81
					(cond 
						((== theMerchant aKattaMerchant)
							(= query C_GOODBYE_KATTA)
						)
						((== theMerchant aMeatSeller)
							(= query C_GOODBYE_MEAT)
						)
						((== theMerchant aAmuletUpper)
							(= query C_GOODBYE_AMULET)
						)
						((== theMerchant aClothSeller)
							(= query C_GOODBYE_CLOTH)
						)
					)
				)
				(-10
					(if (not (Btst fTellKattaAboutShapeir))
						(ego addHonor: 25)
					)
					(ego solvePuzzle: fTellKattaAboutShapeir 3)
					(if (== local6 6)
						(ego addHonor: 5)
						(messager say: N_EGO_TELL V_TELL C_MORE_SHAPEIR)
						(return 0)
					else
						(return query)
					)
				)
				(-84
					(cond 
						((== theMerchant aKattaMerchant)
							(= query C_THIEF_SIGN_KATTA)
						)
						((== theMerchant aMeatSeller)
							(= query C_THIEF_SIGN_MEAT)
						)
						((== theMerchant aAmuletUpper)
							(= query C_THIEF_SIGN_AMULET)
						)
						((== theMerchant aClothSeller)
							(= query C_THIEF_SIGN_CLOTH)
						)
					)
				)
				(-82
					(kattaTeller doVerb: V_ROYALS)
					(return 0)
				)
				(-38
					(ego addHonor: 10)
					(return TRUE)
				)
				(-39
					(ego addHonor: 10)
					(return TRUE)
				)
				(-40
					(ego addHonor: 5)
					(return TRUE)
				)
				(-41
					(ego addHonor: 5)
					(return TRUE)
				)
				(-42
					(ego addHonor: 5)
					(return TRUE)
				)
				(-47
					(if (== ((inventory at: iRoyals) message?) V_DINARS)
						(messager say: N_MEAT V_DOIT C_WRONG_MONEY)
					else
						(meatVendor purchase:)
					)
					(return FALSE)
				)
				(-74
					(if (== ((inventory at: iRoyals) message?) V_DINARS)
						(messager say: N_CLOTH V_DOIT C_WRONG_MONEY)
					else
						(clothVendor purchase:)
					)
					(return FALSE)
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
						(== theMerchant aKattaMerchant)
					else
						0
					)
					-38
					(if (== theMerchant aKattaMerchant) (== local6 2) else 0)
					-39
					(if (== theMerchant aKattaMerchant) (== local6 4) else 0)
					-40
					(if (== theMerchant aKattaMerchant) (== local6 6) else 0)
					-41
					(if (== theMerchant aKattaMerchant) (== local5 3) else 0)
					-42
					(if (== theMerchant aKattaMerchant) (== local5 5) else 0)
					-82
					(== theMerchant aKattaMerchant)
					43
					(if (and (== theMerchant aKattaMerchant) (Btst fDispelledLeopardman))
						(not (Btst fGiftedCarvedLeopard))
					else
						0
					)
					-84
					(== heroType THIEF)
					-47
					(== theMerchant aMeatSeller)
					68
					(== theMerchant aAmuletSeller)
					73
					(== theMerchant aClothSeller)
					-74
					(if (== theMerchant aClothSeller) (not (Btst fBoughtCloth)) else 0)
			)
		)
		(= local2 1)
		(if iconValue
			(= query iconValue)
		)
		(egoTeller respond:)
	)
)

(instance kattaTeller of Teller
	
	(method (showDialog)
		(super showDialog: C_PIN (ego has: iPin))
	)
	
	(method (doChild)
		(switch query
			(-78
				(if (Btst fDispelledLeopardman)
					(= query C_SIMBANI_NEWS2)
				else
					(switch (mod Day 6)
						(0 (= query C_RUMOR1)
							(= local5 1)
						)
						(1 (= query C_RUMOR3)
							(= local5 2)
						)
						(2 (= query C_RUMOR2)
							(= local5 3)
						)
						(3 (= query C_RUMOR4)
							(= local5 4)
						)
						(4 (= query C_RUMOR5)
							(= local5 5)
						)
						(5 (= query C_RUMOR6)
							(= local5 6)
						)
					)
				)
			)
			(-85
				(if (Btst fDispelledLeopardman)
					(= query C_SIMBANI_NEWS3)
				else
					(switch (mod Day 6)
						(0
							(= query C_KATTA_SELF1)
							(= local6 1)
						)
						(1
							(= query C_KATTA_SELF2)
							(= local6 2)
						)
						(2
							(= query C_KATTA_SELF3)
							(= local6 3)
						)
						(3
							(= query C_KATTA_SELF4)
							(= local6 4)
						)
						(4
							(= query C_KATTA_SELF5)
							(= local6 5)
						)
						(5
							(= query C_KATTA_SELF5)
							(= local6 6)
						)
					)
				)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DINARS
				(messager say: N_CARVING V_DOIT C_WRONG_MONEY)
			)
			(V_ROYALS
				(cond 
					((and (Btst fGaveKattaNote) (not (Btst fGiftedCarvedLeopard)))
						(messager say: N_EGO_TELL V_TELL C_FREEBIE_LEOPARD)
						(Bset fGiftedCarvedLeopard)
						(ego
							get: iLeopard
							solvePuzzle: fBuyCarvedLeopard 2
						)
					)
					((not (Btst fGiftedCarvedLeopard))
						(if (== ((inventory at: iRoyals) message?) V_DINARS)
							(messager say: N_CARVING V_DOIT C_WRONG_MONEY)
						else
							(curRoom setScript: purchaseLeopard)
						)
					)
					(else
						(messager say: N_EGO_TELL V_TELL C_NO_MORE_CARVINGS)
					)
				)
			)
			(V_NOTE
				(Bset fGaveKattaNote)
				(ego drop: iNote addHonor: 50 solvePuzzle: fGiveNote 5)
				(messager say: N_CARVING V_NOTE NULL)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance meatTeller of Teller

	(method (doChild)
		(switch query
			(-78
				(switch (mod Day 6)
					(0
						(= query C_RUMOR1)
					)
					(1
						(= query C_RUMOR3)
					)
					(2
						(= query C_RUMOR2)
					)
					(3
						(= query C_RUMOR4)
					)
					(4
						(= query C_RUMOR5)
					)
					(5
						(= query C_RUMOR6)
					)
				)
			)
			(-87
				(switch (mod Day 6)
					(0
						(= query C_SELF_MEAT1)
					)
					(1
						(= query C_SELF_MEAT2)
					)
					(2
						(= query C_SELF_MEAT3)
					)
					(3
						(= query C_SELF_MEAT4)
					)
					(4
						(= query C_SELF_MEAT5)
					)
					(5
						(= query C_SELF_MEAT6)
					)
				)
			)
			(else
				(super doChild: &rest)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DINARS
				(messager say: N_MEAT V_DOIT C_WRONG_MONEY)
			)
			(V_ROYALS
				(meatVendor purchase:)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance amuletTeller of Teller
	
	(method (doChild)
		(switch query
			(-78
				(switch (mod Day 6)
					(0
						(= query C_RUMOR1)
					)
					(1
						(= query C_RUMOR3)
					)
					(2
						(= query C_RUMOR2)
					)
					(3
						(= query C_RUMOR4)
					)
					(4
						(= query C_RUMOR5)
					)
					(5
						(= query C_RUMOR6)
					)
				)
			)
			(-88
				(switch (mod Day 6)
					(0
						(= query C_SELF_AMULET1)
					)
					(1
						(= query C_SELF_AMULET2)
					)
					(2
						(= query C_SELF_AMULET3)
					)
					(3
						(= query C_SELF_AMULET4)
					)
					(4
						(= query C_SELF_AMULET5)
					)
					(5
						(= query C_SELF_AMULET6)
					)
				)
			)
		)
	)
	
	(method (doVerb theVerb)
		(if (or (== theVerb V_DINARS) (== theVerb V_ROYALS))
			(messager say: N_EGO_TELL V_TELL C_BUY_AMULET)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance clothTeller of Teller

	(method (doVerb theVerb)
		(switch theVerb
			(V_DINARS
				(messager say: N_CLOTH V_DOIT C_WRONG_MONEY)
			)
			(V_ROYALS
				(if (Btst fBoughtCloth)
					(messager say: N_EGO_TELL V_TELL C_BUY_CLOTH)
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
		noun N_CARVING
		approachX 63
		approachY 52
		view 252
		signal fixPriOn
		detailLevel 3
	)
)

(instance aMeatSeller of Actor
	(properties
		x 175
		y 68
		noun N_MEAT
		approachX 181
		approachY 79
		view 264
		loop 1
		priority 3
		signal (| ignrAct fixPriOn)
		cycleSpeed 8
		detailLevel 3
	)
	
	(method (init)
		(super init:)
		(return self)
	)
	
	(method (cue)
		([aMeatSellerInit_3_2 local3] stopUpd:)
		(= meatCycled TRUE)
	)
)

(instance aAmuletUpper of Actor
	(properties
		x 247
		y 66
		z 30
		noun N_AMULET
		approachX 259
		approachY 69
		view 266
		priority 3
		signal (| ignrAct fixPriOn)
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
		noun N_AMULET
		approachX 259
		approachY 69
		view 266
		loop 1
		priority 3
		signal (| ignrAct fixPriOn)
		cycleSpeed 8
		detailLevel 3
	)
)

(instance aClothSeller of Actor
	(properties
		x 281
		y 87
		noun N_CLOTH
		approachX 263
		approachY 84
		view 248
		loop 1
		signal (| ignrAct skipCheck)
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
		noun N_DRUMMER
		approachX 138
		approachY 151
		view 256
		cel 5
		priority 11
		signal (| ignrAct fixPriOn)
		cycleSpeed 9
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DINARS
				(bowl doVerb: theVerb)
			)
			(V_ROYALS
				(bowl doVerb: theVerb)
			)
			(else
				(super doVerb: theVerb)
			)
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
		noun N_CARVING
	)
	
	(method (transact what howMany)
		(= theVendor self)
		(Bset fGiftedCarvedLeopard)
		(ego
			get: iLeopard howMany
			solvePuzzle: fBuyCarvedLeopard 2
		)
		(Buy what howMany)
		(if howMany
			(messager say: N_CARVING V_DOIT C_DONE_DEAL 0 self)
		)
		(Bset fGotCarvedLeopard)
	)
	
	(method (doBargain result)
		(switch result
			(bargainSUCCESS
				(messager say: N_CARVING V_DOIT C_BARGAIN_SUCCESS 0 self)
			)
			(bargainTRY1
				(messager say: N_CARVING V_DOIT C_BARGAIN_TRY1 0 self)
			)
			(bargainTRY2
				(messager say: N_CARVING V_DOIT C_BARGAIN_TRY2 0 self)
			)
			(bargainTRY3
				(messager say: N_CARVING V_DOIT C_BARGAIN_TRY3 0 self)
			)
			(bargainFAIL
				(messager say: N_CARVING V_DOIT C_BARGAIN_FAIL 0 self)
			)
			(bargainNODEAL
				(messager say: N_CARVING V_DOIT C_BARGAIN_NO_DEAL 0 self)
			)
			(else
				(self cue:)
			)
		)
	)
)

(instance meatVendor of Vendor
	(properties
		noun N_MEAT
	)
	
	(method (bargain)
		;will accept any price
		(= commStat [egoStats COMM])
		(= [egoStats COMM] 550)
		(super bargain: &rest)
	)
	
	(method (transact what howMany)
		(= theVendor self)
		(ego get: iMeat howMany)
		(Buy what howMany)
		(messager say: N_MEAT V_DOIT C_DONE_DEAL 0 self)
	)
	
	(method (doBargain)
		(= [egoStats COMM] commStat)
		(messager say: N_MEAT V_DOIT C_BARGAIN_SUCCESS 0 self)
	)
)

(instance clothVendor of Vendor
	(properties
		noun N_CLOTH
	)
	
	(method (transact what howMany)
		(= theVendor self)
		(ego
			get: 40 howMany
			solvePuzzle: fBuyRobe 2
		)
		(Buy what howMany)
		(if howMany
			(messager say: N_CLOTH V_DOIT C_DONE_DEAL 0 self)
			(Bset fGotRobe)
			(Bset fBoughtCloth)
		)
	)
	
	(method (doBargain result)
		(switch result
			(bargainSUCCESS
				(messager say: N_CLOTH V_DOIT C_BARGAIN_SUCCESS 0 self)
			)
			(bargainTRY1
				(messager say: N_CLOTH V_DOIT C_BARGAIN_TRY1 0 self)
			)
			(bargainTRY2
				(messager say: N_CLOTH V_DOIT C_BARGAIN_TRY2 0 self)
			)
			(bargainTRY3
				(messager say: N_CLOTH V_DOIT C_BARGAIN_TRY3 0 self)
			)
			(bargainFAIL
				(messager say: N_CLOTH V_DOIT C_BARGAIN_FAIL 0 self)
			)
			(bargainNODEAL
				(messager say: N_CLOTH V_DOIT C_BARGAIN_NO_DEAL 0 self)
			)
			(else
				(self cue:)
			)
		)
	)
)

(instance bowl of View
	(properties
		x 154
		y 145
		noun N_BOWL
		approachX 138
		approachY 151
		view 260
		loop 1
		cel 2
		signal ignrAct
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DINARS
				(messager say: N_MERCHANTS V_DOIT C_WRONG_MONEY)
			)
			(V_ROYALS
				(if (not (Btst fGiveMoneyToDrummer))
					(ego addHonor: 20)
				)
				(curRoom setScript: giveMoney 0 theVerb)
			)
			(V_DO
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
		signal (| ignrAct fixPriOn)
	)
	
	(method (onMe)
		(return FALSE)
	)
)

(instance clothStuff of View
	(properties
		x 207
		y 7
		view 260
		cel 1
		priority 2
		signal (| ignrAct fixPriOn)
	)
	
	(method (onMe)
		(return FALSE)
	)
)

(instance drumsUpper of View
	(properties
		x 180
		y 112
		noun N_DRUMS
		view 260
		loop 1
		cel 1
		priority 10
		signal (| ignrAct skipCheck fixPriOn)
	)
)

(instance drumsLower of View
	(properties
		x 156
		y 154
		noun N_DRUMS
		view 260
		loop 1
		priority 12
		signal (| ignrAct skipCheck fixPriOn)
	)
)

(instance kattaStuff of View
	(properties
		x 73
		y 38
		view 260
		cel 2
		priority 1
		signal (| ignrAct fixPriOn)
	)
	
	(method (onMe)
		(return FALSE)
	)
)

(instance water of Feature
	(properties
		x 10
		y 150
		noun N_WATER
		onMeCheck cBROWN
	)
)

(instance musical_sticks of Feature
	(properties
		x 189
		y 118
		noun N_MUSICAL_STICKS
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
		noun N_UPPER_CARVINGS
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
		noun N_LOWER_CARVINGS
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
		noun N_KATTA_RUG
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
		noun N_CARCASS
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
		noun N_HOTDOGS
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
		noun N_TABLETOP
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
		noun N_DUCKS
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
		noun N_HAM
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
		noun N_BACK_MEAT_TABLE
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
		noun N_MEAT_STAND
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
		noun N_STAIRS
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
		noun N_AMULET_RACK
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
		noun N_AMULET_BEADS
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
		noun N_AMULET_STAND
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
		noun N_CLOTH_RACKS
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
		noun N_CLOTH_ON_TENT
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
		noun N_CLOTH_STAND
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
		noun N_MORECLOTH
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
		noun N_PURPLE_TENTS
		nsTop 119
		nsLeft 280
		nsBottom 188
		nsRight 319
		sightAngle 180
	)
)

(instance sFx of Sound)
