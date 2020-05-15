;;; Sierra Script 1.0 - (do not remove this comment)
(script# rmInsideBar) ;110
(include game.sh)
(use Main)
(use Intrface)
(use LLRoom)
(use PrintD)
(use PolyPath)
(use Polygon)
(use Block)
(use Feature)
(use LoadMany)
(use Wander)
(use Sound)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm110 0
)

(local
	tipCount
	drinkOrdered
	drinkCount
	beenToldPrice
	jokeSeconds
	jokesHeard
	jukeboxMusic
	local7
	local8
	leftyAttention
	theX
	theY
)
(procedure (HearJoke)
	(switch (++ jokesHeard)
		(1
			(Print 110 99 #at 5 5 #mode teJustLeft #dispose)
		)
		(2
			(Print 110 100 #at 5 5 #mode teJustLeft #dispose)
		)
		(3
			(Print 110 101 #at 5 5 #mode teJustLeft #dispose)
		)
		(4
			(Print 110 102 #at 5 5 #mode teJustLeft #dispose)
		)
		(5
			(Print 110 103 #at 5 5 #mode teJustLeft #dispose)
		)
		(6
			(Print 110 104 #at 5 5 #mode teJustLeft #dispose)
		)
		(7
			(Print 110 105 #at 5 5 #mode teJustLeft #dispose)
		)
		(8
			(Print 110 106 #at 5 5 #mode teJustLeft #dispose)
		)
		(else 
			(Print 110 107 #at 5 5 #mode teJustLeft #dispose)
			(= jokesHeard 0)
		)
	)
)

; Bar Drinks
(enum
	drinkWHISKEY
	drinkWINE
	drinkBEER
	drinkCHAMPAGNE
	drinkLIGHTBEER
	drinkROUND
)

(instance rm110 of LLRoom
	(properties
		picture rmInsideBar
		north rmHallway
		south rmOutsideBar
	)
	
	(method (init)
		(LoadMany SOUND 110 113 114 115 116 117 118 119)
		(LoadMany VIEW 110 112 113 801)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 189 0 0 319 0 319 189 310 189 310 149 287 142
						270 154 116 154 89 130 99 106 129 85 128 77 82 107
						72 107 51 126 27 130 12 151 12 189
					yourself:
				)
		)
		(switch prevRoomNum
			(rmHallway
				(self setScript: sFromStoreroom)
				(if (!= (theMusic number?) 110) (theMusic setLoop: 1))
				(theMusic fade: 127 15 5 0)
			)
			(rmOutsideBar
				(theMusic number: 110 loop: -1 vol: 127 flags: 1 play:)
				(ego setPri: -1)
			)
			(rmBackroom
				(HandsOff)
				(ego normal: 0 z: 1000 hide:)
				(curRoom setScript: sFromPimp)
			)
			(else  (ego posn: 160 170))
		)
		(ego init: actions: egoActions)
		(theEgoHead actions: egoActions)
		(super init:)
		(jukebox
			cycleSpeed: howFast
			init:
			approachVerbs: verbDo verbUse verbZipper verbTaste
		)
		(if (!= (theMusic number?) 110)
			(jukebox setCycle: Forward)
		else
			(jukebox stopUpd:)
		)
		(blondGuy init: stopUpd:)
		(blondHand init: stopUpd:)
		(blondHead
			cycleSpeed: (+ howFast 2)
			init:
			setScript: sGuyScript
		)
		(babe
			cycleSpeed: (+ howFast 1)
			init:
			approachVerbs: verbDo verbUse verbZipper verbTaste verbTalk
			setScript: sBabeScript
		)
		(babeTop
			cycleSpeed: (+ howFast 1)
			init:
			approachVerbs: verbDo verbUse verbZipper verbTaste verbTalk
		)
		(ken cycleSpeed: (+ howFast 1) init: stopUpd:)
		(kenHead
			cycleSpeed: (+ howFast 1)
			init:
			setScript: sKenTalksGirl
		)
		(dude init: stopUpd:)
		(skinnyMan
			init:
			cycleSpeed: howFast
			setScript: sDudeScript
		)
		(fatso
			cycleSpeed: (+ howFast 1)
			init:
			setScript: sFatsoScript
		)
		(lefty cycleSpeed: howFast init: stopUpd:)
		(fan cycleSpeed: howFast setCycle: Forward init:)
		(mooseEyes
			cycleSpeed: howFast
			init:
			setScript: (sDudeScript new:)
		)
		(door
			cycleSpeed: (+ howFast 1)
			init:
			stopUpd:
			approachVerbs: verbDo verbUse verbZipper verbTaste
		)
		(if (!= prevRoomNum 140) (door stopUpd:))
		(peephole
			cycleSpeed: howFast
			stopUpd:
			init:
			approachVerbs: verbDo verbUse verbZipper verbTaste
		)
		(stool init: approachVerbs: verbDo verbUse verbZipper verbTaste)
		(moose init:)
		(painting init:)
	)
	
	(method (doit)
		(super doit: &rest)
		(if
			(and
				(!= (theMusic number?) 110)
				(== (theMusic prevSignal?) -1)
				(not (jukebox script?))
			)
			(sPlaysong start: 5)
			(jukebox setScript: sPlaysong)
		)
		(if
			(and
				(not jokeSeconds)
				(== (ego view?) 801)
				(!= (kenHead script?) sTellJoke)
			)
			(kenHead setScript: sTellJoke)
		else
			(-- jokeSeconds)
		)
		(cond 
			(script)
			((< (ego y?) 104) (HandsOff) (curRoom setScript: sToStoreroom))
			((and (ego mover?) (== (ego view?) 801))
				(if (== (lefty script?) sLeftyServes)
					(ego setMotion: 0 x: 208 y: 150)
					(Print 110 0)
				else
					(= theX ((User curEvent?) x?))
					(= theY ((User curEvent?) y?))
					(HandsOff)
					(self setScript: sGetUp)
				)
			)
		)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(Print 110 1)
				(Print 110 2 #at -1 140)
			)
			(verbTalk (Print 110 3))
			(verbTaste (Print 110 4))
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
	
	(method (newRoom n)
		(switch n
			(rmBackroom (theMusic fade: 90 15 5 0))
			(rmOutsideBar (theMusic fade:))
			(rmHallway
				(theMusic setLoop: -1 fade: 90 15 5 0)
			)
			(rmAlley (theMusic stop:))
		)
		(if modelessDialog (modelessDialog dispose:))
		(super newRoom: n)
	)
)

(instance sFromPimp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(peephole z: 1000)
				(= cycles 1)
			)
			(1
				(if (!= (theMusic number?) 110)
					(theMusic number: 110 loop: -1 vol: 127 flags: 1 play:)
				else
					(theMusic fade: 127 10 5 0)
				)
				(door setCycle: EndLoop self)
			)
			(2
				(ego
					egoSpeed:
					show:
					z: 0
					normal: 1
					illegalBits: cWHITE
					x: 319
					y: 145
					setMotion: MoveTo 290 151 self
				)
			)
			(3 (door setCycle: BegLoop self))
			(4
				(peephole z: 32)
				(NormalEgo 1)
				(door stopUpd:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sGetUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego egoSpeed: setCycle: EndLoop self)
			)
			(1
				(NormalEgo 2)
				(ego setMotion: MoveTo (ego x?) 154 self)
			)
			(2
				(if modelessDialog (modelessDialog dispose:))
				(kenHead z: 23 setLoop: 5 setScript: sKenTalksGirl)
				(ken setCel: 0)
				(if (not drinkCount)
					(HandsOn)
					(if
						(and
							(!= (CueObj client?) stool)
							((CueObj client?) approachX?)
							(!= (theIconBar curIcon?) (ScriptID 0 25))
						)
						(ego
							setMotion:
								PolyPath
								((CueObj client?) approachX?)
								(+ (ego z?) ((CueObj client?) approachY?))
								CueObj
						)
					else
						(ego setMotion: PolyPath theX theY)
					)
					(self dispose:)
				else
					(Print 110 5)
					(if (> drinkCount 8) (= drinkCount 8))
					(curRoom setScript: sWalkDrunk)
				)
			)
		)
	)
)

(instance sWalkDrunk of Script
	(properties)
	
	(method (doit &tmp i)
		(super doit: &rest)
		(= local7 (* (SinMult local8 1000) drinkCount))
		(= local8 (mod (= local8 (+ local8 5)) 360))
		(= i 1)
		(while (<= i 15)
			(theMusic send: i 255 local7)
			(++ i)
		)
		(if (and (not register) (< state 2))
			(= register (Random 5 25))
			(ego setLoop: (Random 0 3))
		else
			(-- register)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					egoSpeed:
					observeBlocks: drunkCircle
					setMotion: Wander
				)
				(= seconds 6)
			)
			(1
				(if (== drinkCount 5) (Print 110 6))
				(if (-- drinkCount)
					(self init:)
				else
					(ego setMotion: 0 setLoop: -1 ignoreBlocks: drunkCircle)
					(Print 110 7)
					(= cycles 1)
				)
			)
			(2
				(NormalEgo)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sKenTalksGirl of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ken setLoop: 7 setCel: 0)
				(kenHead setLoop: 5 setCel: 0)
				(= cycles (Random 24 32))
			)
			(1
				(kenHead setCycle: Forward)
				(= cycles (Random 24 32))
			)
			(2
				(kenHead setCel: 0 setCycle: 0 stopUpd:)
				(self init:)
			)
		)
	)
)

(instance sTellJoke of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles (Random 50 100)))
			(1
				(if (== (kenHead loop?) 5)
					(ken setCel: 1)
					(kenHead z: 1000)
				else
					(++ state)
				)
				(= cycles 1)
			)
			(2
				(ken setCel: 2)
				(kenHead z: 23 setLoop: 6)
				(= cycles 1)
			)
			(3
				(kenHead setLoop: 6 setCycle: Forward)
				(= cycles (Random 43 90))
			)
			(4
				(Print 110 8 #at 5 5 #mode teJustLeft #dispose)
				(= seconds 5)
			)
			(5
				(if modelessDialog (modelessDialog dispose:))
				(HearJoke)
				(kenHead setLoop: 8 setCel: 0 setCycle: EndLoop)
				(ken setCel: 3)
				(= seconds 7)
			)
			(6
				(if modelessDialog (modelessDialog dispose:))
				(Print 110 9 #at 5 5 #mode teJustLeft #dispose)
				(ken setLoop: 9 setCycle: Forward)
				(kenHead z: 1000)
				(= cycles 20)
			)
			(7
				(if modelessDialog (modelessDialog dispose:))
				(ken setLoop: 7 setCel: 2)
				(kenHead z: 23 setLoop: 6 setCel: 0 setCycle: 0)
				(= cycles 20)
			)
			(8
				(Print 110 10 #at -1 140)
				(= jokeSeconds (Random 150 250))
				(self dispose:)
			)
		)
	)
)

(instance sSulk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(kenHead z: 1000)
				(ken setLoop: 7 setCel: 1)
				(= jokeSeconds (Random 400 800))
				(= cycles (Random 123 234))
			)
			(1
				(ken setCel: 2)
				(kenHead z: 23 setScript: sKenTalksGirl)
			)
		)
	)
)

