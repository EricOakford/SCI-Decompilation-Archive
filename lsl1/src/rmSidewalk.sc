;;; Sierra Script 1.0 - (do not remove this comment)
(script# rgSidewalk) ;700
(include game.sh)
(use Main)
(use Intrface)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	sidewalk 0
	dog 1
	taxi 2
	sTaxiScript 3
)

(local
	local0
	[taxiPolyPts 8]
	[dogPolyPts 8]
	taxiTimer
	taxiY
	carView
	dogPeed
)
(procedure (AddTaxiObstacle &tmp temp0 temp1 temp2 temp3)
	(= temp0 (- (taxi brLeft?) 8))
	(= temp1 (- (taxi brTop?) 8))
	(= temp2 (+ (taxi brRight?) 8))
	(= temp3 (+ (taxi brBottom?) 5))
	(= [taxiPolyPts 0] (= [taxiPolyPts 6] temp0))
	(= [taxiPolyPts 1] (= [taxiPolyPts 3] temp1))
	(= [taxiPolyPts 2] (= [taxiPolyPts 4] temp2))
	(= [taxiPolyPts 5] (= [taxiPolyPts 7] temp3))
	(taxiPoly points: @taxiPolyPts size: 4)
	(curRoom addObstacle: taxiPoly)
)

(procedure (AddDogObstacle &tmp dogBrLeft dogBrTop dogBrRight dogBrBottom)
	(= dogBrLeft (- (dog brLeft?) 8))
	(= dogBrTop (- (dog brTop?) 4))
	(= dogBrRight (+ (dog brRight?) 8))
	(= dogBrBottom (dog brBottom?))
	(= [dogPolyPts 0]
		(= [dogPolyPts 6] dogBrLeft)
	)
	(= [dogPolyPts 1]
		(= [dogPolyPts 3] dogBrTop)
	)
	(= [dogPolyPts 2]
		(= [dogPolyPts 4] dogBrRight)
	)
	(= [dogPolyPts 5] (= [dogPolyPts 7] dogBrBottom))
	(dogPoly points: @dogPolyPts size: 4)
	(curRoom addObstacle: dogPoly)
)

(instance sidewalk of Region
	(properties)
	
	(method (init)
		(= carView (Random 822 827))
		(LoadMany VIEW carView 806 810 811 820 821 200)
		(LoadMany SOUND
			800 810 811 812 103 102 200 202 203 204 205 206
		)
		(if
			(and
				(or
					(!= (globalSound number?) 800)
					(== (globalSound prevSignal?) -1)
				)
				(not (Btst fStiffedCabbie))
			)
			(globalSound number: 800 loop: -1 vol: 127 play:)
		)
		(super init:)
		(streetF init:)
		(= taxiY
			(= local0
				(switch curRoomNum
					(rmOutsideBar 169)
					(rmOutsideCasino 165)
					(rmOutsideChapel 159)
					(rmOutside7_11 170)
					(rmOutsideDisco 167)
				)
			)
		)
		(if
		(and (<= 710 prevRoomNum) (<= prevRoomNum 720))
			(theMusic fade: 0 30 1 1)
		)
		(= taxiTimer 1000)
		(if (== prevRoomNum 200)
			(HandsOff)
			(if (Btst fStiffedCabbie) (Load VIEW 171))
			(curRoom setScript: sDropoff)
		)
		(if (> (GameHour) 7)
			(self setScript: virginScript)
		)
		(if (!= curRoomNum 100)
			(taxiSignProp
				x:
				(switch curRoomNum
					(rmOutsideCasino 74)
					(rmOutsideChapel 294)
					(rmOutside7_11 200)
					(rmOutsideDisco 246)
				)
				y:
				(switch curRoomNum
					(rmOutsideCasino 178)
					(rmOutsideChapel 181)
					(rmOutside7_11 175)
					(rmOutsideDisco 172)
				)
				approachX: (ego x?)
				approachY: (- local0 10)
				approachVerbs: verbDo verbTalk
				init:
				setPri: 14
				stopUpd:
			)
		)
	)
	
	(method (doit)
		(super doit: &rest)
		(taxiSignProp approachX: (ego x?))
		(if (and taxiTimer (not (dog script?))) (-- taxiTimer))
		(if (ego mover?) (= taxiTimer 1000))
		(cond 
			((== (curRoom script?) sFlattenLarry) (ego setMotion: 0))
			((> (ego y?) local0)
				(cond 
					((not (cast contains: taxi)) (HandsOff) (curRoom setScript: sFlattenLarry))
					((> (ego y?) (+ (taxi y?) 5)) (HandsOff) (curRoom setScript: sFlattenLarry))
				)
			)
			(
				(and
					(not taxiTimer)
					(not dogPeed)
					(!= curRoomNum rmOutside7_11)
					(!= (curRoom curPic?) 245)
				)
				(= taxiTimer -1)
				(dog
					init:
					cycleSpeed: (ego cycleSpeed?)
					moveSpeed: (ego moveSpeed?)
					setCycle: Walk
					z: 0
					setScript: sFindLarry
				)
			)
		)
	)
	
	(method (newRoom n)
		(= keep (OneOf n 100 300 400 500 600))
		(= initialized 0)
		(if
		(and (not (OneOf n 100 300 400 500 600)) (!= n 160))
			(globalSound fade:)
		)
		(if (and (!= n 200) (== (soundFx number?) 205))
			(soundFx stop:)
		)
		(if (and (== n 160) (== (theMusic number?) 700))
			(theMusic fade:)
		)
		(if modelessDialog (modelessDialog dispose:))
		(super newRoom: n &rest)
	)
)

