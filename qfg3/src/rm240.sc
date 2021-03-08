;;; Sierra Script 1.0 - (do not remove this comment)
(script# 240)
(include game.sh) (include "240.shm")
(use Main)
(use TellerIcon)
(use Vendor)
(use Bazaar)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Timer)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm240 0
	aWeaponSeller 2
	aSanford 3
	aSon 4
	aHoneySeller 5
	aOilSeller 6
)

(local
	weaponPitchMade =  1
	merchantNoun
	local2
	local3
	local4
	local5
	local6
	local7
	local8
	theMerchant
	walkedAway =  1
	local11
	[aWeaponSellerInit 4]
	weaponSellerCued
	local17 = [0 -92 -93 -105 -43 -72 -97 -98 999]
	local26 = [0 82 83 84 -86 -85 999]
	local33 = [0 76 77 47 78 24 999]
	local40 = [0 79 80 25 999]
	local45 = [0 104 81 26 999]
	local50 = [0 -36 35 -100 -101 999]
	local56 = [0 37 38 39 40 2 999]
	local63 = [0 -36 999]
	local66 = [0 63 35 -100 -101 999]
	local72 = [0 -48 35 -100 -101 999]
	local78 = [0 49 -91 999]
	local82 = [0 -48 999]
	local85 = [0 52 35 -100 -101 999]
	[local91 4]
	[local95 4]
	[local99 4]
	[local103 5]
	[local108 5]
	[local113 5]
	[local118 5]
)
(procedure (localproc_0892)
	(if (not (= local3 (Random 0 3)))
		(if
			(or
				(== (weaponSellerFirst state?) -1)
				(== (weaponSellerFirst state?) 10)
			)
			(= local3 0)
		else
			(= local3 1)
		)
	)
	(= weaponPitchMade 0)
	([aWeaponSellerInit local3]
		cel: 0
		setCycle: EndLoop aOilSeller
	)
)

(procedure (DisposeVendor who)
	(switch who
		(1 (DisposeScript 245))
		(7 (DisposeScript 246))
		(6 (DisposeScript 246))
		(4 (DisposeScript 247))
		(5 (DisposeScript 248))
	)
)

