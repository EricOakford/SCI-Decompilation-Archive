;;; Sierra Script 1.0 - (do not remove this comment)
(script# 240)
(include sci.sh)
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
	local0 =  1
	aSanfordNoun
	local2
	local3
	local4
	local5
	local6
	local7
	local8
	local9
	local10 =  1
	local11
	[aWeaponSellerInit 4]
	local16
	[local17 9] = [0 -92 -93 -105 -43 -72 -97 -98 999]
	[local26 7] = [0 82 83 84 -86 -85 999]
	[local33 7] = [0 76 77 47 78 24 999]
	[local40 5] = [0 79 80 25 999]
	[local45 5] = [0 104 81 26 999]
	[local50 6] = [0 -36 35 -100 -101 999]
	[local56 7] = [0 37 38 39 40 2 999]
	[local63 3] = [0 -36 999]
	[local66 6] = [0 63 35 -100 -101 999]
	[local72 6] = [0 -48 35 -100 -101 999]
	[local78 4] = [0 49 -91 999]
	[local82 3] = [0 -48 999]
	[local85 6] = [0 52 35 -100 -101 999]
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
	(= local0 0)
	([aWeaponSellerInit local3]
		cel: 0
		setCycle: End aOilSeller
	)
)

(procedure (localproc_08de param1)
	(switch param1
		(1 (DisposeScript 245))
		(7 (DisposeScript 246))
		(6 (DisposeScript 246))
		(4 (DisposeScript 247))
		(5 (DisposeScript 248))
	)
)

(instance rm240 of Rm
	(properties
		noun 26
		picture 240
		horizon -20
		vanishingY -300
	)
	
	(method (init)
		(LoadMany 143 240)
		(walkHandler addToFront: self)
		(self setRegions: 51)
		(ego
			noun: 2
			init:
			normalize:
			edgeHit: 0
			scaleSignal: 1
			scaleX: 120
			scaleY: 120
		)
		(if (or Night (Btst 135))
			(curRoom
				addObstacle:
					((Polygon new:)
						type: 2
						init: 319 189 283 189 239 149 319 137
						yourself:
					)
					((Polygon new:)
						type: 2
						init: 0 95 0 0 319 0 319 104 284 104 255 89 230 104 128 118 109 108 52 114
						yourself:
					)
			)
			(if (and (> Day 4) (not (Btst 172)))
				(= local11 (| local11 $2000))
				(if (and (!= haramiNight Day) (Btst 40))
					(if (Btst 46) (Bset 47) else (Bset 46))
				)
				(ego code: nightCode)
				((ScriptID 241 0)
					view: 954
					loop: 1
					cel: 0
					x: 149
					y: 105
					init:
					scaleSignal: 1
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
				((!= (cSound number?) 230) (cSound fade: 60 10 5 0))
				(else (cSound client: self))
			)
			(curRoom
				addObstacle:
					((Polygon new:)
						type: 2
						init: 240 189 141 189 114 165 218 150 262 186
						yourself:
					)
					((Polygon new:)
						type: 2
						init: 319 189 273 189 236 146 319 137
						yourself:
					)
					((Polygon new:)
						type: 2
						init: 0 100 0 0 319 0 319 98 291 102 270 93 271 105 187 117 51 117
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
			(if (and (== heroType 2) (not (Btst 147)))
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
				(= style -32759)
				(if (and (< Day 5) (Btst 22) (not (Btst 36)))
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
				(= style -32761)
				(self setScript: from250)
			)
			(270
				(= style -32761)
				(self setScript: from270)
			)
			(else  (ego x: 35 y: 120))
		)
	)
	
	(method (dispose)
		(= haramiNight Day)
		(Bclr 113)
		(if local5 (local5 dispose:))
		(if local6 (local6 dispose:))
		(if local7 (local7 dispose:))
		(if local8 (local8 dispose:))
		(walkHandler delete: self)
		(UnLoad 143 240)
		(LoadMany 0 47 241 245 246 247 248)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(messager say: noun 1 0 (if Night 0 else 1))
			)
			(3
				(if (& local11 $2000)
					(if
					(and ((ScriptID 241 0) cycler?) (& local11 $1000))
						(= local11 (& local11 $efff))
						((ScriptID 241 0) setCycle: Beg)
					)
					(if (and (not (Btst 113)) (not local10))
						(= local10 1)
						(messager say: 9 6 93)
					)
				else
					(if (self script?)
						(self script: 0)
						(aWeaponSeller setCycle: End setLoop: 2)
					)
					(if (CueObj theVerb?) (CueObj theVerb: 0))
					(if aSanfordNoun
						(messager say: aSanfordNoun 6 10 0 aSon)
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
			(else  (super doVerb: theVerb))
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
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Bset 159)
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
			(1 (= cycles 5))
			(2
				(cond 
					((Btst 47) (Bset 172) (messager say: 9 6 7 0 self))
					((Btst 46) (messager say: 9 6 6 0 self))
					((Btst 40) (messager say: 9 6 75 0 self))
					(else (messager say: 9 6 62 0 self) (Bset 40))
				)
			)
			(3 (HandsOn) (self dispose:))
		)
	)
)

(instance weaponSellerFirst of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (aWeaponSeller cycler?) (= local0 1))
				(aWeaponSeller setCycle: Beg self)
				(ego setMotion: 0)
				(aSon setCycle: 0)
			)
			(1
				(Face ego aWeaponSeller)
				(= cycles (+ (ego cycleSpeed?) 15))
			)
			(2 (messager say: 1 6 1 1 self))
			(3
				(aWeaponSeller setLoop: 2 cel: 6 setCycle: CT 3 -1 self)
			)
			(4 (messager say: 1 6 3 0 self))
			(5
				(aWeaponSeller cel: 0 setCycle: End self)
			)
			(6
				(HandsOn)
				(localproc_08de 1)
				(Bset 106)
				(aWeaponSeller stopUpd:)
				(= local0 1)
				(self dispose:)
			)
		)
	)
)