(instance virginScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 5)
			)
			(1
				(if (curRoom script?)
					(= cycles 60)
					(-- state)
				else
					(HandsOff)
					(ego setHeading: 180 self)
				)
			)
			(2
				(= cycles 3)
			)
			(3
				(LoadMany FONT giantFont)
				(Print rgSidewalk 0 #at 15 -1 #width 280)
				(Print rgSidewalk 1 #at 15 -1 #width 280)
				(Print rgSidewalk 2 #at 15 -1 #width 280)
				(Print rgSidewalk 3 #at 15 -1 #width 280 #font giantFont)
				(= seconds 3)
			)
			(4
				(ego
					egoSpeed:
					normal: 0
					view: 811
					setLoop: (if register 0 else 1)
					setCel: 8
					setCycle: EndLoop self
				)
			)
			(5
				(= seconds 3)
			)
			(6
				(ShowDeathIcon 807 1)
				(Format @str1 rgSidewalk 4)
				(EgoDead rgSidewalk 5)
			)
		)
	)
)

(instance sDropoff of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if (or (== state 1) (== state 5))
			(taxi x: (- 150 register))
			(soundFx
				send: 9 255 (+ (* (- 150 (taxi x?)) 10) 3000)
			)
			(if (< (taxi x?) -800) (= cycles 1))
			(= register (* register 2))
			(if (== register 4)
				(soundFX number: 202 setLoop: 1 flags: 1 play:)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(taxi
					init:
					setCel: 1
					cycleSpeed: howFast
					moveSpeed: howFast
					x: 150
					y: taxiY
				)
				(ego x: 150 y: (- local0 10) z: 0)
				(if (Btst fStiffedCabbie)
					(ego egoSpeed: view: 171 loop: 0 setCycle: Forward)
					(++ state)
					(= cycles 1)
				else
					(ego setHeading: 180 self)
				)
			)
			(1 (= register 1))
			(2
				(if (Btst fStiffedCabbie)
					(theMusic number: 171 loop: -1 vol: 127 flags: 1 play:)
					(ego setCycle: Forward)
					(= seconds 5)
				else
					(taxi dispose:)
					(soundFx fade:)
					(HandsOn)
					(self dispose:)
				)
			)
			(3
				(ego setLoop: 1)
				(= cycles 3)
			)
			(4
				(ego setLoop: 0 setCycle: Forward)
				(= seconds 4)
			)
			(5
				(theMusic stop:)
				(= register 1)
				(if modelessDialog (modelessDialog dispose:))
				(Print rgSidewalk 6)
				(ego
					view: 806
					loop: 1
					x: (- (ego x?) 0)
					y: (+ (ego y?) 5)
					setCycle: 0
				)
				(mCar2 play:)
			)
			(6
				(soundFx fade:)
				(ShowDeathIcon 171 3 1)
				(Format @str1 rgSidewalk 4)
				(EgoDead rgSidewalk 7)
			)
		)
	)
)