(instance rm240 of Room
	(properties
		noun N_ROOM
		picture 240
		horizon -20
		vanishingY -300
	)
	
	(method (init)
		(LoadMany RES_MESSAGE 240)
		(walkHandler addToFront: self)
		(self setRegions: BAZAAR)
		(ego
			noun: N_EGO_TELL
			init:
			normalize:
			edgeHit: 0
			scaleSignal: scalable
			scaleX: 120
			scaleY: 120
		)
		(if (or Night (Btst fVisitedBazaar))
			(curRoom
				addObstacle:
					((Polygon new:)
						type: PBarredAccess
						init:
							319 189
							283 189
							239 149
							319 137
						yourself:
					)
					((Polygon new:)
						type: PBarredAccess
						init:
							0 95
							0 0
							319 0
							319 104
							284 104
							255 89
							230 104
							128 118
							109 108
							52 114
						yourself:
					)
			)
			(if (and (> Day 4) (not (Btst fHaramiGone)))
				(|= local11 $2000)
				(if (and (!= haramiNight Day) (Btst fMetHonorlessHarami))
					(if (Btst fHaramiSecondVisit)
						(Bset fHaramiThirdVisit)
					else
						(Bset fHaramiSecondVisit)
					)
				)
				(ego code: nightCode)
				((ScriptID 241 0)
					view: 954
					loop: 1
					cel: 0
					x: 149
					y: 105
					init:
					scaleSignal: scalable
					scaleX: 120
					scaleY: 120
				)
				(= [local95 0] @local26)
				(nightTell init: ego @local26 @local95)
			else
				(ego code: nightCodeX)
			)
		else
			(if (!= (globalSound number?) 924)
				(globalSound number: 924 setLoop: -1 play: 127)
			)
			(cond 
				(
					(or
						(and
							(!= (cSound number?) 923)
							(!= (cSound number?) 230)
						)
						(and
							(== (cSound number?) 923)
							(== (cSound prevSignal?) -1)
						)
					)
					(cSound setLoop: -1 number: 923 play: 60)
				)
				((!= (cSound number?) 230)
					(cSound fade: 60 10 5 0)
				)
				(else
					(cSound client: self)
				)
			)
			(curRoom
				addObstacle:
					((Polygon new:)
						type: PBarredAccess
						init:
							240 189
							141 189
							114 165
							218 150
							262 186
						yourself:
					)
					((Polygon new:)
						type: PBarredAccess
						init:
							319 189
							273 189
							236 146
							319 137
						yourself:
					)
					((Polygon new:)
						type: PBarredAccess
						init:
							0 100
							0 0
							319 0
							319 98
							291 102
							270 93
							271 105
							187 117
							51 117
						yourself:
					)
			)
			(= [local91 0] @local17)
			(egoTell init: ego @local17 @local91)
			(= [local118 0] @local66)
			(junkTell init: aSanford @local66 @local118)
			(= [local108 0] @local85)
			(oilTell init: aOilSeller @local85 @local108)
			(= [local103 0] @local50)
			(= [local103 1] @local56)
			(= [local103 2] @local56)
			(weaponTell
				init: aWeaponSeller @local50 @local103 @local63
			)
			(= [local113 0] @local72)
			(= [local113 1] @local78)
			(honeyTell init: aHoneySeller @local72 @local113 @local82)
			(ego code: bazCode)
			(mooseHead init: addToPic:)
			(if (and (== heroType THIEF) (not (Btst fGotBlackBird)))
				(blackBird init: stopUpd:)
			)
			(spearsLeft setPri: 7 init: addToPic:)
			(spearsRight setPri: 8 init: addToPic:)
			(junkRight setPri: 0 init: addToPic:)
			(honeyOnShelf setPri: 11 init: addToPic:)
			(oilRug setPri: 0 init: addToPic:)
			(oilBottles setPri: 14 init: addToPic:)
			(= [aWeaponSellerInit 0] (aWeaponSeller init:))
			(aSanford init: stopUpd:)
			(= [aWeaponSellerInit 1] (aSon init:))
			(= [aWeaponSellerInit 2] (aHoneySeller init:))
			(= [aWeaponSellerInit 3] (aOilSeller init:))
			(aWeaponSeller stopUpd:)
			(aSon stopUpd:)
			(aHoneySeller stopUpd:)
			(aOilSeller stopUpd:)
			(leftoilbottles init:)
			(bigoilbottle init:)
			(rightoilbottles init:)
			(oilrug init:)
			(weapon_stand init:)
			(honeystand init:)
			(pansonrope init:)
			(leftjunk init:)
			(junkdealertent init:)
			(purplepot init:)
			(platerows init:)
			(righttable init:)
			(largeurns init:)
		)
		(super init: &rest)
		(HandsOn)
		(switch prevRoomNum
			(230
				(= style (| BLACKOUT IRISOUT))
				(if (and (< Day 5) (Btst fHaramiGone) (not (Btst fHaramiWasOnTrial)))
					((ScriptID 241 0) init: addToPic:)
					((View new:)
						view: 240
						loop: 2
						cel: 1
						x: 146
						y: 117
						init:
						addToPic:
					)
					(aWeaponSeller addToPic:)
					(aSon addToPic:)
					(aHoneySeller addToPic:)
					(aOilSeller addToPic:)
					(aSanford addToPic:)
					(self setScript: thiefChase self)
				else
					(self setScript: from230)
				)
			)
			(250
				(= style (| BLACKOUT PIXELDISSOLVE))
				(self setScript: from250)
			)
			(270
				(= style (| BLACKOUT PIXELDISSOLVE))
				(self setScript: from270)
			)
			(else
				(ego x: 35 y: 120)
			)
		)
	)
	
	(method (dispose)
		(= haramiNight Day)
		(Bclr fFedHarami)
		(if local5
			(local5 dispose:)
		)
		(if local6
			(local6 dispose:)
		)
		(if local7
			(local7 dispose:)
		)
		(if local8
			(local8 dispose:)
		)
		(walkHandler delete: self)
		(UnLoad RES_MESSAGE 240)
		(LoadMany FALSE 47 241 245 246 247 248)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: noun V_LOOK NULL (if Night 0 else 1))
			)
			(V_WALK
				(if (& local11 $2000)
					(if (and ((ScriptID 241 0) cycler?) (& local11 $1000))
						(&= local11 $efff)
						((ScriptID 241 0) setCycle: BegLoop)
					)
					(if (and (not (Btst fFedHarami)) (not walkedAway))
						(= walkedAway TRUE)
						(messager say: N_HARAMI V_DOIT C_LEAVE_HARAMI)
					)
				else
					(if (self script?)
						(self script: 0)
						(aWeaponSeller setCycle: EndLoop setLoop: 2)
					)
					(if (CueObj theVerb?)
						(CueObj theVerb: 0)
					)
					(if merchantNoun
						(messager say: merchantNoun V_DOIT C_DONE_DEAL 0 aSon)
					)
				)
				(ego
					setMotion:
						PolyPath
						((User curEvent?) x?)
						((User curEvent?) y?)
						aWeaponSeller
				)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
	
	(method (cue)
		(if (== (globalSound number?) 231)
			(cSound pause: 0)
			(globalSound client: 0 number: 924 setLoop: -1 play: 127)
		)
	)
)

(instance haramiBeg of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Bset fMetHarami)
				(switch prevRoomNum
					(270
						(ego setMotion: PolyPath 220 111 self)
					)
					(230
						(ego setMotion: PolyPath 20 115 self)
					)
					(250
						(ego setMotion: PolyPath 87 175 self)
					)
				)
			)
			(1
				(= cycles 5)
			)
			(2
				(cond 
					((Btst fHaramiThirdVisit)
						(Bset fHaramiGone)
						(messager say: N_HARAMI V_DOIT C_GREETING4 0 self)
					)
					((Btst fHaramiSecondVisit)
						(messager say: N_HARAMI V_DOIT C_GREETING3 0 self)
					)
					((Btst fMetHonorlessHarami)
						(messager say: N_HARAMI V_DOIT C_AGREED_MEET 0 self)
					)
					(else
						(messager say: N_HARAMI V_DOIT C_FIRST_MEET 0 self)
						(Bset fMetHonorlessHarami)
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

(instance weaponSellerFirst of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (aWeaponSeller cycler?)
					(= weaponPitchMade 1)
				)
				(aWeaponSeller setCycle: BegLoop self)
				(ego setMotion: 0)
				(aSon setCycle: 0)
			)
			(1
				(Face ego aWeaponSeller)
				(= cycles (+ (ego cycleSpeed?) 15))
			)
			(2
				(messager say: N_WEAPON V_DOIT C_SALES_PITCH 1 self)
			)
			(3
				(aWeaponSeller setLoop: 2 cel: 6 setCycle: CycleTo 3 -1 self)
			)
			(4
				(messager say: N_WEAPON V_DOIT C_DAGGER 0 self)
			)
			(5
				(aWeaponSeller cel: 0 setCycle: EndLoop self)
			)
			(6
				(HandsOn)
				(DisposeVendor 1)
				(Bset fMetWeaponSeller)
				(aWeaponSeller stopUpd:)
				(= weaponPitchMade TRUE)
				(self dispose:)
			)
		)
	)
)

