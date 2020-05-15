;;; Sierra Script 1.0 - (do not remove this comment)
(script# rmBedroom) ;370
(include game.sh)
(use Main)
(use Intrface)
(use LLRoom)
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
	rm370 0
)

(procedure (InitFeatures)
	(painting init:)
	(painting2 init:)
	(lightSwitch
		init:
		approachVerbs: verbDo verbUse verbZipper verbTaste verbTalk verbLook
	)
	(bed init:)
	(pillows init:)
	(flowerBox init:)
	(tree init:)
)

(instance rm370 of LLRoom
	(properties
		lookStr {The Casino Hotel's Penthouse Suite's bedroom is lavishly appointed with the latest in Bill Skirvin art.}
		picture 370
	)
	
	(method (init)
		(cond 
			((== debugging 1) (Bset fFollowingEve))
			((== debugging 2) (Bset fClosetOpen))
		)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 128 79 128 14 166 306 166 306 156 283 136 276 136 256 120
						232 120 243 134 126 134 126 120 110 120 110 124 80 124 80 120
						0 120 0 0 319 0 319 189 0 189
					yourself:
				)
		)
		(switch prevRoomNum
			(360
				(if (Btst fFollowingEve)
					(ego
						egoSpeed: (+ 3 howFast)
						init:
						x: 102
						y: 124
						normal: FALSE
						view: 381
						setLoop: 5
					)
					(LoadMany VIEW 372 371)
					(aEve init:)
					(HandsOff)
					(curRoom setScript: sEveDoesLarry)
				else
					(Load rsVIEW 370)
					(LoadMany 132 370 801)
					(HandsOff)
					(ego init:)
					(curRoom setScript: sFromLivingroom)
				)
			)
			(375
				(LoadMany VIEW 722)
				(LoadMany SOUND 700 801)
				(ego
					init:
					normal: 0
					view: 370
					setLoop: 2
					setPri: 9
					x: 182
					y: 116
					stopUpd:
				)
				(HandsOff)
				(curRoom setScript: sKenSpeaks)
			)
			(else 
				(Load VIEW 370)
				(LoadMany SOUND 370 372 371 801)
				(HandsOff)
				(ego init:)
				(curRoom setScript: sLeakyDoll)
			)
		)
		(fDoor init: approachVerbs: verbDo verbUse verbZipper verbTaste verbLook)
		(InitFeatures)
		(cond 
			((and (Btst fClosetOpen) (Btst fDollInflated)) (door init: setLoop: 1 setCel: 0 stopUpd:))
			((and (Btst fClosetOpen) (not (Btst fDollInflated)))
				(flatDoll init: approachVerbs: verbDo verbUse verbZipper verbTaste)
				(door init: setLoop: 0 setCel: 3 stopUpd:)
			)
			((and (not (Btst fClosetOpen)) (not (Btst fDollInflated)))
				(door cycleSpeed: (+ 3 howFast) init:)
			)
		)
		(super init:)
	)
	
	(method (doit)
		(super doit: &rest)
		(cond 
			(script)
			((IsObjectOnControl ego cBLUE) (HandsOff) (self setScript: sToLivingroom))
		)
	)
)

(instance sEveDoesLarry of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(aEve
					cycleSpeed: (+ 3 howFast)
					setCycle: ForwardCounter 7
				)
				(ego setCycle: Walk setMotion: PolyPath 150 134 self)
			)
			(1
				(aEve stopUpd:)
				(ego
					egoSpeed: (+ 2 egoSpeed)
					setPri: 12
					posn: 166 100
					view: 371
					setLoop: 0
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(2
				(aTowel init:)
				(ego setLoop: 1 setCel: 0 setCycle: EndLoop self)
			)
			(3 (= seconds 3))
			(4
				(ego setLoop: 2 setCel: 0 setCycle: EndLoop self)
			)
			(5 (= seconds 4))
			(6
				(SolvePuzzle fEndGamePoints 25)
				(curRoom newRoom: rmEndCredits)
			)
		)
	)
)

(instance sFromLivingroom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego posn: 53 122 setMotion: PolyPath 79 125 self)
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)

(instance sToLivingroom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 53 122 self)
			)
			(1 (curRoom newRoom: rmLivingRoom))
		)
	)
)

(instance sClosetDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (and (== (door cel?) 0) (not (Btst fClosetOpen)))
					(= cycles 1)
				else
					(Print 370 0)
					(self dispose:)
				)
			)
			(1
				(ego view: 808 setLoop: 2 setCel: 0 setCycle: BegLoop self)
				(Bset fClosetOpen)
			)
			(2
				(soundFx number: 801 loop: 1 flags: mNOPAUSE play:)
				(door init: setLoop: 0 setCel: 0 setCycle: EndLoop self)
				(SolvePuzzle fOpenedClosetFirstTime 5)
				(= cycles 1)
			)
			(3
				(if (not (Btst fDollInflated))
					(flatDoll init: approachVerbs: verbDo verbUse verbZipper verbTaste)
				)
				(= cycles 1)
			)
			(4 (NormalEgo 0) (= cycles 1))
			(5 (self dispose:))
		)
	)
)