(instance thiefChase of Script
	(properties)
	
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
			(1 (= cycles 5))
			(2
				(messager say: 8 6 47 0 self)
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
	(properties)
	
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
				(aSanford setCycle: End self)
			)
			(3
				(messager say: 7 6 62 0 self)
			)
			(4
				(localproc_08de 7)
				(aSanford setCycle: CT 0 -1 self)
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
	(properties)
	
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
						((Btst 47)
							(= [local99 0] @local45)
							(haramiTell init: (ScriptID 241 0) @local45 @local99)
							(curRoom setScript: haramiBeg)
						)
						((Btst 46)
							(= local11 (| local11 $0020))
							(= [local99 0] @local40)
							(haramiTell init: (ScriptID 241 0) @local40 @local99)
							(curRoom setScript: haramiBeg)
						)
						(else
							(= local11 (| local11 $0010))
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
			(3 (HandsOn) (self dispose:))
		)
	)
)

(instance from250 of Script
	(properties)
	
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
						((Btst 47)
							(= [local99 0] @local45)
							(haramiTell init: (ScriptID 241 0) @local45 @local99)
							(curRoom setScript: haramiBeg)
						)
						((Btst 46)
							(= local11 (| local11 $0020))
							(= [local99 0] @local40)
							(haramiTell init: (ScriptID 241 0) @local40 @local99)
							(curRoom setScript: haramiBeg)
						)
						(else
							(= local11 (| local11 $0010))
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
			(3 (HandsOn) (self dispose:))
		)
	)
)

