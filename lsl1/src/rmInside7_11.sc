;;; Sierra Script 1.0 - (do not remove this comment)
(script# rmInside7_11) ;510
(include game.sh)
(use Main)
(use Intrface)
(use LLRoom)
(use Osc)
(use RandCyc)
(use PolyPath)
(use Polygon)
(use Feature)
(use ForCount)
(use LoadMany)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm510 0
)

(local
	lubberKind
	lubberTexture
	lubberColor
	lubberSurface
	lubberPattern
	lubberFlavor
	lubberWeight
	lubberThickness
	lubberProtection
	lubberSize
	sprayPrice
	lubberPrice
	winePrice
	magazinePrice
	moneyOwed
	owedForLubber
	local16
	local17
	clerkTalkCount
	local19
	local20
	seeLubberSign
	findJugsMagazine
)
(procedure (AddUpMoneyOwed)
	(= moneyOwed (+ moneyOwed lubberPrice sprayPrice winePrice magazinePrice))
	(= local16 1)
	(= local17 1)
)

(instance rm510 of LLRoom
	(properties
		lookStr
			{You are in a 24-hour convenience store. 
			There is a magazine rack near the front door, with a sign reading "This no riblaly -- no leeding." 
			Except for the clerk standing behind the counter, the store appears to be deserted. A small sign graces the counter.}
		picture rmInside7_11
		south rmOutside7_11
		west rmOutside7_11
	)
	
	(method (init)
		(LoadMany VIEW 511 512 510 513)
		(LoadMany SOUND 510 514 513 511 512)
		(LoadMany FONT giantFont)
		(theMusic number: 510 vol: 127 loop: -1 flags: mNOPAUSE play:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0 319 0 319 189 76 189 76 184 311 184 311 154 236 130
						236 128 247 120 194 110 26 121 56 136 56 157 38 164 4 167
						4 184 51 184 51 189 0 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 107 173 279 173 279 181 107 181
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 61 150 163 150 163 159 102 159 97 163 61 163
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 165 150 263 150 263 159 165 159
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 62 130 221 130 221 143 62 143
					yourself:
				)
		)
		(switch prevRoomNum
			(500 (ego x: 60 init:))
			(else  (ego init: x: 60 y: 180))
		)
		(cam1 setCycle: Oscillate init:)
		(cam2 setCycle: Oscillate init:)
		(cam3 setCycle: Oscillate init:)
		(cam4 setCycle: Oscillate init:)
		(cam5 setCycle: Oscillate init:)
		(cam6 setCycle: Oscillate init:)
		(cam7 setCycle: Oscillate init:)
		(cam8 setCycle: Oscillate init:)
		(cam9 setCycle: Oscillate init:)
		(cam10 setCycle: Oscillate init:)
		(cam11 setCycle: Oscillate init:)
		(cam12 setCycle: Oscillate init:)
		(lubberSign init: stopUpd: approachVerbs: verbDo verbUse verbZipper verbTaste verbLook)
		(pClerk init: approachVerbs: verbDo verbUse verbZipper verbTaste verbTalk verbLook)
		(sfxGunShot init:)
		(fShelves init:)
		(fShelves1 init:)
		(fMagazineStand init: approachVerbs: verbDo verbUse verbZipper verbTaste verbLook)
		(fWineShelves init: approachVerbs: verbDo verbUse verbZipper verbTaste verbLook)
		(fWineShelves1 init: approachVerbs: verbDo verbUse verbZipper verbTaste verbLook)
		(fWineShelves2 init: approachVerbs: verbDo verbUse verbZipper verbTaste verbLook)
		(fFreezer init:)
		(fBreathSpray init: approachVerbs: verbDo verbUse verbZipper verbTaste verbLook)
		(microwave init:)
		(checkoutCounter init: approachVerbs: verbDo verbUse verbZipper verbTaste verbLook)
		(super init:)
	)
	
	(method (doit)
		(super doit: &rest)
		(if
			(and
				local16
				local17
				(ego inRect: 45 180 81 190)
				(== (curRoom script?) 0)
			)
			(curRoom setScript: sShootLarry)
		)
	)
	
	(method (dispose)
		(if (and lubberFlavor (== local17 0))
			(if modelessDialog (modelessDialog dispose:))
			(Printf 510 0
				lubberWeight
				lubberThickness
				lubberFlavor
				lubberPattern
				lubberSurface
				lubberColor
				lubberTexture
				lubberKind
				lubberProtection
			)
		)
		(theMusic fade:)
		(super dispose:)
	)
)