(instance sLeakyDoll of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not (Btst fScrewDoll)) (Print 370 1))
				(ego z: 0 show:)
				(NormalEgo 1)
				(= cycles 1)
			)
			(1
				(sfxPop play:)
				(door setLoop: 1 setCel: 0)
				(= cycles 1)
			)
			(2
				(soundFx number: 372 loop: -1 flags: mNOPAUSE play:)
				(aDoll
					cycleSpeed: (+ 1 howFast)
					moveSpeed: (+ 1 howFast)
					init:
					setCycle: Forward
					setMotion: MoveTo 33 85
				)
				(ego egoSpeed: setMotion: PolyPath 56 119 self)
			)
			(3
				(Bset fChasingDoll)
				(Bset fDollInflated)
				(curRoom newRoom: rmLivingRoom)
			)
		)
	)
)

(instance sInflate of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego view: 808 setLoop: 0 setCel: 0 setCycle: EndLoop self)
			)
			(1
				(door setLoop: 1 setCel: 0 stopUpd:)
				(ego view: 808 setLoop: 0 setCycle: BegLoop self)
			)
			(2
				(Print 370 2)
				(sfxInflate flags: mNOPAUSE play:)
				(NormalEgo)
				(= seconds 3)
			)
			(3
				(Print 370 3)
				(SolvePuzzle fInflateDollPoints 5)
				(curRoom newRoom: rmDollCloseup)
			)
		)
	)
)