(instance from270 of Script
	(properties)
	
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
						((Btst 47)
							(= [local99 0] @local45)
							(haramiTell init: (ScriptID 241 0) @local45 @local99)
							(curRoom setScript: haramiBeg)
						)
						((Btst 46)
							(= local11 (| local11 $0020))
							(= [local99 0] @local40)
							(haramiTell init: (ScriptID 241 0) @local40 @local99)
							(curRoom setScript: haramiBeg)
						)
						(else
							(= local11 (| local11 $0010))
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
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: 0)
				(Face ego (ScriptID 241 0))
				(= cycles (+ (ego cycleSpeed?) 15))
			)
			(1
				(messager say: 9 6 92 0 self)
			)
			(2 (self dispose:))
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
					((<= (ego x?) 5)
						(= register 230)
						(ego setMotion: PolyPath -30 (ego y?) self)
					)
					((<= (ego y?) 5)
						(= register 230)
						(ego setMotion: PolyPath (ego x?) -30 self)
					)
					((>= (ego x?) 315)
						(if
						(and Night (not (Btst 113)) (& local11 $2000))
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
	(properties)
	
	(method (doit)
		(if local0 (localproc_0892))
		(cond 
			((curRoom script?) 0)
			((not (if (< 5 (ego x?)) (< (ego x?) 315))) (curRoom setScript: sExit))
			((not (if (< 5 (ego y?)) (< (ego y?) 183))) (curRoom setScript: sExit))
			((< (ego distanceTo: aWeaponSeller) 45)
				(aWeaponSeller setCycle: 0)
				(if (and (not (Btst 106)) (not local16))
					(= local16 1)
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
					((not (Btst 107)) (Bset 107) (curRoom setScript: aSonGreeting))
					((not (& local11 $0001))
						(= local11 (| local11 $0001))
						(= aSanfordNoun (aSanford noun?))
						(ego setMotion: 0)
						(aSanford newGreeting:)
					)
				)
			)
			((< (ego distanceTo: aHoneySeller) 40)
				(aHoneySeller setCycle: 0)
				(if (not (& local11 $0002))
					(= local11 (| local11 $0002))
					(ego setMotion: 0)
					(= aSanfordNoun (aHoneySeller noun?))
					(aHoneySeller newGreeting:)
				)
			)
			((< (ego distanceTo: aSon) 17)
				(if (!= (globalSound number?) 231)
					(cSound pause: 1)
					(globalSound client: 0 number: 231 setLoop: -1 play:)
				)
				(cond 
					((not (Btst 107)) (Bset 107) (curRoom setScript: aSonGreeting))
					((not (& local11 $0004))
						(= local11 (| local11 $0004))
						(ego setMotion: 0)
						(= aSanfordNoun (aSanford noun?))
						(aSanford newGreeting:)
					)
				)
			)
			((< (ego distanceTo: aOilSeller) 33)
				(aOilSeller setCycle: 0)
				(if (not (& local11 $0008))
					(= local11 (| local11 $0008))
					(ego setMotion: 0)
					(= aSanfordNoun (aOilSeller noun?))
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
	(properties)
	
	(method (doit)
		(cond 
			((curRoom script?) 0)
			((not (if (< 5 (ego x?)) (< (ego x?) 315))) (curRoom setScript: sExit))
			((not (if (< 5 (ego y?)) (< (ego y?) 183))) (curRoom setScript: sExit))
			((< (ego distanceTo: (ScriptID 241 0)) 30)
				(if (& local11 $0100)
					0
				else
					(= local11 (| local11 $0100))
					((ScriptID 241 0) setCycle: Fwd)
					(= local11 (| local11 $1000))
					(= local10 0)
					(curRoom setScript: begSecond)
				)
			)
		)
	)
)

(instance nightCodeX of Code
	(properties)
	
	(method (doit)
		(cond 
			((curRoom script?) 0)
			((not (if (< 5 (ego x?)) (< (ego x?) 315))) (curRoom setScript: sExit))
			((not (if (< 5 (ego y?)) (< (ego y?) 183))) (curRoom setScript: sExit))
		)
	)
)

(instance aWeaponSeller of MerchantActor
	(properties
		x 62
		y 82
		noun 1
		approachDist 50
		view 325
		loop 2
		signal $4000
		cycleSpeed 12
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 2 4 59 10)
		(return self)
	)
	
	(method (dispose)
		(self actions: 0)
		(super dispose:)
	)
	
	(method (cue)
		(if (& local11 $0100) (= local11 (& local11 $feff)))
	)
	
	(method (newGreeting)
		(switch (mod Day 6)
			(0
				(messager say: noun 6 4 0 aSon)
			)
			(1
				(messager say: noun 6 5 0 aSon)
			)
			(2
				(messager say: noun 6 6 0 aSon)
			)
			(3
				(messager say: noun 6 7 0 aSon)
			)
			(4
				(messager say: noun 6 8 0 aSon)
			)
			(5
				(messager say: noun 6 9 0 aSon)
			)
		)
	)
)

(instance aSanford of MerchantActor
	(properties
		x 241
		y 85
		noun 7
		approachDist 50
		view 242
		signal $5000
		cycleSpeed 19
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 2 4 59 10)
	)
	
	(method (dispose)
		(self actions: 0)
		(super dispose:)
	)
	
	(method (newGreeting)
		(switch (mod Day 6)
			(0
				(messager say: noun 6 4 0 aSon)
			)
			(1
				(messager say: noun 6 5 0 aSon)
			)
			(2
				(messager say: noun 6 6 0 aSon)
			)
			(3
				(messager say: noun 6 7 0 aSon)
			)
			(4
				(messager say: noun 6 8 0 aSon)
			)
			(5
				(messager say: noun 6 9 0 aSon)
			)
		)
	)
)

(instance aSon of MerchantActor
	(properties
		x 209
		y 104
		noun 6
		approachDist 40
		view 244
		loop 2
		signal $4000
		cycleSpeed 11
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 2 4 59 10)
		(return self)
	)
	
	(method (dispose)
		(self actions: 0)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (super doVerb: theVerb))
			(else 
				(junkTell doVerb: theVerb)
			)
		)
	)
	
	(method (cue)
		(localproc_08de aSanfordNoun)
		(= aSanfordNoun 0)
	)
)