(instance sFindLarry of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if (ego mover?)
			(sPiss stop:)
			(dog setScript: sRunOff 0 register)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic number: 103 loop: 1 vol: 127 flags: 1 play:)
				(dog
					x: (if (= register (Random 0 1)) 330 else -10)
					y: 155
					setCycle: Walk
					setMotion:
						PolyPath
						(if register (+ (ego x?) 17) else (- (ego x?) 15))
						(+ (ego y?) 1)
						self
				)
			)
			(1
				(AddDogObstacle)
				(dog
					view: 821
					loop: (+ 0 register)
					cel: 0
					setCycle: EndLoop self
				)
			)
			(2
				(dog loop: (+ 2 register) cel: 0 setCycle: EndLoop self)
			)
			(3
				(sPiss play:)
				(dog loop: (+ 4 register) setCycle: Forward)
				(Bset fSmellsLikeDogPiss)
				(= dogPeed TRUE)
				(= seconds 3)
			)
			(4
				(sPiss stop:)
				(if
				(or (== (ego view?) 800) (== (ego view?) 809))
					(HandsOff)
					(curRoom setScript: sShakeLeg 0 register)
				)
				(dog setScript: sRunOff 0 register)
			)
		)
	)
)

(instance sRunOff of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((or (== (dog view?) 820) (== (dog loop?) 0)) (= cycles (= state 1)))
					((== (dog loop?) 2) (= cycles 1))
					(else (dog loop: (+ 2 register) cel: 2 setCycle: BegLoop self))
				)
			)
			(1
				(dog
					loop: (if register 0 else 1)
					cel: 2
					setCycle: BegLoop self
				)
			)
			(2
				((curRoom obstacles?) delete: dogPoly)
				(dog
					view: 820
					setCycle: Walk
					setMotion: PolyPath (if (> (dog x?) (ego x?)) 350 else -30) 155 self
				)
			)
			(3
				(theMusic fade:)
				(dog z: 1000 dispose:)
				(self dispose:)
			)
		)
	)
)

(instance sTaxiScript of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if (== state 4)
			(soundFx send: 9 255 (* (- (taxi x?) 150) 50))
		)
		(if (== state 5)
			(taxi x: (+ 150 register))
			(soundFx send: 9 255 (* (- (taxi x?) 150) 30))
			(if (== register 0)
				(soundFx send: 9 255 0)
				(= cycles 1)
			)
			(if (== register 16)
				(soundFX number: 202 setLoop: 1 flags: 1 play:)
			)
			(= register (/ register 2))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (ego setHeading: 180 self))
			(1
				(ego
					egoSpeed:
					view: 810
					setLoop: 0
					setCel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(2
				(soundFX number: 200 loop: 1 flags: 0 vol: 127 play:)
				(= seconds 3)
			)
			(3 (= seconds (Random 1 3)))
			(4
				(NormalEgo 2)
				(if (not (cast contains: taxi))
					(taxi
						approachVerbs: verbDo
						cycleSpeed: howFast
						moveSpeed: howFast
						init:
						x: 400
						y: taxiY
						setMotion: MoveTo 214 taxiY self
					)
					(= register 64)
					(soundFx number: 205 loop: -1 vol: 127 flags: mNOPAUSE play:)
				else
					(cond 
						((== (taxi x?) 150)
							(if modelessDialog (modelessDialog dispose:))
							(Print rgSidewalk 8)
						)
						((< (taxi x?) 150)
							(if modelessDialog (modelessDialog dispose:))
							(Print rgSidewalk 9)
						)
						(else
							(if modelessDialog (modelessDialog dispose:))
							(Print rgSidewalk 10)
						)
					)
					(HandsOn)
					(self dispose:)
				)
			)
			(5 0)
			(6 (taxi setCycle: EndLoop self))
			(7
				(HandsOn)
				(AddTaxiObstacle)
				(taxi
					setScript: sTaxiWait
					approachX: (+ (taxi x?) 10)
					approachY: (- (taxi brTop?) 8)
				)
				(self dispose:)
			)
		)
	)
)