(instance sSitDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 208 150 self)
			)
			(1
				(ego
					egoSpeed:
					view: 801
					setLoop: 0
					cel: 0
					setCycle: EndLoop self
				)
			)
			(2
				(ego setLoop: 1 setCel: 0)
				(= cycles 1)
			)
			(3
				(HandsOn)
				(ego userSpeed:)
				(self dispose:)
			)
		)
	)
)

(instance sToStoreroom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 126 82 self)
			)
			(1
				(curRoom newRoom: rmHallway)
				(self dispose:)
			)
		)
	)
)

(instance sFromStoreroom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego posn: 94 100 setMotion: PolyPath 91 110 self)
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)

(instance sDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print 110 11)
				(= seconds 4)
			)
			(1
				(soundFx number: 119 loop: 1 vol: 127 flags: mNOPAUSE play:)
				(peephole setCycle: EndLoop self)
			)
			(2
				(Print 110 12 #at 180 55)
				(= seconds 15)
			)
			(3
				(Print 110 13 #at 180 55)
				(peephole setCycle: BegLoop)
				(soundFx number: 119 loop: 1 vol: 127 flags: mNOPAUSE play:)
				(self dispose:)
			)
			(4
				(Print 110 14 #at 220 55 #dispose)
				(soundFx number: 119 loop: 1 vol: 127 flags: mNOPAUSE play:)
				(peephole setCycle: BegLoop self)
			)
			(5
				(peephole z: 1000 dispose:)
				(door setCycle: EndLoop self)
			)
			(6
				(if modelessDialog (modelessDialog dispose:))
				(curRoom setScript: sGoPimp)
				(self dispose:)
			)
		)
	)
)