(instance aHoneySeller of MerchantActor
	(properties
		x 304
		y 167
		noun 4
		approachDist 30
		view 254
		priority 11
		signal $4010
		cycleSpeed 14
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 2 4 59 10)
		(return self)
	)
	
	(method (dispose)
		(self actions: 0)
		(super dispose:)
	)
	
	(method (newGreeting)
		(switch (mod Day 6)
			(0
				(messager say: noun 6 4 0 aSon)
			)
			(1
				(messager say: noun 6 5 0 aSon)
			)
			(2
				(messager say: noun 6 6 0 aSon)
			)
			(3
				(messager say: noun 6 7 0 aSon)
			)
			(4
				(messager say: noun 6 8 0 aSon)
			)
			(5
				(messager say: noun 6 9 0 aSon)
			)
		)
	)
)

(instance aOilSeller of MerchantActor
	(properties
		x 197
		y 182
		noun 5
		approachDist 30
		view 258
		loop 2
		priority 15
		signal $4000
		cycleSpeed 11
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 2 4 59 10)
		(return self)
	)
	
	(method (dispose)
		(self actions: 0)
		(super dispose:)
	)
	
	(method (cue)
		([aWeaponSellerInit local3] stopUpd:)
		(= local0 1)
	)
	
	(method (newGreeting)
		(switch (mod Day 6)
			(0
				(messager say: noun 6 4 0 aSon)
			)
			(1
				(messager say: noun 6 5 0 aSon)
			)
			(2
				(messager say: noun 6 6 0 aSon)
			)
			(3
				(messager say: noun 6 7 0 aSon)
			)
			(4
				(messager say: noun 6 8 0 aSon)
			)
			(5
				(messager say: noun 6 9 0 aSon)
			)
		)
	)
)