(instance sTaxiWait of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if (== state 7)
			(taxi startUpd: x: (- 150 (/ register 4)))
			(soundFx send: 9 255 (* (- 150 (taxi x?)) 10))
			(if (< (taxi x?) -120) (= cycles 1))
			(= register (* register 2))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 14))
			(1
				(taxi startUpd:)
				(= cycles 1)
			)
			(2
				(if modelessDialog (modelessDialog dispose:))
				(Print rgSidewalk 11 #dispose #time 4)
				(= cycles 9)
			)
			(3
				(if
					(and
						(< (ego x?) (- (taxi x?) 20))
						(> (ego y?) (- (taxi brTop?) 8))
					)
					(soundFX number: 810 play:)
					(= cycles 12)
				else
					(= state 6)
					(= cycles 1)
				)
			)
			(4
				(soundFX stop:)
				(= cycles 1)
			)
			(5
				(soundFX play:)
				(= cycles 12)
			)
			(6
				(soundFX stop:)
				(= state 2)
				(= seconds 5)
			)
			(7
				(= register 1)
				(if (!= (curRoom script?) sEnterTaxi)
					((curRoom obstacles?) delete: taxiPoly)
					(soundFX number: 202 setLoop: 1 flags: 1 play:)
				else
					(self dispose:)
				)
			)
			(8
				(if (< (taxi x?) -80)
					(taxi dispose:)
					(soundFx fade:)
					(self dispose:)
				else
					(= state (- state 1))
					(= cycles 1)
				)
			)
		)
	)
)

(instance sFlattenLarry of Script
	(properties)
	
	(method (doit &tmp temp0)
		(super doit: &rest)
		(cond 
			(
				(<
					(= temp0
						(GetDistance (ego x?) (ego y?) (aCar x?) (aCar y?))
					)
					0
				)
				(= temp0 0)
			)
			((> temp0 300) (= temp0 300))
		)
		(if (< state 2) (mCar setVol: (- 127 (/ temp0 4))))
		(if (or (== state 1) (== state 2))
			(++ register)
			(mCar send: 2 255 (- (* register 100)))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(mCar play: 50)
				(ego view: 806 loop: 0 cel: 0)
				(aCar
					cycleSpeed: howFast
					moveSpeed: howFast
					init:
					view: carView
					x: 450
					setPri: (+ (ego priority?) 2)
					setMotion: MoveTo (+ (ego x?) 67) (aCar y?) self
				)
			)
			(1
				(mCar2 play:)
				(mCar3 play:)
				(ego
					setPri: (+ (aCar priority?) 1)
					x: (- (aCar x?) 67)
					setCel: 1
				)
				(aCar setMotion: MoveTo -50 (aCar y?) aCar)
				(= cycles 5)
			)
			(2
				(mCar fade: 0 5 8 1)
				(ego
					setPri: -1
					setLoop: 1
					setCel: 1
					x: (- (ego x?) 0)
					y: (+ (ego y?) 0)
				)
				(= seconds 3)
			)
			(3 (= seconds 3))
			(4
				(ShowDeathIcon 806 2)
				(Format @str1 rgSidewalk 12)
				(EgoDead rgSidewalk 13)
			)
		)
	)
)