(instance thiefChase of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					x: -20
					y: 110
					code: 0
					setMotion: PolyPath 85 120 self
				)
			)
			(1
				(= cycles 5)
			)
			(2
				(messager say: N_GUARD V_DOIT C_THIEF 0 self)
			)
			(3
				(HandsOff)
				(globalSound fade:)
				(soundFx fade:)
				(curRoom newRoom: 340)
			)
		)
	)
)

(instance aSonGreeting of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: 0)
				(= cycles 5)
			)
			(1
				(Face ego aSanford)
				(= cycles (+ (ego cycleSpeed?) 15))
			)
			(2
				(aSanford setCycle: EndLoop self)
			)
			(3
				(messager say: N_JUNK V_DOIT 62 0 self)
			)
			(4
				(DisposeVendor 7)
				(aSanford setCycle: CycleTo 0 -1 self)
			)
			(5
				(HandsOn)
				(aSanford stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance from230 of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego x: -30 y: 70)
				((ScriptID 241 0) setLoop: 0)
				(= cycles 5)
			)
			(1
				(if (& local11 $2000)
					(cond 
						((Btst fHaramiThirdVisit)
							(= [local99 0] @local45)
							(haramiTell init: (ScriptID 241 0) @local45 @local99)
							(curRoom setScript: haramiBeg)
						)
						((Btst fHaramiSecondVisit)
							(|= local11 $0020)
							(= [local99 0] @local40)
							(haramiTell init: (ScriptID 241 0) @local40 @local99)
							(curRoom setScript: haramiBeg)
						)
						(else
							(|= local11 $0010)
							(= [local99 0] @local33)
							(haramiTell init: (ScriptID 241 0) @local33 @local99)
							(curRoom setScript: haramiBeg)
						)
					)
				)
				(self cue:)
			)
			(2
				(ego setMotion: PolyPath 20 115 self)
			)
			(3
				(HandsOn)
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
				(ego x: 130 y: 250)
				((ScriptID 241 0) setLoop: 0)
				(= cycles 5)
			)
			(1
				(if (& local11 $2000)
					(cond 
						((Btst fHaramiThirdVisit)
							(= [local99 0] @local45)
							(haramiTell init: (ScriptID 241 0) @local45 @local99)
							(curRoom setScript: haramiBeg)
						)
						((Btst fHaramiSecondVisit)
							(|= local11 $0020)
							(= [local99 0] @local40)
							(haramiTell init: (ScriptID 241 0) @local40 @local99)
							(curRoom setScript: haramiBeg)
						)
						(else
							(|= local11 $0010)
							(= [local99 0] @local33)
							(haramiTell init: (ScriptID 241 0) @local33 @local99)
							(curRoom setScript: haramiBeg)
						)
					)
				)
				(self cue:)
			)
			(2
				(ego setMotion: PolyPath 87 175 self)
			)
			(3
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance from270 of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego x: 330 y: 120)
				(= cycles 5)
			)
			(1
				(if (& local11 $2000)
					(cond 
						((Btst fHaramiThirdVisit)
							(= [local99 0] @local45)
							(haramiTell init: (ScriptID 241 0) @local45 @local99)
							(curRoom setScript: haramiBeg)
						)
						((Btst fHaramiSecondVisit)
							(|= local11 $0020)
							(= [local99 0] @local40)
							(haramiTell init: (ScriptID 241 0) @local40 @local99)
							(curRoom setScript: haramiBeg)
						)
						(else
							(|= local11 $0010)
							(= [local99 0] @local33)
							(haramiTell init: (ScriptID 241 0) @local33 @local99)
							(curRoom setScript: haramiBeg)
						)
					)
				)
				(self cue:)
			)
			(2
				(ego setMotion: PolyPath 300 115 self)
			)
			(3 (HandsOn) (self dispose:))
		)
	)
)