(instance sGoPimp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 340 141 self)
			)
			(1 (door setCycle: BegLoop self))
			(2 (curRoom newRoom: rmBackroom))
		)
	)
)

(instance sLeftyServes of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(soundFx number: 113 setLoop: 1 flags: 1 play:)
				(if (== (lefty loop?) 7) (lefty setCel: 1))
				(= cycles 1)
			)
			(1
				(lefty setLoop: 0 setCycle: EndLoop self)
			)
			(2
				(lefty
					setLoop: 1
					setCel: 0
					x: (+ (lefty x?) 30)
					setCycle: CycleTo 7 1 self
				)
			)
			(3
				(soundFx number: 114 play:)
				(lefty setCycle: CycleTo 11 1 self)
			)
			(4
				(soundFx number: 115 play:)
				(lefty setCycle: EndLoop self)
			)
			(5
				(soundFx number: 113 play:)
				(lefty
					setLoop: 2
					setCel: 0
					x: (- (lefty x?) 16)
					setCycle: CycleTo 2 1 self
				)
			)
			(6
				(lefty x: (- (lefty x?) 14) setCycle: CycleTo 5 1 self)
			)
			(7
				(soundFx number: 114 play:)
				(lefty setCycle: CycleTo 11 1 self)
			)
			(8
				(soundFx number: 115 play:)
				(lefty setCycle: EndLoop self)
			)
			(9
				(lefty setLoop: 3 setCel: 0 setCycle: EndLoop self)
			)
			(10
				(lefty setLoop: 4 setCycle: Forward)
				(= seconds (Random 2 3))
			)
			(11
				(soundFx number: 113 play:)
				(lefty setLoop: 7 setCel: 0 setCycle: EndLoop self)
			)
			(12
				(lefty setLoop: 6 setCel: 255 setCycle: BegLoop self)
			)
			(13
				(Print 110 15 #at 73 42)
				(lefty setLoop: 7 setCel: 255)
				(if (< dollars 5)
					(Print 110 16)
					(Print 110 17 #at 73 42)
					(HandsOff)
					(curRoom setScript: sThrowLarry)
				else
					(Print 110 18)
					(= dollars (- dollars 5))
					(switch drinkOrdered
						(drinkWHISKEY
							(if (ego has: iWhiskey)
								(++ drinkCount)
								(Print 110 19)
							else
								(SolvePuzzle fGetWhiskey 1)
								(ego get: iWhiskey)
								(= drinkOrdered 999)
								(Print 110 20)
							)
						)
						(drinkWINE
							(++ drinkCount)
							(Print 110 21)
							(Print 110 22)
							(Print 110 23)
						)
						(drinkBEER (++ drinkCount) (Print 110 24))
					)
					(if (!= drinkOrdered 999)
						(switch drinkCount
							(1 (Print 110 25))
							(else  (Print 110 26))
						)
					)
					(= drinkOrdered NULL)
					(= cycles 1)
				)
			)
			(14 (self dispose:))
		)
	)
)