(instance sShootLarry of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					cycleSpeed: 3
					setMotion: 0
					view: 511
					setLoop: 0
					setCel: 0
				)
				(pClerk
					cycleSpeed: 3
					setPri: 11
					setLoop: 2
					setCycle: CycleTo 6 1 self
				)
				(Print 510 1 #font bigFont #icon 512 2 0 #at -1 20)
			)
			(1
				(sfxGunShot play:)
				(pClerk setCycle: EndLoop self)
				(Print 510 2)
			)
			(2 (ego setCycle: EndLoop self))
			(3 (= seconds 4))
			(4
				(theMusic fade:)
				(ShowDeathIcon 511 2 1)
				(Format @str1 510 3)
				(EgoDead 510 4)
			)
		)
	)
)

(instance sBuyLubber of Script
	(properties)
	
	(method (doit &tmp temp0)
		(super doit:)
		(if (and local19 (== 4 (++ local20)))
			(= local20 0)
			(if (== (= temp0 ((ego _head?) cel?)) 5)
				(= cycles 5)
			)
			((ego _head?)
				cel:
					(switch temp0
						(0 4)
						(1 7)
						(2 5)
						(3 6)
						(4 2)
						(5 1)
						(6 0)
						(7 3)
					)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== ((inventory at: 8) owner?) ego)
					(if modelessDialog (modelessDialog dispose:))
					(Print 510 5)
					(self dispose:)
				else
					(HandsOff)
					(ego setMotion: PolyPath 50 160 self)
				)
			)
			(1
				(Face ego pClerk)
				(ego moveHead: 0)
				(= seconds 3)
			)
			(2 (= local19 1))
			(3
				(= local19 0)
				((ego _head?) cel: 1)
				(= seconds 3)
			)
			(4
				(if modelessDialog (modelessDialog dispose:))
				(Print 510 6)
				(= seconds 3)
			)
			(5
				(cam1 setCel: 6)
				(cam2 setCel: 0)
				(cam3 setCel: 0)
				(cam4 setCel: 1)
				(cam5 setCel: 4)
				(cam6 setCel: 0)
				(cam7 setCel: 2)
				(cam8 setCel: 0)
				(cam9 setCel: 0)
				(cam10 setCel: 2)
				(cam11 setCel: 0)
				(cam12 setCel: 4)
				(pClerk
					cycleSpeed: (+ 1 howFast)
					setCycle: RandCycle 20 self
				)
			)
			(6
				(if modelessDialog (modelessDialog dispose:))
				(cam1 stopUpd:)
				(cam2 stopUpd:)
				(cam3 stopUpd:)
				(cam4 stopUpd:)
				(cam5 stopUpd:)
				(cam6 stopUpd:)
				(cam7 stopUpd:)
				(cam8 stopUpd:)
				(cam9 stopUpd:)
				(cam10 stopUpd:)
				(cam11 stopUpd:)
				(cam12 stopUpd:)
				(Print 510 7)
				(Print 510 8 #at -1 140)
				(pClerk setCycle: RandCycle 18 self)
			)
			(7
				(theGame setCursor: ARROW_CURSOR TRUE)
				(Animate (cast elements?) FALSE)
				(if
				(Print 510 9 #button {Lambskin} 0 #button {Latex} 1)
					(= lubberKind {latex})
				else
					(= lubberKind {lambskin})
				)
				(pClerk setCycle: RandCycle 16 self)
			)
			(8
				(if
				(Print 510 10 #button {Smooth} 0 #button {Ribbed} 1)
					(= lubberTexture {libbed})
				else
					(= lubberTexture {smooth})
				)
				(pClerk setCycle: RandCycle 14 self)
			)
			(9
				(if
				(Print 510 11 #button {Colored} 0 #button {Plain} 1)
					(= lubberColor {plain})
				else
					(= lubberColor {colored})
				)
				(pClerk setCycle: RandCycle 12 self)
			)
			(10
				(if
					(Print 510 12 #button {Lubricated} 0 #button {Rough-cut} 1)
					(= lubberSurface {rough-cut})
				else
					(= lubberSurface {lubricated})
				)
				(pClerk setCycle: RandCycle 10 self)
			)
			(11
				(if
				(Print 510 13 #button {Striped} 0 #button {Plaid} 1)
					(= lubberPattern {plaid})
				else
					(= lubberPattern {striped})
				)
				(pClerk setCycle: RandCycle 8 self)
			)
			(12
				(if
					(Print 510 14 #button {Peppermint} 0 #button {Spearmint} 1)
					(= lubberFlavor {spearmint})
				else
					(= lubberFlavor {peppermint})
				)
				(pClerk setCycle: RandCycle 6 self)
			)
			(13
				(if
				(Print 510 15 #button {Light} 0 #button {Heavy} 1)
					(= lubberWeight {heavy})
				else
					(= lubberWeight {light})
				)
				(pClerk setCycle: RandCycle 4 self)
			)
			(14
				(if
				(Print 510 16 #button {Normal} 0 #button {Industrial} 1)
					(= lubberThickness {industrial})
				else
					(= lubberThickness {normal})
				)
				(pClerk setCycle: RandCycle 4 self)
			)
			(15
				(if
				(Print 510 17 #button {Plain} 0 #button {Spermicide} 1)
					(= lubberProtection {spermicidal})
				else
					(= lubberProtection {non-spermicidal})
				)
				(pClerk setCycle: RandCycle 4 self)
			)
			(16
				(switch
					(Print 510 18
						#button {Large} 0
						#button {Giant} 1
						#button {Gonzo} 2
					)
					(0 (= lubberSize {large}))
					(1 (= lubberSize {giant}))
					(2 (= lubberSize {gonzo}))
				)
				(theGame setCursor: ((theIconBar curIcon?) cursor?) TRUE)
				(pClerk setCycle: RandCycle 4 self)
			)
			(17
				(Print 510 19)
				(pClerk setCycle: RandCycle 4 self)
			)
			(18
				(Printf 510 20
					lubberWeight
					lubberThickness
					lubberFlavor
					lubberPattern
					lubberSurface
					lubberColor
					lubberTexture
					lubberKind
					lubberProtection
					#at 15 -1
					#width 280
				)
				(theMusic stop:)
				(= seconds 2)
			)
			(19
				(sfxHeadsOut init: play:)
				(pDudeLeft init:)
				(pKid init:)
				(pDudeUp init:)
				(pGranny init:)
				(pRabi init:)
				(= seconds 2)
			)
			(20
				(sfxPervert play:)
				(Print 510 21 #font giantFont #mode teJustCenter #at -1 4 #width 250)
				(sfxHeadsIn init: play:)
				(ego get: iLubber)
				(pDudeLeft dispose:)
				(pKid dispose:)
				(pDudeUp dispose:)
				(pGranny dispose:)
				(pRabi dispose:)
				(cam1 setCycle: Oscillate)
				(cam2 setCycle: Oscillate)
				(cam3 setCycle: Oscillate)
				(cam4 setCycle: Oscillate)
				(cam5 setCycle: Oscillate)
				(cam6 setCycle: Oscillate)
				(cam7 setCycle: Oscillate)
				(cam8 setCycle: Oscillate)
				(cam9 setCycle: Oscillate)
				(cam10 setCycle: Oscillate)
				(cam11 setCycle: Oscillate)
				(cam12 setCycle: Oscillate)
				(= lubberPrice (Random 4 10))
				(AddUpMoneyOwed)
				(pClerk setCycle: RandCycle 4 self)
			)
			(21
				(SolvePuzzle fBoughtLubber 4)
				(Printf 510 22 moneyOwed)
				(= owedForLubber TRUE)
				(theMusic play:)
				(HandsOn)
				(NormalEgo 1)
				(self dispose:)
			)
		)
	)
)

(instance sGrabWine of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Face ego fWineShelves)
				(= cycles 7)
			)
			(1
				(ego
					view: 511
					setLoop: 1
					setCel: 0
					cycleSpeed: (* 2 howFast)
					setCycle: EndLoop self
				)
			)
			(2
				(ego get: iWine)
				(SolvePuzzle fGotWineBox 1)
				(= cycles 8)
			)
			(3
				(if modelessDialog (modelessDialog dispose:))
				(Print 510 23)
				(= cycles 3)
			)
			(4
				(HandsOn)
				(NormalEgo 3)
				(self dispose:)
			)
		)
	)
)

(instance sGrabBreathSpray of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Face ego fBreathSpray)
				(= cycles 1)
			)
			(1
				(pClerk setLoop: 0 setCycle: RandCycle 10 self)
			)
			(2
				(if modelessDialog (modelessDialog dispose:))
				(Print 510 24)
				(ego get: iBreathSpray)
				(= cycles 3)
			)
			(3 (self dispose:))
		)
	)
)

(instance sfxPervert of Sound
	(properties
		flags mNOPAUSE
		number 512
	)
)

(instance sfxGunShot of Sound
	(properties
		number 514
	)
)

(instance sfxHeadsIn of Sound
	(properties
		number 513
	)
)

(instance sfxHeadsOut of Sound
	(properties
		number 511
	)
)

(instance pClerk of Person
	(properties
		x 8
		y 131
		description {the store clerk}
		approachX 50
		approachY 160
		lookStr {What law says that convenience store clerks must be non-English speaking?!}
		view 512
		priority 10
		signal fixPriOn
	)
	
	(method (doVerb theVerb theItem)
		(if modelessDialog (modelessDialog dispose:))
		(switch theVerb
			(verbDo (Print 510 25))
			(verbZipper
				(curRoom setScript: sBuyLubber)
			)
			(verbUse
				(switch theItem
					(iWallet
						(cond 
							((and local16 local17 (>= dollars moneyOwed))
								(if (== owedForLubber FALSE) (Printf 510 26 moneyOwed))
								(= dollars (- dollars moneyOwed))
								(= local16 0)
								(= local17 0)
								(= magazinePrice 0)
								(= winePrice 0)
								(= sprayPrice 0)
								(= lubberPrice 0)
								(= moneyOwed 0)
								(Print 510 27)
								(Print 510 28)
							)
							((< dollars moneyOwed) (Printf 510 29 dollars) (Print 510 30 #at -1 140))
							(else (Print 510 31) (= dollars (- dollars 10)))
						)
					)
					(else 
						(super doVerb: theVerb theItem &rest)
					)
				)
			)
			(5
				(if seeLubberSign
					(curRoom setScript: sBuyLubber)
				else
					(pClerk setLoop: 0 setCycle: ForwardCounter 5 self)
					(switch clerkTalkCount
						(0 (Print 510 32 #dispose))
						(1 (Print 510 33 #dispose))
						(2 (Print 510 34 #dispose))
						(3 (Print 510 35 #dispose))
						(4 (Print 510 36 #dispose))
						(5 (Print 510 37 #dispose))
						(6 (Print 510 38 #dispose))
					)
					(if (< clerkTalkCount 6) (++ clerkTalkCount) else (= clerkTalkCount 0))
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
	
	(method (cue)
		(if modelessDialog (modelessDialog dispose:))
		(pClerk setCycle: 0)
	)
)

(instance cam1 of Prop
	(properties
		x 8
		y 55
		description {the security camera}
		lookStr {Saddam is watching.}
		view 510
		loop 3
		cel 6
		priority 15
		signal fixPriOn
		cycleSpeed 10
		detailLevel 1
	)
	
	(method (doVerb theVerb theItem)
		(if modelessDialog (modelessDialog dispose:))
		(switch theVerb
			(verbDo (Print 510 39))
			(verbZipper (Print 510 40))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance cam2 of Prop
	(properties
		x 27
		y 55
		description {the security camera}
		lookStr {Saddam is watching.}
		view 510
		loop 1
		priority 15
		signal fixPriOn
		cycleSpeed 15
		detailLevel 2
	)
	
	(method (doVerb theVerb theItem)
		(cam1 doVerb: theVerb theItem)
	)
)

(instance cam3 of Prop
	(properties
		x 46
		y 55
		description {the security camera}
		lookStr {Saddam is watching.}
		view 510
		loop 2
		priority 15
		signal fixPriOn
		cycleSpeed 5
		detailLevel 3
	)
	
	(method (doVerb theVerb theItem)
		(cam1 doVerb: theVerb theItem)
	)
)

(instance cam4 of Prop
	(properties
		x 67
		y 40
		description {the security camera}
		lookStr {Saddam is watching.}
		view 510
		loop 1
		cel 1
		cycleSpeed 10
		detailLevel 1
	)
	
	(method (doVerb theVerb theItem)
		(cam1 doVerb: theVerb theItem)
	)
)

(instance cam5 of Prop
	(properties
		x 93
		y 39
		description {the security camera}
		lookStr {Saddam is watching.}
		view 510
		loop 2
		cel 4
		cycleSpeed 25
		detailLevel 2
	)
	
	(method (doVerb theVerb theItem)
		(cam1 doVerb: theVerb theItem)
	)
)

(instance cam6 of Prop
	(properties
		x 121
		y 38
		description {the security camera}
		lookStr {Saddam is watching.}
		view 510
		loop 3
		cycleSpeed 20
		detailLevel 2
	)
	
	(method (doVerb theVerb theItem)
		(cam1 doVerb: theVerb theItem)
	)
)

(instance cam7 of Prop
	(properties
		x 150
		y 37
		description {the security camera}
		lookStr {Saddam is watching.}
		view 510
		loop 1
		cel 2
		cycleSpeed 30
		detailLevel 3
	)
	
	(method (doVerb theVerb theItem)
		(cam1 doVerb: theVerb theItem)
	)
)

(instance cam8 of Prop
	(properties
		x 181
		y 36
		description {the security camera}
		lookStr {Saddam is watching.}
		view 510
		loop 3
		cycleSpeed 15
		detailLevel 1
	)
	
	(method (doVerb theVerb theItem)
		(cam1 doVerb: theVerb theItem)
	)
)

(instance cam9 of Prop
	(properties
		x 209
		y 37
		description {the security camera}
		lookStr {Saddam is watching.}
		view 510
		cycleSpeed 10
		detailLevel 2
	)
	
	(method (doVerb theVerb theItem)
		(cam1 doVerb: theVerb theItem)
	)
)

(instance cam10 of Prop
	(properties
		x 238
		y 39
		description {the security camera}
		lookStr {Saddam is watching.}
		view 510
		loop 1
		cel 2
		cycleSpeed 20
		detailLevel 3
	)
	
	(method (doVerb theVerb theItem)
		(cam1 doVerb: theVerb theItem)
	)
)

(instance cam11 of Prop
	(properties
		x 269
		y 41
		description {the security camera}
		lookStr {Saddam is watching.}
		view 510
		loop 3
		cycleSpeed 15
		detailLevel 1
	)
	
	(method (doVerb theVerb theItem)
		(cam1 doVerb: theVerb theItem)
	)
)

(instance cam12 of Prop
	(properties
		x 299
		y 43
		description {the security camera}
		lookStr {Saddam is watching.}
		view 510
		loop 2
		cel 4
		cycleSpeed 10
		detailLevel 2
	)
	
	(method (doVerb theVerb theItem)
		(cam1 doVerb: theVerb theItem)
	)
)

(instance pDudeLeft of Prop
	(properties
		x 64
		y 98
		view 513
		priority 12
		signal  fixPriOn
	)
)

(instance pKid of Prop
	(properties
		x 106
		y 89
		view 513
		loop 1
		priority 5
		signal  fixPriOn
	)
)

(instance pDudeUp of Prop
	(properties
		x 162
		y 145
		view 513
		loop 2
		priority 10
		signal  fixPriOn
	)
)

(instance pGranny of Prop
	(properties
		x 204
		y 142
		view 513
		loop 3
		priority 12
		signal  fixPriOn
	)
)

(instance pRabi of Prop
	(properties
		x 267
		y 159
		view 513
		loop 4
		priority 14
		signal  fixPriOn
	)
)

(instance fShelves of Feature
	(properties
		x 97
		y 121
		nsTop 86
		nsLeft 74
		nsBottom 157
		nsRight 120
		description {the store shelves}
		sightAngle 40
		lookStr {The shelves are filled with all the necessities of life: 
		"Dwinkies," "Wallo-Wars," "Loritos," various colas, cheap wine, and coffee. 
		You don't see anything you really need in this part of the store.}
	)
	
	(method (doVerb theVerb theItem)
		(if modelessDialog (modelessDialog dispose:))
		(switch theVerb
			(verbLook (Print 510 41))
			(verbTaste (Print 510 42))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance fShelves1 of Feature
	(properties
		x 180
		y 75
		nsTop 78
		nsLeft 120
		nsBottom 139
		nsRight 241
		description {the store shelves}
		sightAngle 40
		lookStr {The shelves are filled with all the necessities of life: 
		"Dwinkies," "Wallo-Wars," "Loritos," various colas, cheap wine, and coffee. 
		You don't see anything you really need in this part of the store.}
	)
	
	(method (doVerb theVerb theItem)
		(fShelves doVerb: theVerb theItem)
	)
)

(instance fMagazineStand of Feature
	(properties
		x 192
		y 160
		nsTop 140
		nsLeft 121
		nsBottom 181
		nsRight 263
		description {the magazine rack}
		sightAngle 40
		approachX 180
		approachY 185
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(= findJugsMagazine TRUE)
				(Print 510 43)
			)
			(verbDo
				(cond 
					((Btst fGetMagazine)
						(if modelessDialog (modelessDialog dispose:))
						(Print 510 44)
					)
					((== findJugsMagazine TRUE)
						(if modelessDialog (modelessDialog dispose:))
						(= magazinePrice 4)
						(Print 510 45)
						(AddUpMoneyOwed)
						(ego get: iMagazine)
						(SolvePuzzle fGetMagazine 1)
					)
					(else (Print 510 46))
				)
			)
			(else 
				(if modelessDialog (modelessDialog dispose:))
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance fWineShelves of Feature
	(properties
		x 81
		y 70
		nsTop 55
		nsLeft 48
		nsBottom 85
		nsRight 114
		description {the wine shelf}
		sightAngle 40
		approachX 49
		approachY 121
		lookStr {That extra large box of wine looks like a bargain.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(if (!= ((inventory at: iWine) owner?) ego)
					(= winePrice 1)
					(AddUpMoneyOwed)
					(curRoom setScript: sGrabWine)
				else
					(if modelessDialog (modelessDialog dispose:))
					(Print 510 47)
				)
			)
			(else 
				(if modelessDialog (modelessDialog dispose:))
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance fWineShelves1 of Feature
	(properties
		x 36
		y 70
		nsTop 92
		nsBottom 115
		nsRight 72
		description {the wine shelf}
		sightAngle 40
		approachX 49
		approachY 121
		lookStr {That extra large box of wine looks like a bargain.}
	)
	
	(method (doVerb theVerb theItem)
		(fWineShelves doVerb: theVerb theItem)
	)
)

(instance fWineShelves2 of Feature
	(properties
		x 55
		y 70
		nsTop 86
		nsLeft 39
		nsBottom 92
		nsRight 72
		description {the wine shelf}
		sightAngle 40
		approachX 49
		approachY 121
		lookStr {That extra large box of wine looks like a bargain.}
	)
	
	(method (doVerb theVerb theItem)
		(fWineShelves doVerb: theVerb theItem)
	)
)

(instance fFreezer of Feature
	(properties
		x 284
		y 105
		nsTop 72
		nsLeft 250
		nsBottom 139
		nsRight 318
		description {the food freezer}
		sightAngle 40
		lookStr {There's nothing of interest inside the freezer.}
	)
	
	(method (doVerb theVerb theItem)
		(if modelessDialog (modelessDialog dispose:))
		(switch theVerb
			(verbDo (Print 510 48))
			(verbTaste (Print 510 49))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance fBreathSpray of Feature
	(properties
		x 83
		y 136
		nsTop 119
		nsLeft 72
		nsBottom 153
		nsRight 94
		description {the rack filled with breath spray}
		sightAngle 40
		approachX 87
		approachY 165
		lookStr {The counter contains a lovely display rack filled with breath spray. And, it's your brand!}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(if (!= ((inventory at: iBreathSpray) owner?) ego)
					(= sprayPrice 3)
					(AddUpMoneyOwed)
					(curRoom setScript: sGrabBreathSpray)
				else
					(if modelessDialog (modelessDialog dispose:))
					(Print 510 50)
				)
			)
			(verbTaste
				(Print 510 51)
				(Print 510 52)
			)
			(else 
				(if modelessDialog (modelessDialog dispose:))
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance microwave of Feature
	(properties
		x 131
		y 115
		z 47
		nsTop 57
		nsLeft 115
		nsBottom 79
		nsRight 147
		description {the microwave}
		sightAngle 40
		lookStr {You may want to warm something in this microwave oven, but you can't.}
	)
	
	(method (doVerb theVerb theItem)
		(if modelessDialog (modelessDialog dispose:))
		(switch theVerb
			(verbDo (Print 510 53))
			(verbUse
				(switch theItem
					(iApple (Print 510 54))
					(iCandy (Print 510 55))
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

(instance checkoutCounter of Feature
	(properties
		x 22
		y 70
		z 32
		nsTop 132
		nsBottom 158
		nsRight 44
		description {the checkout counter}
		sightAngle 40
		approachX 50
		approachY 160
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(= seeLubberSign 1)
				(Print 510 56)
			)
			(verbDo
				(curRoom setScript: sBuyLubber)
			)
			(verbZipper
				(curRoom setScript: sBuyLubber)
			)
			(else 
				(if modelessDialog (modelessDialog dispose:))
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance lubberSign of View
	(properties
		x 17
		y 136
		description {the small, lubber sign}
		approachX 50
		approachY 160
		view 510
		loop 4
		priority 10
		signal  fixPriOn
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(= seeLubberSign TRUE)
				(Print 510 57)
			)
			(verbDo
				(curRoom setScript: sBuyLubber)
			)
			(verbZipper
				(curRoom setScript: sBuyLubber)
			)
			(else 
				(if modelessDialog (modelessDialog dispose:))
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)