(instance begSecond of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: 0)
				(Face ego (ScriptID 241 0))
				(= cycles (+ (ego cycleSpeed?) 15))
			)
			(1
				(messager say: N_HARAMI V_DOIT C_HARAMI_BEGS 0 self)
			)
			(2 (self dispose:))
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
						(= register 230)
						(ego setMotion: PolyPath -30 (ego y?) self)
					)
					((<= (ego y?) 5)
						(= register 230)
						(ego setMotion: PolyPath (ego x?) -30 self)
					)
					((>= (ego x?) 315)
						(if (and Night (not (Btst fFedHarami)) (& local11 $2000))
							(ego addHonor: -50)
						)
						(= register 270)
						(cSound fade:)
						(globalSound fade:)
						(ego setMotion: PolyPath 340 (ego y?) self)
					)
					((>= (ego y?) 183)
						(= register 250)
						(ego setMotion: PolyPath (ego x?) 240 self)
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

(instance bazCode of Code
	
	(method (doit)
		(if weaponPitchMade (localproc_0892))
		(cond 
			((curRoom script?) 0)
			((not (if (< 5 (ego x?)) (< (ego x?) 315)))
				(curRoom setScript: sExit)
			)
			((not (if (< 5 (ego y?)) (< (ego y?) 183)))
				(curRoom setScript: sExit)
			)
			((< (ego distanceTo: aWeaponSeller) 45)
				(aWeaponSeller setCycle: 0)
				(if (and (not (Btst fMetWeaponSeller)) (not weaponSellerCued))
					(= weaponSellerCued TRUE)
					(aWeaponSeller setCycle: 0)
					(curRoom setScript: weaponSellerFirst)
				)
			)
			((< (ego distanceTo: aSanford) 25)
				(if (!= (globalSound number?) 231)
					(cSound pause: 1)
					(globalSound client: 0 number: 231 setLoop: -1 play:)
				)
				(cond 
					((not (Btst fMetSanfordAndSon)) (Bset fMetSanfordAndSon)
						(curRoom setScript: aSonGreeting)
					)
					((not (& local11 $0001))
						(|= local11 $0001)
						(= merchantNoun (aSanford noun?))
						(ego setMotion: 0)
						(aSanford newGreeting:)
					)
				)
			)
			((< (ego distanceTo: aHoneySeller) 40)
				(aHoneySeller setCycle: 0)
				(if (not (& local11 $0002))
					(|= local11 $0002)
					(ego setMotion: 0)
					(= merchantNoun (aHoneySeller noun?))
					(aHoneySeller newGreeting:)
				)
			)
			((< (ego distanceTo: aSon) 17)
				(if (!= (globalSound number?) 231)
					(cSound pause: TRUE)
					(globalSound client: 0 number: 231 setLoop: -1 play:)
				)
				(cond 
					((not (Btst fMetSanfordAndSon))
						(Bset fMetSanfordAndSon)
						(curRoom setScript: aSonGreeting)
					)
					((not (& local11 $0004))
						(|= local11 $0004)
						(ego setMotion: 0)
						(= merchantNoun (aSanford noun?))
						(aSanford newGreeting:)
					)
				)
			)
			((< (ego distanceTo: aOilSeller) 33)
				(aOilSeller setCycle: 0)
				(if (not (& local11 $0008))
					(|= local11 $0008)
					(ego setMotion: 0)
					(= merchantNoun (aOilSeller noun?))
					(aOilSeller newGreeting:)
				)
			)
			(
				(and
					(== (globalSound number?) 231)
					(== (globalSound client?) 0)
				)
				(globalSound fade: 60 5 5 0 curRoom)
			)
		)
	)
)

(instance nightCode of Code
	
	(method (doit)
		(cond 
			((curRoom script?) 0)
			((not (if (< 5 (ego x?)) (< (ego x?) 315)))
				(curRoom setScript: sExit)
			)
			((not (if (< 5 (ego y?)) (< (ego y?) 183)))
				(curRoom setScript: sExit)
			)
			((< (ego distanceTo: (ScriptID 241 0)) 30)
				(if (& local11 $0100)
					0
				else
					(|= local11 $0100)
					((ScriptID 241 0) setCycle: Forward)
					(|= local11 $1000)
					(= walkedAway FALSE)
					(curRoom setScript: begSecond)
				)
			)
		)
	)
)

(instance nightCodeX of Code
	
	(method (doit)
		(cond 
			((curRoom script?) 0)
			((not (if (< 5 (ego x?)) (< (ego x?) 315)))
				(curRoom setScript: sExit)
			)
			((not (if (< 5 (ego y?)) (< (ego y?) 183)))
				(curRoom setScript: sExit)
			)
		)
	)
)

(instance aWeaponSeller of MerchantActor
	(properties
		x 62
		y 82
		noun N_WEAPON
		approachDist 50
		view 325
		loop 2
		signal ignrAct
		cycleSpeed 12
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: V_TALK V_DO V_DINARS V_ROYALS)
		(return self)
	)
	
	(method (dispose)
		(self actions: 0)
		(super dispose:)
	)
	
	(method (cue)
		(if (& local11 $0100)
			(&= local11 $feff)
		)
	)
	
	(method (newGreeting)
		(switch (mod Day 6)
			(0
				(messager say: noun V_DOIT C_GREETING1 0 aSon)
			)
			(1
				(messager say: noun V_DOIT C_GREETING2 0 aSon)
			)
			(2
				(messager say: noun V_DOIT C_GREETING3 0 aSon)
			)
			(3
				(messager say: noun V_DOIT C_GREETING4 0 aSon)
			)
			(4
				(messager say: noun V_DOIT C_GREETING5 0 aSon)
			)
			(5
				(messager say: noun V_DOIT C_GREETING6 0 aSon)
			)
		)
	)
)