(instance sBabeScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(babe setCel: 0 stopUpd:)
				(babeTop setCel: 0 stopUpd:)
				(= cycles (Random 30 60))
			)
			(1
				(if (Random 0 3)
					(babe setCycle: Forward)
					(= cycles (Random 40 80))
				else
					(babeTop setCel: 0 setCycle: EndLoop self)
				)
			)
			(2 (self init:))
		)
	)
)

(instance sGuyScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(blondHead setLoop: 0 setCel: 0)
				(blondHand setPri: 10)
				(= cycles 2)
			)
			(1
				(blondHead stopUpd:)
				(= cycles (Random 75 200))
			)
			(2
				(if (Random 0 1)
					(blondHead setCycle: Forward)
					(= cycles (Random 15 25))
				else
					(blondHead setLoop: 1 setCel: 0 setCycle: EndLoop self)
					(blondHand z: 1000)
				)
			)
			(3
				(if (blondHead cycler?)
					(blondHead setCel: 0 setCycle: 0)
					(self init:)
				else
					(blondHead setLoop: 2 setCycle: Forward)
					(= seconds (Random 3 5))
				)
			)
			(4
				(blondHead setLoop: 1 setCel: 255 setCycle: BegLoop self)
			)
			(5
				(blondHand z: 17 setPri: 11)
				(self init:)
			)
		)
	)
)