(instance egoTell of Teller
	(properties)
	
	(method (respond)
		(return
			(if (not local4)
				(super respond:)
			else
				(= local4 0)
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
		(= local9 (proc51_1))
		(= temp0 (ego distanceTo: local9))
		(switch local9
			(aWeaponSeller
				(if (> temp0 45) (messager say: 3 6 99) (return -999))
			)
			(aOilSeller
				(if (> temp0 48) (messager say: 3 6 99) (return -999))
			)
			(aSon
				(if (> temp0 18) (messager say: 3 6 99) (return -999))
			)
			(aHoneySeller
				(if (> temp0 41) (messager say: 3 6 99) (return -999))
			)
			(else 
				(cond 
					((> temp0 26) (messager say: 3 6 99) (return -999))
					((!= (globalSound number?) 231)
						(cSound pause: 1)
						(globalSound client: 0 number: 231 setLoop: -1 play:)
					)
				)
			)
		)
		(if
			(!=
				(ego heading?)
				(GetAngle (ego x?) (ego y?) (local9 x?) (local9 y?))
			)
			(Face ego local9)
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
						((== local9 aWeaponSeller) (= query 41))
						((== local9 aSon) (= query 73))
						((== local9 aSanford) (= query 73))
						((== local9 aHoneySeller) (= query 50))
						((== local9 aOilSeller) (= query 59))
					)
				)
				(-93
					(cond 
						((== local9 aWeaponSeller) (= query 42))
						((== local9 aSon) (= query 74))
						((== local9 aSanford) (= query 74))
						((== local9 aHoneySeller) (= query 51))
						((== local9 aOilSeller) (= query 60))
					)
				)
				(-105
					(cond 
						((== local9 aWeaponSeller) (= query 87))
						((== local9 aSon) (= query 88))
						((== local9 aSanford) (= query 88))
						((== local9 aHoneySeller) (= query 89))
						((== local9 aOilSeller) (= query 90))
					)
				)
				(-43
					(if (== ((inventory at: 0) message?) 59)
						(messager say: 1 6 94)
					else
						(weaponTell doVerb: 10)
					)
					(return 0)
				)
				(-72
					(cond 
						((== ((inventory at: 0) message?) 59) (messager say: 7 6 94))
						(
							(or
								(and (ego has: 9) (!= heroType 2))
								(and (== heroType 2) (ego has: 9) (ego has: 33))
							)
							(messager say: 3 6 115)
						)
						(else (junkTell doVerb: 10))
					)
					(return 0)
				)
				(-97
					(cond 
						((== ((inventory at: 0) message?) 59) (messager say: 4 6 94))
						((ego has: 29) (messager say: 3 6 113))
						(else (honeyTell doVerb: 10))
					)
					(return 0)
				)
				(-98
					(cond 
						((== ((inventory at: 0) message?) 59) (messager say: 5 6 94))
						((ego has: 25) (messager say: 3 6 114))
						(else (oilTell doVerb: 10))
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
					-105
					(== heroType 2)
					-97
					(== aHoneySeller local9)
					-72
					(if (== aSanford local9) else (== aSon local9))
					-43
					(== aWeaponSeller local9)
					-98
					(== aOilSeller local9)
			)
		)
		(= local4 1)
		(if iconValue (= query iconValue))
		(egoTell respond:)
	)
)

(instance nightTell of Teller
	(properties)
	
	(method (showDialog)
		(super
			showDialog:
				84
				(if (Btst 46) else (Btst 47))
				-86
				(Btst 47)
				-85
				(== heroType 2)
		)
	)
	
	(method (doChild)
		(return
			(switch query
				(-86
					(ego solvePuzzle: 221 8)
					(return query)
				)
				(-85
					(ego solvePuzzle: 229 8)
					(return query)
				)
			)
		)
	)
)

(instance weaponTell of Teller
	(properties)
	
	(method (doChild)
		(switch query
			(-100
				(switch (mod Day 6)
					(0 (= query 23))
					(1 (= query 24))
					(2 (= query 25))
					(3 (= query 26))
					(4 (= query 27))
					(5 (= query 28))
				)
			)
			(-101
				(switch (mod Day 6)
					(0 (= query 29))
					(1 (= query 30))
					(2 (= query 31))
					(3 (= query 32))
					(4 (= query 33))
					(5 (= query 34))
				)
			)
			(else  (super doChild: query))
		)
	)
	
	(method (doVerb theVerb)
		(if (!= local2 1) (localproc_08de local2) (= local2 1))
		(return
			(switch theVerb
				(59
					(= aSanfordNoun 1)
					(messager say: 1 6 94 0 aSon)
				)
				(10
					(if (not local5)
						((ScriptID 245 1)
							goods:
								((List new:)
									add:
										((Ware new: 29)
											price: 15
											quantity: (if (Btst 166) 0 else 1)
										)
										((Ware new: 30) price: 6 quantity: 26)
										((Ware new: 31)
											price: 15
											quantity: (if (Btst 167) 0 else 1)
										)
								)
						)
						(= local5 ((ScriptID 245 1) goods?))
					else
						((ScriptID 245 1) goods: local5)
					)
					((ScriptID 245 1) init: purchase: dispose:)
					(return 1)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance junkTell of Teller
	(properties)
	
	(method (doChild)
		(switch query
			(-100
				(switch (mod Day 6)
					(0 (= query 23))
					(1 (= query 24))
					(2 (= query 25))
					(3 (= query 26))
					(4 (= query 27))
					(5 (= query 28))
				)
			)
			(-101
				(switch (mod Day 6)
					(0 (= query 64))
					(1 (= query 65))
					(2 (= query 66))
					(3 (= query 67))
					(4 (= query 68))
					(5 (= query 69))
				)
			)
		)
	)
	
	(method (doVerb theVerb)
		(if (!= local2 7) (localproc_08de local2) (= local2 7))
		(return
			(switch theVerb
				(59
					(= aSanfordNoun 7)
					(messager say: 6 6 94 0 aSon)
				)
				(10
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
	(properties)
	
	(method (doChild)
		(return
			(switch query
				(-91 (Bset 83) (return query))
				(-100
					(switch (mod Day 6)
						(0 (= query 23))
						(1 (= query 24))
						(2 (= query 25))
						(3 (= query 26))
						(4 (= query 27))
						(5 (= query 28))
					)
				)
				(-101
					(switch (mod Day 6)
						(0 (= query 29))
						(1 (= query 30))
						(2 (= query 31))
						(3 (= query 32))
						(4 (= query 33))
						(5 (= query 34))
					)
				)
				(else  (super doChild: query))
			)
		)
	)
	
	(method (doVerb theVerb)
		(if (!= local2 4) (localproc_08de local2) (= local2 4))
		(return
			(switch theVerb
				(59
					(= aSanfordNoun 4)
					(messager say: 4 6 94 0 aSon)
				)
				(10
					(if (ego has: 29)
						(messager say: 3 6 113)
					else
						(if (not local7)
							((ScriptID 247 1)
								goods:
									((List new:)
										add:
											((Ware new: 4)
												price: 80
												denomination: 1
												quantity: (if (ego has: 29) 0 else 1)
											)
									)
							)
							(= local7 ((ScriptID 247 1) goods?))
						else
							((ScriptID 247 1) goods: local7)
						)
						((ScriptID 247 1) init: purchase: dispose:)
					)
					(return 1)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance oilTell of Teller
	(properties)
	
	(method (doChild)
		(switch query
			(-100
				(switch (mod Day 6)
					(0 (= query 23))
					(1 (= query 24))
					(2 (= query 25))
					(3 (= query 26))
					(4 (= query 27))
					(5 (= query 28))
				)
			)
			(-101
				(switch (mod Day 6)
					(0 (= query 53))
					(1 (= query 54))
					(2 (= query 55))
					(3 (= query 56))
					(4 (= query 57))
					(5 (= query 58))
				)
			)
		)
	)
	
	(method (doVerb theVerb)
		(if (!= local2 5) (localproc_08de local2) (= local2 5))
		(return
			(switch theVerb
				(59
					(= aSanfordNoun 5)
					(messager say: 5 6 94 0 aSon)
				)
				(10
					(if (ego has: 25)
						(messager say: 3 6 114)
					else
						(if (not local8)
							((ScriptID 248 1)
								goods:
									((List new:)
										add:
											((Ware new: 5)
												price: 100
												denomination: 1
												quantity: (if (ego has: 25) 0 else 1)
											)
									)
							)
							(= local8 ((ScriptID 248 1) goods?))
						else
							((ScriptID 248 1) goods: local8)
						)
						((ScriptID 248 1) init: purchase: dispose:)
						(return 1)
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance spearsLeft of View
	(properties
		x 31
		y 78
		noun 11
		view 240
		signal $5000
	)
)

(instance spearsRight of View
	(properties
		x 88
		y 98
		view 240
		cel 1
		signal $5000
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
		signal $4000
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
		signal $4000
	)
)

(instance blackBird of Prop
	(properties
		x 222
		y 76
		noun 28
		view 241
		priority 6
		signal $0010
	)
)

(instance honeyOnShelf of View
	(properties
		x 287
		y 143
		view 240
		loop 1
		signal $4000
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
		signal $4000
	)
)

(instance oilBottles of View
	(properties
		x 228
		y 170
		view 240
		loop 1
		cel 2
		signal $4000
	)
	
	(method (doVerb theVerb)
		(rightoilbottles doVerb: theVerb)
	)
)

(instance haramiTell of Teller
	(properties)
	
	(method (doVerb theVerb &tmp temp0)
		(cond 
			((OneOf theVerb 27 29 40 28 24)
				(= temp0
					(switch theVerb
						(24 14)
						(29 19)
						(40 29)
						(28 18)
						(27 17)
					)
				)
				(ego drop: temp0 1)
				(Bset 113)
				(ego addHonor: 10)
				(if ((ScriptID 241 0) cycler?)
					((ScriptID 241 0) setCycle: Beg)
				)
				(messager say: 9 6 10)
			)
			((OneOf theVerb 59 10)
				(ego addHonor: 5)
				(if ((ScriptID 241 0) cycler?)
					((ScriptID 241 0) setCycle: Beg)
				)
				(messager say: 9 6 112)
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
		noun 12
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
		noun 13
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
		noun 14
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
		noun 15
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
		noun 16
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
		noun 17
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
		noun 24
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
		noun 19
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
		noun 20
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
		noun 18
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
		noun 25
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
		noun 21
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
		noun 22
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
		noun 23
		nsTop 64
		nsLeft 290
		nsBottom 90
		nsRight 319
	)
)