(instance sEnterTaxi of Script
	(properties)
	
	(method (doit &tmp temp0)
		(super doit: &rest)
		(if (== state 3)
			(taxi startUpd: x: (- 150 register))
			(= temp0 (* (- 150 (taxi x?)) 10))
			(soundFx
				send: 9 255 (if (< temp0 2000) temp0 else 2000)
			)
			(if (< (taxi x?) -80) (= cycles 1))
			(= register (* register 2))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 1))
			(1
				(soundFX number: 203 loop: 1 flags: 0 vol: 127 play:)
				(ego setHeading: 180)
				(= cycles 10)
			)
			(2
				(soundFX number: 204 loop: 1 flags: 0 vol: 127 play:)
				(ego hide:)
				(= cycles 10)
			)
			(3
				(= register 1)
				(soundFX number: 202 setLoop: 1 flags: 1 play:)
			)
			(4
				(soundFx fade: 90 5 5 0)
				(curRoom newRoom: rmInsideTaxi)
			)
		)
	)
)

(instance sShakeLeg of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if (and (not state) (== (sRunOff state?) 2))
			(= cycles 1)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 0)
			(1
				(ego
					egoSpeed:
					normal: 0
					view: 811
					loop: (if register 0 else 1)
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(2
				(NormalEgo 2)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance aCar of Actor
	(properties
		x 340
		y 170
		description {the car}
		lookStr {Crazy driver!}
		view 806
		signal (| ignrAct skipCheck fixedLoop)
		xStep 30
	)
	
	(method (cue)
		(super cue:)
		(= z 1000)
		(self dispose:)
	)
)

(instance taxi of Actor
	(properties
		description {the taxi}
		sightAngle 40
		view 200
		signal (| ignrAct skipCheck fixedLoop)
		xStep 20
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(ego
					setHeading: (GetAngle (ego x?) (ego y?) (self x?) (self y?)) self
				)
			)
			(verbTaste
				(if modelessDialog (modelessDialog dispose:))
				(Print rgSidewalk 17)
			)
			(verbDo
				(if (< (taxi x?) 150)
					(if modelessDialog (modelessDialog dispose:))
					(Print rgSidewalk 18)
				else
					(if (not (Btst fEnteredTaxi))
						(Bset fEnteredTaxi)
						(if modelessDialog (modelessDialog dispose:))
						(Print rgSidewalk 19)
					)
					(HandsOff)
					(taxi setScript: 0)
					(soundFX stop:)
					(curRoom setScript: sEnterTaxi)
				)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
	
	(method (cue)
		(super cue:)
		(if modelessDialog (modelessDialog dispose:))
		(Print rgSidewalk 14)
		(Print rgSidewalk 15)
		(Print rgSidewalk 16 #at -1 140)
	)
)

(instance dog of Actor
	(properties
		description {the dog}
		sightAngle 40
		view 820
		signal ignrAct
	)
	
	(method (doVerb theVerb theItem)
		(if modelessDialog (modelessDialog dispose:))
		(switch theVerb
			(verbLook
				(cond 
					((== (dog view?) 821) (Print rgSidewalk 20))
					((== (dog script?) sRunOff) (Print rgSidewalk 21))
					(else (Print rgSidewalk 22))
				)
			)
			(verbDo (Print rgSidewalk 23))
			(verbTaste (Print rgSidewalk 24))
			(verbZipper (Print rgSidewalk 25))
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance taxiSignProp of View
	(properties
		z 100
		description {the taxi sign}
		sightAngle 40
		lookStr {High up that pole sits a lonely sign that reads, "Taxi Stand."}
		view 810
		loop 1
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(Print rgSidewalk 26)
				(HandsOff)
				(curRoom setScript: sTaxiScript)
			)
			(verbTalk (self doVerb: verbUse))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance dogPoly of Polygon
	(properties
		type PBarredAccess
	)
)

(instance taxiPoly of Polygon
	(properties
		type PBarredAccess
	)
)

(instance streetF of Feature
	(properties
		x 160
		y 480
		description {the street}
		sightAngle 85
		onMeCheck $0800
		lookStr {Isn't it funny how there's so little traffic?}
	)
)

(instance mCar of Sound
	(properties
		number 810
		vol 50
	)
)

(instance mCar2 of Sound
	(properties
		number 811
	)
)

(instance mCar3 of Sound
	(properties
		number 812
	)
)

(instance sPiss of Sound
	(properties
		flags mNOPAUSE
		number 102
		loop -1
	)
)

(instance soundFX of Sound
	(properties
		flags mNOPAUSE
	)
)