(instance sFatsoScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(fatso setLoop: 3 setCel: 0)
				(= cycles 2)
			)
			(1
				(fatso stopUpd:)
				(= cycles (Random 75 200))
			)
			(2
				(cond 
					((<= (theGame detailLevel:) 2) (self init:))
					((not (Random 0 2)) (fatso setLoop: 3 setCycle: EndLoop self))
					(else
						(fatso setLoop: 4 setCycle: CycleTo 4 1)
						(= cycles (Random 5 10))
					)
				)
			)
			(3
				(if (== (fatso loop?) 4)
					(fatso setCycle: EndLoop self)
				else
					(self init:)
				)
			)
			(4 (self init:))
		)
	)
)

(instance sDudeScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client stopUpd:)
				(= cycles (Random 50 150))
			)
			(1
				(if
				(<= (theGame detailLevel:) (client detailLevel:))
					(self init:)
				else
					(client setCycle: EndLoop)
					(= cycles (Random 6 20))
				)
			)
			(2 (client setCycle: BegLoop self))
			(3 (self init:))
		)
	)
)

(instance sThrowLarry of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if modelessDialog (modelessDialog dispose:))
				(cast eachElementDo: #z 1000)
				(kenHead setScript: 0)
				(curRoom style: IRISIN drawPic: 720)
				(= seconds 3)
				(curRoom newRoom: rmAlley)
			)
		)
	)
)

(instance sPlaysong of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= register jukeboxMusic)
				(theGame setCursor: 999 1)
				(Animate (cast elements?) 0)
				(= jukeboxMusic
					(PrintD
						{Lefty's proudly presents for your listening and drinking pleasure:}
						#new
						#button {Taxicab from Hell} 206
						#new
						#button {Air For A "G" String} 410
						#new
						#button {I Can Can You} 320
						#new
						#button {Mantovani's Revenge} 310
						#new
						#button {Theme from "Bo-Larry"} 351
						#new
						#button {"Wedding March"\nby Bloughengrin} 411
						#new
						#button {Prelude to an\nAfternoon of Fawn} 610
						#new
						#button {Her Albert Has\nSome Iguana Gas} 510
						#new
						#button {That's a Sweet Moon, Honey} 390
					)
				)
				(theGame setCursor: ((theIconBar curIcon?) cursor?) TRUE)
				(theMusic fade: 0 5 5 1 self)
			)
			(1
				(if register
					(UnLoad SOUND register)
				else
					(UnLoad SOUND 110)
				)
				(globalSound
					number: 116
					loop: 1
					vol: 127
					flags: mNOPAUSE
					play: self
				)
			)
			(2
				(sScratchy
					number: 117
					loop: -1
					vol: 127
					flags: mNOPAUSE
					priority: 10
					play:
				)
				(= seconds 3)
			)
			(3
				(client setCycle: Forward)
				(theMusic vol: 127 loop: 1 number: jukeboxMusic play: self)
				(HandsOn)
				(= seconds 3)
			)
			(4 (sScratchy fade:))
			(5 (= seconds 3))
			(6
				(sScratchy play:)
				(globalSound number: 118 play: self)
			)
			(7
				(theMusic number: 110 vol: 0 loop: -1 play:)
				(= cycles 1)
			)
			(8
				(client setCycle: BegLoop)
				(sScratchy fade:)
				(theMusic fade: 127 10 5 0)
				(= seconds 3)
			)
			(9
				(UnLoad SOUND jukeboxMusic)
				(= jukeboxMusic 0)
				(self start: 0 dispose:)
			)
		)
	)
)