(instance sKenSpeaks of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 5))
			(1
				(Print 370 4)
				(Print 370 5)
				(Print 370 6)
				(theMusic fade: 0 12 20 1 self)
				(= seconds 8)
			)
			(2
				(= seconds 0)
				(if (not (Btst fClosetOpen))
					(door setCycle: EndLoop self)
					(soundFx number: 801 loop: 1 flags: mNOPAUSE play:)
				else
					(self cue:)
				)
			)
			(3
				(globalSound number: 700 vol: 127 loop: -1 flags: mNOPAUSE play:)
				(aKen
					init:
					;EO: code modifications taken from speed patch
					;cycleSpeed: (+ 4 howFast)
					;moveSpeed: (+ 4 howFast)
					cycleSpeed: (+ 2 howFast)
					moveSpeed: (+ 2 howFast)
					setLoop: 1
					setCel: 0
					setCycle: Walk
					setMotion: MoveTo 178 137 self
				)
			)
			(4
				(Print 370 7 #at -1 20)
				(aKen setLoop: 2 setCycle: EndLoop self)
			)
			(5
				(kenHead
					cycleSpeed: (* 2 howFast)
					init:
					setLoop: 3
					setCycle: ForwardCounter 2 self
				)
			)
			(6
				(Print 370 8 #at -1 20)
				(kenHead setCycle: ForwardCounter 4 self)
			)
			(7
				(Print 370 9 #at -1 19 #width 280)
				(kenHead setCycle: ForwardCounter 4 self)
			)
			(8
				(Print 370 10 #at -1 19 #width 280)
				(kenHead setCycle: ForwardCounter 4 self)
			)
			(9
				(Print 370 11 #at -1 19 #width 280)
				(kenHead setCycle: ForwardCounter 4 self)
			)
			(10
				(Print 370 12 #at -1 19 #width 280)
				(kenHead setCycle: ForwardCounter 4 self)
			)
			(11
				(Print 370 13 #at -1 20)
				(kenHead setCycle: ForwardCounter 4 self)
			)
			(12
				(Print 370 14 #at -1 20)
				(kenHead setCycle: ForwardCounter 2 self)
			)
			(13
				(Print 370 15 #at -1 19 #width 280)
				(kenHead setCycle: ForwardCounter 2 self)
			)
			(14
				(Print 370 16 #at -1 20)
				(aKen setLoop: 2 setCycle: CycleTo 2 -1 self)
			)
			(15
				(aKen
					setLoop: 1
					setCel: 3
					setCycle: Walk
					setMotion: PolyPath 52 121 self
				)
			)
			(16
				(Print 370 17 #at -1 20)
				(aKen
					setLoop: 0
					setCycle: Walk
					setMotion: PolyPath 80 128 self
				)
			)
			(17
				(aKen setLoop: 2 setCel: 0)
				(= seconds 3)
			)
			(18
				(aKen setLoop: 2 setCel: 0 setCycle: EndLoop self)
			)
			(19
				(kenHead setLoop: 3 setCycle: ForwardCounter 2 self)
			)
			(20
				(Print 370 18 #at -1 20)
				(kenHead setLoop: 3 setCycle: ForwardCounter 4 self)
			)
			(21
				(Print 370 19 #at -1 20)
				(Print 370 20 #at -1 20)
				(kenHead dispose:)
				(aKen setLoop: 2 setCycle: CycleTo 2 -1 self)
			)
			(22
				(aKen
					setLoop: 1
					setCel: 3
					setCycle: Walk
					setMotion: PolyPath 52 121 self
				)
			)
			(23
				(aKen dispose:)
				(Print 370 21 #at -1 20)
				(Bset fEndGame)
				(curRoom newRoom: rmEndCredits)
			)
		)
	)
)

(instance sfxInflate of Sound
	(properties
		flags mNOPAUSE
		number 370
	)
)

(instance sfxPop of Sound
	(properties
		flags mNOPAUSE
		number 371
	)
)

(instance aKen of Actor
	(properties
		x 338
		y 152
		view 722
		signal ignrAct
	)
)

(instance aEve of Prop
	(properties
		x 198
		y 108
		view 372
		priority 10
		signal fixPriOn
	)
)

(instance aDoll of Actor
	(properties
		x 271
		y 98
		yStep 5
		view 373
		xStep 7
	)
)

(instance kenHead of Prop
	(properties
		x 139
		y 143
		view 722
		loop 4
		priority 14
		signal (| ignrAct fixPriOn)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			((and (== (aKen loop?) 2) (== (aKen cel?) 4)) (kenHead x: (- (aKen x?) 2) y: (aKen y?) z: 27 species:))
			((== (kenHead z?) 27) (kenHead z: 1000 species:))
		)
	)
)

(instance door of Prop
	(properties
		x 311
		y 150
		description {the closet}
		sightAngle 40
		view 370
		priority 4
		signal $4010
	)
	
	(method (doVerb theVerb theItem)
		(fDoor doVerb: theVerb theItem)
	)
)

(instance flatDoll of Feature
	(properties
		x 306
		y 155
		z 5
		nsTop 92
		nsLeft 299
		nsBottom 132
		nsRight 313
		description {the inflatable doll}
		sightAngle 40
		approachX 287
		approachY 140
		lookStr {It appears to be an inflatable doll.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(Print 370 22)
			)
			(verbTalk
				(Print 370 23)
			)
			(verbZipper
				(Print 370 24)
			)
			(verbTaste
				(curRoom setScript: sInflate)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance fDoor of Feature
	(properties
		x 304
		y 144
		z 15
		nsTop 86
		nsLeft 290
		nsBottom 143
		nsRight 319
		description {the closet}
		sightAngle 40
		approachX 287
		approachY 140
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(if (door loop?)
					(Print 370 25)
				else
					(curRoom setScript: sClosetDoor)
				)
			)
			(verbLook
				(cond 
					((and (door cel?) (not (Btst fDollInflated))) (Print 370 26))
					((and (Btst fDollInflated) (door cel?)) (Print 370 25))
					((and (Btst fDollInflated) (Btst fClosetOpen)) (Print 370 27))
					(else (Print 370 28))
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance painting of Feature
	(properties
		x 28
		y 149
		z 56
		nsTop 68
		nsLeft 3
		nsBottom 118
		nsRight 54
		description {the painting}
		sightAngle 40
		lookStr {Oh, look! A portrait of Jesse Helms!}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance painting2 of Feature
	(properties
		x 163
		y 116
		z 55
		nsTop 46
		nsLeft 152
		nsBottom 77
		nsRight 175
		description {the painting}
		sightAngle 40
		lookStr {Oh, that's disgusting. Be glad you don't have a higher-resolution screen!}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance lightSwitch of Feature
	(properties
		x 277
		y 129
		z 29
		nsTop 96
		nsLeft 274
		nsBottom 105
		nsRight 281
		description {the electrical switch}
		sightAngle 40
		approachX 264
		approachY 127
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(Print 370 29)
				(Print 370 30 #at -1 140)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance bed of Feature
	(properties
		x 179
		y 115
		nsTop 99
		nsLeft 136
		nsBottom 131
		nsRight 223
		description {the bed}
		sightAngle 40
		lookStr {Play your cards right and you might get to try this baby out tonight!}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(Print 370 31)
			)
			(verbZipper
				(Print 370 32)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance pillows of Feature
	(properties
		x 182
		y 91
		nsTop 84
		nsLeft 146
		nsBottom 98
		nsRight 218
		description {the pillows}
		sightAngle 40
		lookStr {They look soft and fluffy -- just like their owner!}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(Print 370 33)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance flowerBox of Feature
	(properties
		x 159
		y 169
		nsTop 150
		nsBottom 189
		nsRight 319
		description {the flower box}
		sightAngle 40
		lookStr {What an unusual plant! But how can it grow near all those lights?}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(Print 370 34)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance tree of Feature
	(properties
		x 119
		y 61
		nsTop -1
		nsLeft 86
		nsBottom 123
		nsRight 153
		description {the ficus tree}
		sightAngle 40
		lookStr {This tree is the only normal-looking plant around. That's because it's artificial!}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(Print 370 35)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance aTowel of View
	(properties
		x 153
		y 134
		view 371
		loop 3
		priority 9
		signal (| ignrAct fixPriOn)
	)
)
