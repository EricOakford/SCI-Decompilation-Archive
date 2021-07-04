;;; Sierra Script 1.0 - (do not remove this comment)
(script# 18)
(include game.sh)
(use Main)
(use Procs)
(use Print)
(use Actor)
(use System)

(public
	scaryInvInit 0
	proc18_1 1
	reposition 2
)

(procedure (proc18_1 param1 &tmp temp0 temp1)
	(if (< global171 7)
		(= temp0 (+ 70 (* global171 22)))
	else
		(= temp0 (+ 70 (* (- global171 7) 22)))
	)
	(= temp1 (if (< global171 7) 15 else 35))
	(param1
		x: temp0
		y: temp1
		owner: global104
		init: theInterfaceCast
		show:
	)
)

(instance pInventory of List)

(instance scaryInvInit of Code
	
	(method (doit)
		((= inventory pInventory)
			add:
				(invComb owner: -1)
				(invPeticoat owner: -1)
				(invPot owner: -1)
				(invStick owner: -1)
				(invFlag owner: -1)
				(invPuz1 owner: -1)
				(invSeed owner: -1)
				(invBasket owner: -1)
				(invHorn owner: -1)
				(invSpecs owner: -1)
				(invHair owner: -1)
				(invBead owner: -1)
				(invPuz2 owner: -1)
				(invPuzzle owner: -1)
				(invCornSeed owner: -1)
				(invSaltH2O owner: -1)
				(invFreshH2O owner: -1)
				(invRope owner: -1)
				(invPowder owner: -1)
				(invCorn owner: -1)
				(invPear owner: -1)
				(invSaltCrystal owner: -1)
				(invTurquoiseShape owner: -1)
				(invRat owner: -1)
				(invGoldBowl owner: -1)
				(invSilSpoon owner: -1)
				(invBeetle owner: -1)
				(invDragScale owner: -1)
				(invPellet owner: -1)
				(invShield owner: -1)
				(invShieldSpike owner: -1)
				(invDragToad owner: -1)
				(invMagRope owner: -1)
				(invBrassBowl owner: -1)
				(invSulfur owner: -1)
				(invLantern owner: -1)
				(invSpark owner: -1)
				(invBigGem owner: -1)
				(invHamChisel owner: -1)
				(invCrook owner: -1)
				(invNectar owner: -1)
				(invFeather owner: -1)
				(invChinaBird owner: -1)
				(invMask owner: -1)
				(invNickel owner: -1)
				(invBook owner: -1)
				(invRubberChicken owner: -1)
				(invStatue owner: -1)
				(invMoon owner: -1)
				(invDiggerHorn owner: -1)
				(invBackBone owner: -1)
				(invPet owner: -1)
				(invDefoliant owner: -1)
				(invMagicWand owner: -1)
				(invVeil owner: -1)
				(invScarab owner: -1)
				(invShovel owner: -1)
				(invDiggerRat owner: -1)
				(invLife owner: -1)
				(invFootInBag owner: -1)
				(invFlower owner: -1)
				(invStocking owner: -1)
				(invDevice owner: -1)
				(invSling owner: -1)
				(invGrape owner: -1)
				(invWereSalve owner: -1)
				(invPomGranate owner: -1)
				(invAmbrosia owner: -1)
				(invDreamCatcher owner: -1)
				(invBridle owner: -1)
				(invTapestry owner: -1)
				(invCrystal owner: -1)
				(invFemur owner: -1)
				(invMedal owner: -1)
				(invFirecracker owner: -1)
				(invHead owner: -1)
				(invFife owner: -1)
		)
	)
)

(class ScaryInventory of View
	(properties
		sightAngle 360
		verb 0
		owner 0
		hiliteState 0
		enabled 1
		newX 0
		newY 0
	)
	
	(method (init)
		(if (and (== owner global104) (!= (user message?) verb))
			(self setPri: 254 ignoreActors: 1)
			(theInterfaceCast addToFront: self)
			(super init: theInterfaceCast)
		)
	)
	
	(method (show)
		(if newX (= x newX) (= y newY) (= newX (= newY 0)))
		(super show:)
	)
	
	(method (doVerb theVerb)
		(if (and enabled (== theVerb 10))
			(user message: verb)
			(invCursor view: view loop: loop cel: cel init:)
			(theGame setCursor: invCursor)
			(= curInvItem self)
			(self hide:)
		else
			(super doVerb: theVerb)
		)
	)
	
	(method (isMyVerb theVerb)
		(return (== verb theVerb))
	)
	
	(method (enable tOrF)
		(if (and argc (not tOrF))
			(= enabled FALSE)
		else
			(= enabled TRUE)
		)
	)
	
	(method (moveTo whom)
		(= owner whom)
		(return self)
	)
	
	(method (ownedBy whom)
		(return (== owner whom))
	)
)

(instance invComb of ScaryInventory
	(properties
		modNum 18
		view 990
		verb 5
	)
)

(instance invPeticoat of ScaryInventory
	(properties
		modNum 18
		view 990
		cel 2
		verb 6
	)
	
	(method (doVerb theVerb)
		(return
			(if (and enabled (== theVerb 11))
				(invCursor
					view: (invFlag view?)
					loop: (invFlag loop?)
					cel: (invFlag cel?)
				)
				(invStick owner: -1 hide:)
				(-- global171)
				(invPeticoat owner: -1 hide:)
				(-- global171)
				(ego get: 4)
				(invFlag hide:)
				(user message: 13)
				(theGame setCursor: invCursor)
				(= curInvItem invFlag)
				(reposition doit:)
				(= theExitFeature 0)
				(self setHotspot: 0)
				(return 1)
			else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance invPot of ScaryInventory
	(properties
		modNum 18
		view 990
		cel 4
		verb 12
	)
)

(instance invStick of ScaryInventory
	(properties
		modNum 18
		view 990
		cel 6
		verb 11
	)
	
	(method (doVerb theVerb)
		(return
			(if (and enabled (== theVerb 6))
				(invCursor
					view: (invFlag view?)
					loop: (invFlag loop?)
					cel: (invFlag cel?)
				)
				(invStick owner: -1 hide:)
				(-- global171)
				(invPeticoat owner: -1 hide:)
				(-- global171)
				(ego get: 4)
				(invFlag hide:)
				(user message: 13)
				(theGame setCursor: invCursor)
				(= curInvItem invFlag)
				(reposition doit:)
				(= theExitFeature 0)
				(self setHotspot: 0)
				(return 1)
			else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance invFlag of ScaryInventory
	(properties
		modNum 18
		view 990
		cel 8
		verb 13
	)
)

(instance invPuz1 of ScaryInventory
	(properties
		modNum 18
		view 990
		cel 10
		verb 20
	)
	
	(method (doVerb theVerb)
		(return
			(if (and enabled (== theVerb 22))
				(invCursor
					view: (invPuzzle view?)
					loop: (invPuzzle loop?)
					cel: (invPuzzle cel?)
				)
				(invPuz1 owner: -1 hide:)
				(-- global171)
				(invPuz2 owner: -1 hide:)
				(-- global171)
				(ego get: 13)
				(invPuzzle hide:)
				(user message: 23)
				(theGame setCursor: invCursor)
				(= curInvItem invPuzzle)
				(reposition doit:)
				(return 1)
			else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance invSeed of ScaryInventory
	(properties
		modNum 18
		view 990
		cel 12
		verb 15
	)
)

(instance invBasket of ScaryInventory
	(properties
		modNum 18
		view 990
		cel 14
		verb 17
	)
)

(instance invHorn of ScaryInventory
	(properties
		modNum 18
		view 990
		loop 1
		verb 18
	)
)

(instance invSpecs of ScaryInventory
	(properties
		modNum 18
		view 990
		loop 1
		cel 2
		verb 19
	)
)

(instance invHair of ScaryInventory
	(properties
		modNum 18
		view 990
		loop 1
		cel 4
		verb 21
	)
)

(instance invBead of ScaryInventory
	(properties
		modNum 18
		view 990
		loop 1
		cel 6
		verb 16
	)
)

(instance invPuz2 of ScaryInventory
	(properties
		modNum 18
		view 990
		loop 1
		cel 8
		verb 22
	)
	
	(method (doVerb theVerb)
		(return
			(if (and enabled (== theVerb 20))
				(invCursor
					view: (invPuzzle view?)
					loop: (invPuzzle loop?)
					cel: (invPuzzle cel?)
				)
				(invPuz1 owner: -1 hide:)
				(-- global171)
				(invPuz2 owner: -1 hide:)
				(-- global171)
				(ego get: 13)
				(invPuzzle hide:)
				(user message: 23)
				(theGame setCursor: invCursor)
				(= curInvItem invPuzzle)
				(reposition doit:)
				(return 1)
			else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance invPuzzle of ScaryInventory
	(properties
		modNum 18
		view 990
		loop 1
		cel 10
		verb 23
	)
)

(instance invCornSeed of ScaryInventory
	(properties
		modNum 18
		view 990
		loop 1
		cel 12
		verb 24
	)
)

(instance invSaltH2O of ScaryInventory
	(properties
		modNum 18
		view 990
		loop 1
		cel 14
		verb 27
	)
)

(instance invFreshH2O of ScaryInventory
	(properties
		modNum 18
		view 990
		loop 2
		verb 28
	)
)

(instance invRope of ScaryInventory
	(properties
		modNum 18
		view 990
		loop 2
		cel 2
		verb 25
	)
)

(instance invPowder of ScaryInventory
	(properties
		modNum 18
		view 990
		loop 2
		cel 4
		verb 26
	)
)

(instance invCorn of ScaryInventory
	(properties
		modNum 18
		view 990
		loop 2
		cel 8
		verb 30
	)
)

(instance invPear of ScaryInventory
	(properties
		modNum 18
		view 990
		loop 2
		cel 10
		verb 50
	)
)

(instance invRat of ScaryInventory
	(properties
		modNum 18
		view 991
		verb 32
	)
)

(instance invGoldBowl of ScaryInventory
	(properties
		modNum 18
		view 991
		cel 2
		verb 33
	)
)

(instance invSilSpoon of ScaryInventory
	(properties
		modNum 18
		view 991
		cel 4
		verb 34
	)
)

(instance invBeetle of ScaryInventory
	(properties
		modNum 18
		view 991
		cel 6
		verb 35
	)
)

(instance invDragScale of ScaryInventory
	(properties
		modNum 18
		view 991
		cel 8
		verb 36
	)
)

(instance invPellet of ScaryInventory
	(properties
		modNum 18
		view 991
		cel 10
		verb 37
	)
	
	(method (doVerb theVerb)
		(return
			(if (and enabled (== theVerb 84))
				(invCursor
					view: (invSling view?)
					loop: (invSling loop?)
					cel: (invSling cel?)
				)
				(invPellet owner: -1 hide:)
				(-- global171)
				(invStocking owner: -1 hide:)
				(-- global171)
				(ego get: 63)
				(invSling hide:)
				(user message: 86)
				(theGame setCursor: invCursor)
				(= curInvItem invSling)
				(reposition doit:)
				(= theExitFeature 0)
				(self setHotspot: 0)
				(return 1)
			else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance invShield of ScaryInventory
	(properties
		modNum 18
		view 991
		cel 12
		verb 38
	)
)

(instance invShieldSpike of ScaryInventory
	(properties
		modNum 18
		view 991
		loop 3
		cel 12
		verb 97
	)
)

(instance invDragToad of ScaryInventory
	(properties
		modNum 18
		view 991
		cel 14
		verb 39
	)
)

(instance invMagRope of ScaryInventory
	(properties
		modNum 18
		view 991
		loop 1
		verb 40
	)
)

(instance invBrassBowl of ScaryInventory
	(properties
		modNum 18
		view 991
		cel 2
		verb 95
	)
)

(instance invSulfur of ScaryInventory
	(properties
		modNum 18
		view 991
		loop 1
		cel 4
		verb 41
	)
)

(instance invLantern of ScaryInventory
	(properties
		modNum 18
		view 991
		loop 1
		cel 6
		verb 43
	)
)

(instance invSpark of ScaryInventory
	(properties
		modNum 18
		view 991
		loop 1
		cel 8
		verb 44
	)
)

(instance invBigGem of ScaryInventory
	(properties
		modNum 18
		view 991
		loop 1
		cel 10
		verb 45
	)
)

(instance invHamChisel of ScaryInventory
	(properties
		modNum 18
		view 991
		loop 1
		cel 12
		verb 46
	)
)

(instance invSaltCrystal of ScaryInventory
	(properties
		modNum 18
		view 991
		loop 1
		cel 14
		verb 51
	)
)

(instance invTurquoiseShape of ScaryInventory
	(properties
		modNum 18
		view 991
		loop 2
		verb 53
	)
)

(instance invCrook of ScaryInventory
	(properties
		modNum 18
		view 991
		loop 2
		cel 2
		verb 52
	)
)

(instance invNectar of ScaryInventory
	(properties
		modNum 18
		view 991
		loop 2
		cel 4
		verb 54
	)
)

(instance invFeather of ScaryInventory
	(properties
		modNum 18
		view 991
		loop 2
		cel 6
		verb 55
	)
)

(instance invChinaBird of ScaryInventory
	(properties
		modNum 18
		view 991
		loop 2
		cel 8
		verb 56
	)
)

(instance invMask of ScaryInventory
	(properties
		modNum 18
		view 991
		loop 2
		cel 10
		verb 57
	)
)

(instance invNickel of ScaryInventory
	(properties
		modNum 18
		view 991
		loop 2
		cel 12
		verb 59
	)
)

(instance invBook of ScaryInventory
	(properties
		modNum 18
		view 991
		loop 2
		cel 14
		verb 58
	)
)

(instance invRubberChicken of ScaryInventory
	(properties
		modNum 18
		view 991
		loop 3
		verb 60
	)
	
	(method (doVerb theVerb)
		(if (not (Btst 107))
			(Prints
				{How about that you find a feather on the chicken.}
			)
			(ego get: 41)
			(Bset 107)
			(super doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
		(return 1)
	)
)

(instance invStatue of ScaryInventory
	(properties
		modNum 18
		view 991
		loop 3
		cel 2
		verb 61
	)
	
	(method (doVerb theVerb)
		(if (and (== theVerb 5) (not (Btst 193)))
			(Prints {See vision of Rosella in Ooga Booga.})
			(Bset 193)
		else
			(super doVerb: theVerb)
		)
		(return 1)
	)
)

(instance invDiggerHorn of ScaryInventory
	(properties
		modNum 18
		view 991
		loop 3
		cel 4
		verb 65
	)
)

(instance invBackBone of ScaryInventory
	(properties
		modNum 18
		view 991
		loop 3
		cel 6
		verb 66
	)
)

(instance invPet of ScaryInventory
	(properties
		modNum 18
		view 991
		loop 3
		cel 8
		verb 67
	)
)

(instance invDefoliant of ScaryInventory
	(properties
		modNum 18
		view 991
		loop 3
		cel 10
		verb 68
	)
)

(instance invMagicWand of ScaryInventory
	(properties
		modNum 18
		view 992
		verb 69
	)
)

(instance invVeil of ScaryInventory
	(properties
		modNum 18
		view 992
		cel 2
		verb 70
	)
)

(instance invMoon of ScaryInventory
	(properties
		modNum 18
		view 992
		cel 4
		verb 71
	)
)

(instance invWereSalve of ScaryInventory
	(properties
		modNum 18
		view 992
		cel 6
		verb 72
	)
	
	(method (doVerb theVerb)
		(return
			(if (== theVerb 21)
				(Prints {See Val put the hair in the salve.})
				(Bset 210)
				(ego put: 10)
				(return 1)
			else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance invPomGranate of ScaryInventory
	(properties
		modNum 18
		view 992
		cel 8
		verb 73
	)
)

(instance invScarab of ScaryInventory
	(properties
		modNum 18
		view 992
		cel 10
		verb 74
	)
	
	(method (doVerb theVerb)
		(asm
			pToa     enabled
			bnt      code_0acd
			lsp      theVerb
			ldi      69
			eq?     
			bnt      code_0acd
			pushi    #owner
			pushi    1
			pushi    65535
			pushi    112
			pushi    0
			lofsa    invScarab
			send     10
			-ag      global171
			pushi    1
			lofsa    {You turn the scarab into the real troll king.}
			push    
			calle    Prints,  2
			lsg      curRoomNum
			ldi      5200
			eq?     
			not     
			bnt      code_0a7c
			pushi    1
			pushi    162
			calle    Bset,  2
			pushi    #cue
			pushi    0
			class    122
			send     4
			jmp      code_0a85
code_0a7c:
			pushi    #notify
			pushi    0
			lag      curRoom
			send     4
code_0a85:
			pushi    #put
			pushi    1
			pushi    55
			lag      ego
			send     6
			pushi    #hide
			pushi    0
			lofsa    invFlag
			send     4
			pushi    #message
			pushi    1
			pushi    69
			lag      user
			send     6
			pushi    #setCursor
			pushi    1
			lsg      invCursor
			lag      theGame
			send     6
			lofsa    invMagicWand
			sag      curInvItem
			pushi    #doit
			pushi    0
			lofsa    reposition
			send     4
			ldi      0
			sag      theExitFeature
			pushi    #setHotspot
			pushi    1
			pushi    0
			self     6
			ldi      1
			ret     
			jmp      code_0ad7
code_0acd:
			pushi    #doVerb
			pushi    1
			lsp      theVerb
			super    ScaryInventory,  6
code_0ad7:
			ret     
		)
	)
)

(instance invShovel of ScaryInventory
	(properties
		modNum 18
		view 992
		cel 12
		verb 75
	)
)

(instance invAmbrosia of ScaryInventory
	(properties
		modNum 18
		view 992
		cel 14
		verb 76
	)
)

(instance invDiggerRat of ScaryInventory
	(properties
		modNum 18
		view 991
		verb 78
	)
)

(instance invLife of ScaryInventory
	(properties
		modNum 18
		view 992
		loop 1
		verb 77
	)
)

(instance invFootInBag of ScaryInventory
	(properties
		modNum 18
		view 992
		loop 1
		cel 2
		verb 79
	)
)

(instance invFlower of ScaryInventory
	(properties
		modNum 18
		view 992
		loop 1
		cel 4
		verb 80
	)
)

(instance invDreamCatcher of ScaryInventory
	(properties
		modNum 18
		view 992
		loop 1
		cel 6
		verb 81
	)
)

(instance invBridle of ScaryInventory
	(properties
		modNum 18
		view 992
		loop 1
		cel 8
		verb 82
	)
)

(instance invTapestry of ScaryInventory
	(properties
		modNum 18
		view 992
		loop 1
		cel 10
		verb 83
	)
)

(instance invStocking of ScaryInventory
	(properties
		modNum 18
		view 992
		loop 1
		cel 12
		verb 84
	)
	
	(method (doVerb theVerb)
		(return
			(if (and enabled (== theVerb 37))
				(invCursor
					view: (invSling view?)
					loop: (invSling loop?)
					cel: (invSling cel?)
				)
				(invPellet owner: -1 hide:)
				(-- global171)
				(invStocking owner: -1 hide:)
				(-- global171)
				(ego get: 63)
				(invSling hide:)
				(user message: 86)
				(theGame setCursor: invCursor)
				(= curInvItem invSling)
				(reposition doit:)
				(= theExitFeature 0)
				(self setHotspot: 0)
				(return 1)
			else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance invDevice of ScaryInventory
	(properties
		modNum 18
		view 992
		loop 1
		cel 14
		verb 85
	)
)

(instance invCrystal of ScaryInventory
	(properties
		modNum 18
		view 992
		loop 2
		verb 87
	)
)

(instance invSling of ScaryInventory
	(properties
		modNum 18
		view 992
		loop 2
		cel 2
		verb 86
	)
)

(instance invGrape of ScaryInventory
	(properties
		modNum 18
		view 992
		loop 2
		cel 4
		verb 88
	)
)

(instance invFemur of ScaryInventory
	(properties
		modNum 18
		view 992
		loop 2
		cel 6
		verb 90
	)
)

(instance invMedal of ScaryInventory
	(properties
		modNum 18
		view 992
		loop 2
		cel 8
		verb 89
	)
)

(instance invFirecracker of ScaryInventory
	(properties
		modNum 18
		view 992
		loop 2
		cel 10
		verb 92
	)
)

(instance invHead of ScaryInventory
	(properties
		modNum 18
		view 992
		loop 2
		cel 12
		verb 93
	)
)

(instance invFife of ScaryInventory
	(properties
		modNum 18
		view 992
		loop 2
		cel 14
		verb 94
	)
)

(instance reposition of Code
	(properties)
	
	(method (doit &tmp temp0 temp1 temp2 temp3 temp4)
		(= temp3 0)
		(= temp4 0)
		(while (< temp4 (inventory size:))
			(if
			((= temp2 (inventory at: temp4)) ownedBy: global104)
				(if (< temp3 7)
					(= temp0 (+ 70 (* temp3 22)))
				else
					(= temp0 (+ 70 (* (- temp3 7) 22)))
				)
				(= temp1 (if (< temp3 7) 15 else 35))
				(if (temp2 isNotHidden:)
					(temp2 posn: temp0 temp1)
					(UpdateScreenItem temp2)
				else
					(temp2 newX: temp0 newY: temp1)
				)
				(++ temp3)
			)
			(= temp4 (+ temp4 1))
		)
	)
)