(instance egoActions of Code ;EO: Was a class, but it is not in the class table
	(properties)
	
	(method (doVerb theVerb theItem)
		(return
			(switch theVerb
				(verbUse
					(switch theItem
						(iWhiskey
							(if (== (ego view?) 801)
								(++ drinkCount)
								(ego put: iWhiskey)
								(Print 110 27)
							else
								(Print 110 28)
							)
						)
						(iBreathSpray
							(if (== (ego view?) 801)
								(if modelessDialog (modelessDialog dispose:))
								(Print 110 29 #at -1 20 #time 2)
								(++ sprayCount)
								(= spraySeconds 600)
								(Bclr fMouthSmellsBad)
								(if (Btst fPersonComplained) (Bclr fPersonComplained) (Print 110 30))
								(if (> sprayCount 9)
									(Print 110 31)
									(ego put: iBreathSpray 510)
									(= sprayCount 0)
								)
								(return TRUE)
							)
						)
					)
				)
			)
		)
	)
)

(instance drunkCircle of Cage
	(properties
		top 150
		left 25
		bottom 185
		right 310
	)
)

(instance jukebox of Prop
	(properties
		x 36
		y 122
		description {the jukebox}
		sightAngle 40
		approachX 58
		approachY 120
		lookStr {Gee, Dad. It's a Wurlitzer!}
		view 110
		priority 4
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(if (== (ego view?) 801)
			(if (== theVerb verbLook)
				(super doVerb: theVerb)
			else
				(Print 110 32)
			)
		else
			(switch theVerb
				(verbDo (Print 110 33))
				(verbZipper (Print 110 34))
				(verbTaste (Print 110 4))
				(verbUse
					(switch theItem
						(iWallet
							(HandsOff)
							(Print 110 35)
							(= dollars (- dollars 1))
							(jukebox setScript: sPlaysong)
						)
						(else 
							(super doVerb: theVerb theItem)
						)
					)
				)
				(else 
					(super doVerb: theVerb theItem)
				)
			)
		)
	)
)

(instance blondHead of Prop
	(properties
		x 125
		y 115
		z 17
		description {Ivan's head}
		sightAngle 40
		view 113
		priority 10
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(blondGuy doVerb: theVerb theItem)
	)
)

(instance blondHand of View
	(properties
		x 126
		y 121
		z 17
		description {Ivan's hand}
		sightAngle 40
		view 113
		loop 13
		cel 1
		priority 10
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(blondGuy doVerb: theVerb theItem)
	)
)

(instance blondGuy of Person
	(properties
		x 106
		y 115
		description {Ivan}
		sightAngle 40
		lookStr {Obviously, heavy drinking is not just an obsession with this guy!}
		view 113
		loop 13
		priority 10
		signal (| ignrAct fixPriOn stopUpdOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (Print 110 36))
			(verbTalk
				(Print 110 37)
				(Print 110 38)
			)
			(verbZipper (Print 110 39))
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance babeTop of Prop
	(properties
		x 146
		y 125
		z 21
		description {the babe}
		sightAngle 40
		approachX 141
		approachY 154
		view 113
		loop 12
		priority 10
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(babe doVerb: theVerb theItem)
	)
)

(instance babe of Person
	(properties
		x 146
		y 125
		description {the babe}
		sightAngle 40
		approachX 141
		approachY 154
		lookStr {She may not be a great looker, but think of the muscles she must have in that leg!}
		view 113
		loop 11
		priority 10
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (Print 110 40 #at -1 20))
			(verbTaste (self doVerb: verbDo))
			(verbTalk
				(Print 110 41)
				(Print 110 42 #at -1 20)
				(Print 110 43 #at -1 140)
			)
			(verbZipper
				(Print 110 44)
				(Print 110 45)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance kenHead of Prop
	(properties
		x 178
		y 128
		z 23
		description {Ken's head}
		sightAngle 40
		view 113
		loop 5
		priority 11
		signal $4810
	)
	
	(method (doVerb theVerb theItem)
		(ken doVerb: theVerb theItem)
	)
)

(instance ken of Person
	(properties
		x 178
		y 128
		description {Ken}
		sightAngle 40
		lookStr {A large man sits beside the only woman in Lefty's. He's obviously thrilled with the sound of his own voice.}
		view 113
		loop 7
		priority 10
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (Print 110 46))
			(verbTalk
				(cond 
					((not (== (ego view?) 801)) (Print 110 47))
					((== (ken script?) sKenTalksGirl) (Print 110 48))
					(else
						(if modelessDialog (modelessDialog dispose:))
						(Print 110 49)
						(kenHead setScript: sSulk)
					)
				)
			)
			(verbZipper (Print 110 39))
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance dude of View
	(properties
		x 236
		y 130
		description {James}
		sightAngle 40
		view 113
		loop 13
		cel 2
		priority 10
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(skinnyMan doVerb: theVerb theItem)
	)
)

(instance skinnyMan of Person
	(properties
		x 237
		y 130
		z 22
		description {James}
		sightAngle 40
		lookStr {A skinny man sits at the bar, talking to his overweight friend.}
		view 113
		loop 10
		cel 2
		priority 10
		signal (| ignrAct fixPriOn)
		detailLevel 2
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (Print 110 46))
			(verbZipper
				(Print 110 50)
				(Print 110 51 #at -1 140)
			)
			(verbTalk
				(Print 110 52)
				(Print 110 53)
				(Print 110 54)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance fatso of Person
	(properties
		x 263
		y 132
		description {Richard}
		sightAngle 40
		lookStr {An overweight man sits at the bar, talking to his skinny friend.}
		view 113
		loop 3
		cel 5
		priority 10
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (Print 110 46))
			(verbTalk
				(Print 110 55)
				(Print 110 56)
				(Print 110 57)
			)
			(verbZipper
				(Print 110 50)
				(Print 110 58 #at -1 140)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance lefty of Person
	(properties
		x 201
		y 101
		description {Lefty}
		sightAngle 40
		view 112
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(if (not (== (ego view?) 801))
					(ego
						setHeading: (GetAngle (ego x?) (ego y?) (self x?) (self y?)) self
					)
				else
					(self cue:)
				)
			)
			(verbZipper (Print 110 61))
			(verbDo (self doVerb: verbTalk))
			(verbTalk
				(cond 
					((not (== (ego view?) 801)) (Print 110 62))
					((or drinkOrdered (== (lefty script?) sLeftyServes)) (Print 110 63))
					(else
						(if (not leftyAttention) (= leftyAttention TRUE) (Print 110 64))
						(theGame setCursor: ARROW_CURSOR TRUE)
						(Animate (cast elements?) FALSE)
						(= drinkOrdered
							(PrintD
								{"What'll it be?" Lefty responds.}
								#at 73 42
								#new
								#button {A Round} drinkROUND
								#button {Champagne} drinkCHAMPAGNE
								#button { Wine_} drinkWINE
								#new
								#button {Light Beer} drinkLIGHTBEER
								#button { Beer_} drinkBEER
								#button { Whiskey_} drinkWHISKEY
							)
						)
						(theGame setCursor: ((theIconBar curIcon?) cursor?) 1)
						(switch drinkOrdered
							(drinkWHISKEY
								(Print 110 65)
								(lefty setScript: sLeftyServes)
							)
							(drinkWINE
								(Print 110 66)
								(Print 110 67)
								(lefty setScript: sLeftyServes)
							)
							(drinkBEER
								(Print 110 68)
								(lefty setScript: sLeftyServes)
							)
							(drinkCHAMPAGNE
								(Print 110 69 #at 73 42)
								(= drinkOrdered NULL)
							)
							(drinkLIGHTBEER
								(Print 110 70 #at 73 42)
								(= drinkOrdered NULL)
							)
							(drinkROUND
								(= drinkOrdered 0)
								(cond 
									((not beenToldPrice) (= beenToldPrice TRUE) (Print 110 71 #at 73 42))
									((< dollars 90) (Print 110 72))
									(else
										(Print 110 73 #at 73 42)
										(Print 110 74)
										(= dollars (- dollars 90))
									)
								)
							)
						)
					)
				)
			)
			(verbUse
				(switch theItem
					(iWallet
						(cond 
							((not (== (ego view?) 801)) (Print 110 75))
							((< dollars 10) (Print 110 76))
							(else
								(= dollars (- dollars 10))
								(switch (++ tipCount)
									(1 (Print 110 77 #at 73 42))
									(2 (Print 110 78 #at 73 42))
									(3 (Print 110 79 #at 73 42))
									(4 (Print 110 80 #at 73 42))
									(else  (Print 110 81 #at 73 42))
								)
							)
						)
					)
					(5
						(if (not (== (ego view?) 801))
							(Print 110 75)
						else
							(Print 110 82 #at 73 42)
						)
					)
					(else 
						(super doVerb: theVerb theItem)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
	
	(method (cue)
		(super cue:)
		(Print 110 59)
		(Print 110 60 #at -1 140)
	)
)

(instance fan of Prop
	(properties
		x 170
		description {the fan}
		sightAngle 40
		view 110
		loop 3
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbZipper (Print 110 85))
			(verbLook
				(if (not (== (ego view?) 801))
					(ego
						setHeading: (GetAngle (ego x?) (ego y?) (self x?) (self y?)) self
					)
				else
					(self cue:)
				)
			)
			(verbDo (Print 110 86))
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
	
	(method (cue)
		(super cue:)
		(Print 110 83)
		(Print 110 84)
	)
)

(instance mooseEyes of Prop
	(properties
		x 269
		y 130
		z 86
		description {the moose's eyes}
		sightAngle 40
		view 110
		loop 2
		cel 3
		detailLevel 2
	)
	
	(method (doVerb theVerb theItem)
		(moose doVerb: theVerb theItem)
	)
)

(instance peephole of Prop
	(properties
		x 303
		y 140
		z 32
		description {the peephole}
		sightAngle 40
		approachX 290
		approachY 151
		view 110
		loop 1
		priority 10
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(if (not (== (ego view?) 801))
					(ego
						setHeading: (GetAngle (ego x?) (ego y?) (self x?) (self y?)) self
					)
				else
					(self cue:)
				)
			)
			(verbDo (Print 110 89))
			(else 
				(door doVerb: theVerb theItem)
			)
		)
	)
	
	(method (cue)
		(super cue:)
		(if cel (Print 110 87) else (Print 110 88))
	)
)

(instance door of Prop
	(properties
		x 306
		y 139
		description {the door}
		sightAngle 40
		approachX 290
		approachY 151
		lookStr {You wonder how many naugas had to give their all just to decorate this sleazehole.}
		view 110
		loop 4
		priority 9
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(cond 
					((== (ego view?) 801) (Print 110 32))
					((self script?) (Print 110 90))
					(else (self setScript: sDoor))
				)
			)
			(verbUse
				(switch theItem
					(iGraffiti
						(if (== (sDoor state?) 2)
							(HandsOff)
							(Print 110 91)
							(sDoor seconds: 3 state: 3 cue:)
						else
							(Print 110 92)
						)
					)
					(iHammer (Print 110 93))
					(else 
						(super doVerb: theVerb theItem)
					)
				)
			)
			(verbTalk
				(if (== (sDoor state?) 2)
					(Print 110 94)
					(Print 110 95 #at 180 55)
					(sDoor seconds: 0 cue:)
				else
					(Print 110 96)
				)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance stool of Feature
	(properties
		x 206
		y 150
		z 13
		nsTop 125
		nsLeft 200
		nsBottom 149
		nsRight 212
		description {the stool}
		sightAngle 40
		approachX 208
		approachY 150
		lookStr {There is a lovely, empty, pink stool just waiting for a lovely, empty, pink rump like yours.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(kenHead setScript: sTellJoke)
				(HandsOff)
				(curRoom setScript: sSitDown)
			)
			(verbTaste (Print 110 97))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance moose of Feature
	(properties
		x 272
		y 134
		z 86
		nsTop 24
		nsLeft 246
		nsBottom 73
		nsRight 298
		description {the moosehead}
		sightAngle 40
		lookStr {It's an antique, left over from "King's Quest III."}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbTalk
				(if (== (ego view?) 801)
					(Print 110 98)
					(= drinkCount 4)
				else
					(super doVerb: theVerb theItem)
				)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance painting of Feature
	(properties
		x 171
		y 110
		z 64
		nsTop 29
		nsLeft 129
		nsBottom 63
		nsRight 214
		description {the painting}
		sightAngle 40
		lookStr {You don't know much about art, but you do know what you like!}
	)
)

(instance sScratchy of Sound
	(properties)
)