(instance aSanford of MerchantActor
	(properties
		x 241
		y 85
		noun N_JUNK
		approachDist 50
		view 242
		signal (| ignrAct skipCheck)
		cycleSpeed 19
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: V_TALK V_DO V_DINARS V_ROYALS)
	)
	
	(method (dispose)
		(self actions: 0)
		(super dispose:)
	)
	
	(method (newGreeting)
		(switch (mod Day 6)
			(0
				(messager say: noun V_DOIT C_GREETING1 0 aSon)
			)
			(1
				(messager say: noun V_DOIT C_GREETING2 0 aSon)
			)
			(2
				(messager say: noun V_DOIT C_GREETING3 0 aSon)
			)
			(3
				(messager say: noun V_DOIT C_GREETING4 0 aSon)
			)
			(4
				(messager say: noun V_DOIT C_GREETING5 0 aSon)
			)
			(5
				(messager say: noun V_DOIT C_GREETING6 0 aSon)
			)
		)
	)
)

(instance aSon of MerchantActor
	(properties
		x 209
		y 104
		noun N_SON
		approachDist 40
		view 244
		loop 2
		signal ignrAct
		cycleSpeed 11
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: V_TALK V_DO V_DINARS V_ROYALS)
		(return self)
	)
	
	(method (dispose)
		(self actions: 0)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(super doVerb: theVerb)
			)
			(else 
				(junkTell doVerb: theVerb)
			)
		)
	)
	
	(method (cue)
		(DisposeVendor merchantNoun)
		(= merchantNoun 0)
	)
)

(instance aHoneySeller of MerchantActor
	(properties
		x 304
		y 167
		noun N_HONEY
		approachDist 30
		view 254
		priority 11
		signal (| ignrAct fixPriOn)
		cycleSpeed 14
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: V_TALK V_DO V_DINARS V_ROYALS)
		(return self)
	)
	
	(method (dispose)
		(self actions: 0)
		(super dispose:)
	)
	
	(method (newGreeting)
		(switch (mod Day 6)
			(0
				(messager say: noun V_DOIT C_GREETING1 0 aSon)
			)
			(1
				(messager say: noun V_DOIT C_GREETING2 0 aSon)
			)
			(2
				(messager say: noun V_DOIT C_GREETING3 0 aSon)
			)
			(3
				(messager say: noun V_DOIT C_GREETING4 0 aSon)
			)
			(4
				(messager say: noun V_DOIT C_GREETING5 0 aSon)
			)
			(5
				(messager say: noun V_DOIT C_GREETING6 0 aSon)
			)
		)
	)
)

(instance aOilSeller of MerchantActor
	(properties
		x 197
		y 182
		noun N_OIL
		approachDist 30
		view 258
		loop 2
		priority 15
		signal ignrAct
		cycleSpeed 11
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: V_TALK V_DO V_DINARS V_ROYALS)
		(return self)
	)
	
	(method (dispose)
		(self actions: 0)
		(super dispose:)
	)
	
	(method (cue)
		([aWeaponSellerInit local3] stopUpd:)
		(= weaponPitchMade TRUE)
	)
	
	(method (newGreeting)
		(switch (mod Day 6)
			(0
				(messager say: noun V_DOIT C_GREETING1 0 aSon)
			)
			(1
				(messager say: noun V_DOIT C_GREETING2 0 aSon)
			)
			(2
				(messager say: noun V_DOIT C_GREETING3 0 aSon)
			)
			(3
				(messager say: noun V_DOIT C_GREETING4 0 aSon)
			)
			(4
				(messager say: noun V_DOIT C_GREETING5 0 aSon)
			)
			(5
				(messager say: noun V_DOIT C_GREETING6 0 aSon)
			)
		)
	)
)

(instance egoTell of Teller
	
	(method (respond)
		(return
			(if (not local4)
				(super respond:)
			else
				(= local4 0)
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
	
	(method (showDialog &tmp temp0)
		(= theMerchant (proc51_1))
		(= temp0 (ego distanceTo: theMerchant))
		(switch theMerchant
			(aWeaponSeller
				(if (> temp0 45)
					(messager say: N_MERCHANTS V_DOIT C_WHICH_ONE)
					(return -999)
				)
			)
			(aOilSeller
				(if (> temp0 48)
					(messager say: N_MERCHANTS V_DOIT C_WHICH_ONE)
					(return -999)
				)
			)
			(aSon
				(if (> temp0 18)
					(messager say: N_MERCHANTS V_DOIT C_WHICH_ONE)
					(return -999)
				)
			)
			(aHoneySeller
				(if (> temp0 41)
					(messager say: N_MERCHANTS V_DOIT C_WHICH_ONE)
					(return -999)
				)
			)
			(else 
				(cond 
					((> temp0 26)
						(messager say: N_MERCHANTS V_DOIT C_WHICH_ONE)
						(return -999)
					)
					((!= (globalSound number?) 231)
						(cSound pause: TRUE)
						(globalSound client: 0 number: 231 setLoop: -1 play:)
					)
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
	
	(method (doChild)
		(return
			(switch query
				(-92
					(cond 
						((== theMerchant aWeaponSeller)
							(= query C_GREET_WEAPON)
						)
						((== theMerchant aSon)
							(= query C_GREET_SANFORD)
						)
						((== theMerchant aSanford)
							(= query C_GREET_SANFORD)
						)
						((== theMerchant aHoneySeller)
							(= query C_GREET_HONEY)
						)
						((== theMerchant aOilSeller)
							(= query C_GREET_OIL)
						)
					)
				)
				(-93
					(cond 
						((== theMerchant aWeaponSeller)
							(= query C_GOODBYE_WEAPON)
						)
						((== theMerchant aSon)
							(= query C_GOODBYE_SANFORD)
						)
						((== theMerchant aSanford)
							(= query C_GOODBYE_SANFORD)
						)
						((== theMerchant aHoneySeller)
							(= query C_GOODBYE_HONEY)
						)
						((== theMerchant aOilSeller)
							(= query C_GOODBYE_OIL)
						)
					)
				)
				(-105
					(cond 
						((== theMerchant aWeaponSeller)
							(= query C_THIEF_SIGN_WEAPON)
						)
						((== theMerchant aSon)
							(= query C_THIEF_SIGN_SANFORD)
						)
						((== theMerchant aSanford)
							(= query C_THIEF_SIGN_SANFORD)
						)
						((== theMerchant aHoneySeller)
							(= query C_THIEF_SIGN_HONEY)
						)
						((== theMerchant aOilSeller)
							(= query C_THIEF_SIGN_OIL)
						)
					)
				)
				(-43
					(if (== ((inventory at: iRoyals) message?) V_DINARS)
						(messager say: N_WEAPON V_DOIT C_WRONG_MONEY)
					else
						(weaponTell doVerb: V_ROYALS)
					)
					(return FALSE)
				)
				(-72
					(cond 
						((== ((inventory at: iRoyals) message?) V_DINARS)
							(messager say: N_JUNK V_DOIT C_WRONG_MONEY)
						)
						(
							(or
								(and (ego has: iTinderbox) (!= heroType THIEF))
								(and (== heroType THIEF) (ego has: iTinderbox) (ego has: iBird))
							)
							(messager say: N_MERCHANTS V_DOIT C_NO_MORE_JUNK)
						)
						(else
							(junkTell doVerb: V_ROYALS)
						)
					)
					(return FALSE)
				)
				(-97
					(cond 
						((== ((inventory at: iRoyals) message?) V_DINARS)
							(messager say: N_HONEY V_DOIT C_WRONG_MONEY)
						)
						((ego has: iHoney)
							(messager say: N_MERCHANTS V_DOIT C_NO_MORE_HONEY)
						)
						(else
							(honeyTell doVerb: V_ROYALS)
						)
					)
					(return FALSE)
				)
				(-98
					(cond 
						((== ((inventory at: iRoyals) message?) V_DINARS)
							(messager say: N_OIL V_DOIT C_WRONG_MONEY)
						)
						((ego has: iOil)
							(messager say: N_MERCHANTS V_DOIT C_NO_MORE_OIL)
						)
						(else
							(oilTell doVerb: V_ROYALS)
						)
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
					-105
					(== heroType THIEF)
					-97
					(== aHoneySeller theMerchant)
					-72
					(if (== aSanford theMerchant) else (== aSon theMerchant))
					-43
					(== aWeaponSeller theMerchant)
					-98
					(== aOilSeller theMerchant)
			)
		)
		(= local4 1)
		(if iconValue
			(= query iconValue)
		)
		(egoTell respond:)
	)
)

(instance nightTell of Teller
	(properties)
	
	(method (showDialog)
		(super
			showDialog:
				84
				(if (Btst fHaramiSecondVisit) else (Btst fHaramiThirdVisit))
				-86
				(Btst fHaramiThirdVisit)
				-85
				(== heroType THIEF)
		)
	)
	
	(method (doChild)
		(return
			(switch query
				(-86
					(ego solvePuzzle: fTellHaramiAboutRakeesh 8)
					(return query)
				)
				(-85
					(ego solvePuzzle: fShowSignToHarami 8)
					(return query)
				)
			)
		)
	)
)

(instance weaponTell of Teller
	
	(method (doChild)
		(switch query
			(-100
				(switch (mod Day 6)
					(0
						(= query C_RUMOR1)
					)
					(1
						(= query C_RUMOR2)
					)
					(2
						(= query C_RUMOR3)
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
			(-101
				(switch (mod Day 6)
					(0
						(= query C_NAME)
					)
					(1
						(= query C_SELF1)
					)
					(2
						(= query C_SELF2)
					)
					(3
						(= query C_SELF3)
					)
					(4
						(= query C_SELF4)
					)
					(5
						(= query C_SELF5)
					)
				)
			)
			(else
				(super doChild: query)
			)
		)
	)
	
	(method (doVerb theVerb)
		(if (!= local2 1)
			(DisposeVendor local2)
			(= local2 1)
		)
		(return
			(switch theVerb
				(V_DINARS
					(= merchantNoun N_WEAPON)
					(messager say: N_WEAPON V_DOIT C_WRONG_MONEY 0 aSon)
				)
				(V_ROYALS
					(if (not local5)
						((ScriptID 245 1)
							goods:
								((List new:)
									add:
										((Ware new: N_FINE_DAGGER)
											price: 15
											quantity: (if (Btst fGotFineDagger) 0 else 1)
										)
										((Ware new: N_THROW_DAGGER)
											price: 6
											quantity: 26
										)
										((Ware new: N_FINE_SPEAR)
											price: 15
											quantity: (if (Btst fGotFineSpear) 0 else 1)
										)
								)
						)
						(= local5 ((ScriptID 245 1) goods?))
					else
						((ScriptID 245 1) goods: local5)
					)
					((ScriptID 245 1) init: purchase: dispose:)
					(return TRUE)
				)
				(else
					(super doVerb: theVerb)
				)
			)
		)
	)
)

(instance junkTell of Teller
	
	(method (doChild)
		(switch query
			(-100
				(switch (mod Day 6)
					(0
						(= query C_RUMOR1)
					)
					(1
						(= query C_RUMOR2)
					)
					(2
						(= query C_RUMOR3)
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
			(-101
				(switch (mod Day 6)
					(0
						(= query C_JUNK1)
					)
					(1
						(= query C_JUNK2)
					)
					(2
						(= query C_JUNK3)
					)
					(3
						(= query C_JUNK4)
					)
					(4
						(= query C_JUNK5)
					)
					(5
						(= query C_JUNK6)
					)
				)
			)
		)
	)
	
	(method (doVerb theVerb)
		(if (!= local2 7)
			(DisposeVendor local2)
			(= local2 7)
		)
		(return
			(switch theVerb
				(V_DINARS
					(= merchantNoun N_JUNK)
					(messager say: N_SON V_DOIT C_WRONG_MONEY 0 aSon)
				)
				(V_ROYALS
					(if
						(or
							(and (ego has: 9) (!= heroType 2))
							(and (== heroType 2) (ego has: 9) (ego has: 33))
						)
						(messager say: 3 6 115)
					else
						(if (not local6)
							((ScriptID 246 2)
								goods:
									((List new:)
										add:
											((Ware new: 27)
												price: 200
												denomination: 1
												quantity: (if (ego has: 9) 0 else 1)
											)
											((Ware new: 28)
												price: 40
												denomination: 1
												quantity: (if (and (== heroType 2) (not (Btst 147))) 1 else 0)
											)
									)
							)
							(= local6 ((ScriptID 246 2) goods?))
						else
							((ScriptID 246 2) goods: local6)
						)
						((ScriptID 246 2) init: purchase: dispose:)
						(if (and (== heroType 2) (Btst 147))
							(blackBird dispose:)
						)
						(return 1)
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance honeyTell of Teller
	
	(method (doChild)
		(return
			(switch query
				(-91
					(Bset fHoneyBirdHinted)
					(return query)
				)
				(-100
					(switch (mod Day 6)
						(0
							(= query C_RUMOR1)
						)
						(1
							(= query C_RUMOR2)
						)
						(2
							(= query C_RUMOR3)
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
				(-101
					(switch (mod Day 6)
						(0
							(= query C_NAME)
						)
						(1
							(= query C_SELF1)
						)
						(2
							(= query C_SELF2)
						)
						(3
							(= query C_SELF3)
						)
						(4
							(= query C_SELF4)
						)
						(5
							(= query C_SELF5)
						)
					)
				)
				(else
					(super doChild: query)
				)
			)
		)
	)
	
	(method (doVerb theVerb)
		(if (!= local2 4)
			(DisposeVendor local2)
			(= local2 4)
		)
		(return
			(switch theVerb
				(V_DINARS
					(= merchantNoun N_HONEY)
					(messager say: N_HONEY V_DOIT C_WRONG_MONEY 0 aSon)
				)
				(V_ROYALS
					(if (ego has: iHoney)
						(messager say: N_HONEY V_DOIT C_NO_MORE_HONEY)
					else
						(if (not local7)
							((ScriptID 247 1)
								goods:
									((List new:)
										add:
											((Ware new: N_HONEY)
												price: 80
												denomination: 1
												quantity: (if (ego has: iHoney) 0 else 1)
											)
									)
							)
							(= local7 ((ScriptID 247 1) goods?))
						else
							((ScriptID 247 1) goods: local7)
						)
						((ScriptID 247 1) init: purchase: dispose:)
					)
					(return TRUE)
				)
				(else
					(super doVerb: theVerb)
				)
			)
		)
	)
)

(instance oilTell of Teller

	(method (doChild)
		(switch query
			(-100
				(switch (mod Day 6)
					(0
						(= query C_RUMOR1)
					)
					(1
						(= query C_RUMOR2)
					)
					(2
						(= query C_RUMOR3)
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
			(-101
				(switch (mod Day 6)
					(0
						(= query C_OIL1)
					)
					(1
						(= query C_OIL2)
					)
					(2
						(= query C_OIL3)
					)
					(3
						(= query C_OIL4)
					)
					(4
						(= query C_OIL5)
					)
					(5
						(= query C_OIL6)
					)
				)
			)
		)
	)
	
	(method (doVerb theVerb)
		(if (!= local2 5)
			(DisposeVendor local2)
			(= local2 5)
		)
		(return
			(switch theVerb
				(V_DINARS
					(= merchantNoun N_OIL)
					(messager say: N_OIL V_DOIT C_WRONG_MONEY 0 aSon)
				)
				(V_ROYALS
					(if (ego has: iOil)
						(messager say: N_MERCHANTS V_DOIT C_NO_MORE_OIL)
					else
						(if (not local8)
							((ScriptID 248 1)
								goods:
									((List new:)
										add:
											((Ware new: N_OIL)
												price: 100
												denomination: 1
												quantity: (if (ego has: iOil) 0 else 1)
											)
									)
							)
							(= local8 ((ScriptID 248 1) goods?))
						else
							((ScriptID 248 1) goods: local8)
						)
						((ScriptID 248 1) init: purchase: dispose:)
						(return TRUE)
					)
				)
				(else
					(super doVerb: theVerb)
				)
			)
		)
	)
)

(instance spearsLeft of View
	(properties
		x 31
		y 78
		noun N_SPEARS
		view 240
		signal (| ignrAct skipCheck)
	)
)

(instance spearsRight of View
	(properties
		x 88
		y 98
		view 240
		cel 1
		signal (| ignrAct skipCheck)
	)
	
	(method (doVerb theVerb)
		(weapon_stand doVerb: theVerb)
	)
)

(instance junkRight of View
	(properties
		x 119
		y 41
		view 240
		cel 2
		signal ignrAct
	)
	
	(method (doVerb theVerb)
		(junktable doVerb: theVerb)
	)
)

(instance mooseHead of View
	(properties
		x 113
		y 80
		noun 33
		view 240
		loop 2
		signal ignrAct
	)
)

(instance blackBird of Prop
	(properties
		x 222
		y 76
		noun 28
		view 241
		priority 6
		signal fixPriOn
	)
)

(instance honeyOnShelf of View
	(properties
		x 287
		y 143
		view 240
		loop 1
		signal ignrAct
	)
	
	(method (doVerb theVerb)
		(honeystand doVerb: theVerb)
	)
)

(instance oilRug of View
	(properties
		x 117
		y 154
		view 240
		loop 1
		cel 1
		signal ignrAct
	)
)

(instance oilBottles of View
	(properties
		x 228
		y 170
		view 240
		loop 1
		cel 2
		signal ignrAct
	)
	
	(method (doVerb theVerb)
		(rightoilbottles doVerb: theVerb)
	)
)

(instance haramiTell of Teller
	(properties)
	
	(method (doVerb theVerb &tmp what)
		(cond 
			((OneOf theVerb V_FISH V_FRUIT V_HONEY V_MEAT V_RATIONS)
				(= what
					(switch theVerb
						(V_RATIONS iRations)
						(V_FRUIT iFruit)
						(V_HONEY iHoney)
						(V_MEAT iMeat)
						(V_FISH iFish)
					)
				)
				(ego drop: what 1)
				(Bset 113)
				(ego addHonor: 10)
				(if ((ScriptID 241 0) cycler?)
					((ScriptID 241 0) setCycle: BegLoop)
				)
				(messager say: N_HARAMI V_DOIT 10)
			)
			((OneOf theVerb 59 10)
				(ego addHonor: 5)
				(if ((ScriptID 241 0) cycler?)
					((ScriptID 241 0) setCycle: BegLoop)
				)
				(messager say: N_HARAMI V_DOIT 112)
			)
			(else (super doVerb: theVerb))
		)
	)
)

(instance leftoilbottles of Feature
	(properties
		x 148
		y 199
		z 30
		noun N_LEFT_OIL_BOTTLES
		nsTop 164
		nsLeft 138
		nsBottom 175
		nsRight 158
	)
)

(instance bigoilbottle of Feature
	(properties
		x 160
		y 183
		noun N_BIG_OIL_BOTTLE
		nsTop 177
		nsLeft 153
		nsBottom 189
		nsRight 167
	)
)

(instance rightoilbottles of Feature
	(properties
		x 228
		y 193
		z 30
		noun N_RIGHT_OIL_BOTTLES
		nsTop 157
		nsLeft 222
		nsBottom 169
		nsRight 234
	)
)

(instance oilrug of Feature
	(properties
		x 189
		y 175
		noun N_OIL_RUG
		nsTop 162
		nsLeft 124
		nsBottom 189
		nsRight 254
	)
)

(instance weapon_stand of Feature
	(properties
		x 72
		y 73
		noun N_WEAPON_STAND
		nsTop 48
		nsLeft 34
		nsBottom 99
		nsRight 111
	)
)

(instance honeystand of Feature
	(properties
		x 290
		y 128
		noun N_HONEY_STAND
		nsTop 98
		nsLeft 262
		nsBottom 159
		nsRight 319
	)
)

(instance pansonrope of Feature
	(properties
		x 153
		y 66
		noun N_PANS_ON_ROPE
		nsTop 50
		nsLeft 116
		nsBottom 82
		nsRight 191
	)
)

(instance leftjunk of Feature
	(properties
		x 165
		y 95
		noun N_LEFT_JUNK
		nsTop 83
		nsLeft 146
		nsBottom 107
		nsRight 185
	)
)

(instance junktable of Feature
	(properties
		x 238
		y 86
		noun N_JUNK_TABLE
		nsTop 75
		nsLeft 220
		nsBottom 98
		nsRight 257
	)
)

(instance junkdealertent of Feature
	(properties
		x 199
		y 52
		noun N_JUNK_TENT
		nsTop 17
		nsLeft 109
		nsBottom 87
		nsRight 289
	)
)

(instance purplepot of Feature
	(properties
		x 253
		y 63
		noun N_PURPLE_POT
		nsTop 52
		nsLeft 247
		nsBottom 74
		nsRight 260
	)
)

(instance platerows of Feature
	(properties
		x 270
		y 53
		noun N_PLATE_ROWS
		nsTop 44
		nsLeft 261
		nsBottom 63
		nsRight 279
	)
)

(instance righttable of Feature
	(properties
		x 275
		y 69
		noun N_RIGHT_TABLE
		nsTop 63
		nsLeft 262
		nsBottom 75
		nsRight 289
	)
)

(instance largeurns of Feature
	(properties
		x 304
		y 77
		noun N_LARGE_URNS
		nsTop 64
		nsLeft 290
		nsBottom 90
		nsRight 319
	)
)
