;;; Sierra Script 1.0 - (do not remove this comment)
(script# 760)
(include game.sh)
(use Main)
(use LLRoom)
(use Talker)
(use PolyPath)
(use Polygon)
(use Feature)
(use MoveFwd)
(use ForCount)
(use LoadMany)
(use StopWalk)
(use Sound)
(use Motion)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	rm760 0
	bench 1
)

(local
	local0
	local1
	local2
	thePEventMessage
	local4
	local5
	local6
	local7
	pEventX
	pEventY
	thePicView
	local11
	[local12 4] = [0 82 103 124]
	[local16 11] = [103 124 145]
	[local27 17] = [53 53 53 53 53 53 53 53 53 53 53 53 53 106 106 53 159]
	local44
)
(instance rm760 of LLRoom
	(properties
		lookStr {The commercialization of the lovely Tramp Boardwalk seems complete. Businesses line every inch of the far side of the boardwalk. At least the near side has been kept open for fishermen and skaters.}
		picture 760
		north 790
		east 760
		south 780
		west 700
	)
	
	(method (init &tmp temp0)
		(LoadMany VIEW
			763 762 761 764 771 772 773 774
			775 776 777 778 779 769 770 791
			1731 1795
		)
		(theMusic number: 700 loop: -1 play:)
		(if (ego skating?)
			(= local5 765)
			(= local6 768)
		else
			(= local5 761)
			(= local6 764)
		)
		(ego init: normalize: local6 setStep: 3 3)
		(switch prevRoomNum
			(north
				(HandsOn)
				(ego view: local5 posn: 204 85 setHeading: 180 edgeHit: 0)
			)
			(west
				(self setScript: sFromWest)
			)
			(else 
				(HandsOn)
				(= local5 765)
				(= local6 768)
				(ego
					posn: 160 145
					edgeHit: 0
					skating: 1
					normalize: local6
				)
				(= boardwalkRoom 2)
				(= local44 99)
			)
		)
		(super init:)
		(skater1 init:)
		(skater2 init:)
		(skater3 init:)
		(skater4 init:)
		(skater5 init:)
		(skater6 init:)
		(skater7 init:)
		(skater8 init:)
		(skater9 init:)
		(bench init:)
		(post1 init:)
		(post2 init:)
		(post3 init:)
		(post4 init:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						90 145
						154 145
						154 160
						90 160
					yourself:
				)
		)
		(directionHandler addToFront: self)
		(mouseDownHandler addToFront: self)
		(keyDownHandler addToFront: self)
	)
	
	(method (doit &tmp [temp0 80])
		(super doit:)
		(cond 
			((not (OneOf script 0 sMoveLarry)))
			((IsObjectOnControl ego cGREEN)
				(self setScript: sScroll 0 2)
			)
			((IsObjectOnControl ego cCYAN)
				(cond 
					(global159
						(self setScript: sScroll 0 4)
					)
					((and (ego skating?) (not local2))
						(if (not local1) (TimePrint 760 0) (= local1 1))
						(HandsOff)
						(ego setMotion: MoveTo (+ (ego x?) 10) (ego y?) self)
						(= local2 1)
					)
				)
			)
		)
		(if
			(and
				(ego skating?)
				(ego mover?)
				(< boardwalkRoom 3)
				(== (++ local44) 100)
			)
			(= local44 0)
			(if (and (== (++ boardwalkRoom) 3) (not (Btst fMetLana)))
				(lana init:)
			)
		)
	)
	
	(method (dispose)
		(directionHandler delete: self)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(theMusic fade:)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp eType eMsg)
		(= eType (event type?))
		(= eMsg (event message?))
		(cond 
			((and (& eType $0040) (== eMsg 0)) (event claimed: 0))
			(
				(or
					(and
						(OneOf eType 1 4)
						(not (event modifiers?))
						(== theCursor 6)
					)
					(and (== eType 16384) (== eMsg 1))
				)
				(sMeetLana cue:)
			)
			((== (ego script?) sMeetLana) 0)
			((not (User canControl:)) (event claimed: 1))
			((& eType $0040)
				(if (not (ego sitting?))
					(if (and (ego mover?) (== eMsg thePEventMessage))
						(ego setMotion: 0)
						(= thePEventMessage 0)
					else
						(switch eMsg
							(1
								(event x: (ego x?) y: (- (ego y?) 20))
							)
							(2
								(event x: (+ (ego x?) 100) y: (- (ego y?) 20))
							)
							(3 (event x: 1000 y: (ego y?)))
							(4
								(event x: (+ (ego x?) 20) y: (+ (ego y?) 20))
							)
							(5
								(event x: (ego x?) y: (+ (ego y?) 20))
							)
							(6
								(event x: (- (ego x?) 20) y: (+ (ego y?) 20))
							)
							(7 (event x: 0 y: (ego y?)))
							(8
								(event x: (- (ego x?) 20) y: (- (ego y?) 20))
							)
							(0
								(theIconBar advanceCurIcon:)
								(event claimed: 1)
							)
						)
						(if (!= eMsg 0)
							(= pEventX (event x?))
							(= pEventY (event y?))
							(if (OneOf (curRoom script?) 0 sMoveLarry)
								(curRoom setScript: sMoveLarry)
							)
						)
					)
					(= thePEventMessage eMsg)
				else
					(curRoom setScript: sStandUp 0 0)
				)
				(event claimed: 1)
			)
			(
				(or
					(and
						(== eType 1)
						(not (event modifiers?))
						(or
							(== (theIconBar curIcon?) (theIconBar at: 0))
							(== eMsg 1)
						)
					)
					(and (== eType 4) (== eMsg 13))
				)
				(cond 
					((ego sitting?) (event claimed: 1) (curRoom setScript: sStandUp 0 0))
					((OneOf (curRoom script?) 0 sMoveLarry)
						(= pEventX (event x?))
						(if (> (= pEventY (event y?)) 158)
							(if (not (ego skating?))
								(curRoom setScript: sFall)
							else
								(= pEventY 145)
								(curRoom setScript: sMoveLarry)
							)
						else
							(if (> pEventY 145) (= pEventY 145))
							(curRoom setScript: sMoveLarry)
						)
					)
				)
			)
		)
	)
	
	(method (cue)
		(if local2
			(= local2 0)
			(HandsOn)
		else
			(ego edgeHit: 1)
			(curRoom newRoom: (curRoom north?))
		)
	)
	
	(method (drawPic pic &tmp temp0 temp1 temp2 temp3 temp4 temp5 temp6)
		(if (!= pic 795)
			(cast eachElementDo: #perform showSkaterCode)
			(addToPics dispose:)
			(super drawPic: pic &rest)
			(= temp4 1)
			(= temp0 0)
			(while (< temp4 318)
				(= temp3 (* (+ global159 1) temp0 37))
				(if (& $0001 (>> temp3 temp0)) (= temp3 (/ temp3 17)))
				(if (and (== global159 3) (== temp0 3))
					(= temp2 2)
					(= temp3 0)
					(= temp5 106)
				else
					(= temp5 [local27 (= temp3 (mod temp3 17))])
					(= temp2 0)
					(if (> temp3 8) (= temp3 (- temp3 9)) (= temp2 1))
				)
				(= temp6
					(/ (CelWide (theBuilding view?) temp2 temp3) 2)
				)
				((theBuilding new:)
					x: temp4
					loop: temp2
					cel: temp3
					approachX: (Min 300 (+ temp4 temp6))
					approachY: 85
					init:
				)
				(= temp4 (+ temp4 temp5))
				(++ temp0)
			)
			(addToPics doit:)
		else
			(super drawPic: pic &rest)
		)
	)
)

(class Skater of Actor
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 0
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		description 0
		sightAngle 26505
		actions 0
		onMeCheck $6789
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 26505
		lookStr 0
		yStep 2
		view 0
		loop 0
		cel 0
		priority 0
		underBits 0
		signal $4000
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		palette 0
		cycleSpeed 12
		script 0
		cycler 0
		timer 0
		detailLevel 0
		illegalBits $8000
		xLast 0
		yLast 0
		xStep 3
		moveSpeed 6
		blocks 0
		baseSetter 0
		mover 0
		looper 0
		viewer 0
		avoider 0
		code 0
		room 0
		freq 1
		nearLarry 0
		paused 0
	)
	
	(method (init)
		(self setCycle: Forward)
		(super init:)
		(= x 160)
		(self cue:)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(nearLarry (-- nearLarry))
			(
			(and (== room global159) (< (ego distanceTo: self) 30)) (= nearLarry 18))
			((and (not mover) (OneOf x 0 320)) (self cue:))
		)
		(cond 
			((<= freq 0))
			((== loop 0)
				(cond 
					((and (== nearLarry 18) (self isNotHidden:)) (self turn: 4))
					((and (< 300 x) (< x 312))
						(cond 
							((or (== (Random 1 freq) 1) (== room 20)) (self turn: 4))
							((& signal $0080)
								(++ room)
								(= x (- x 320))
								(self setMotion: MoveTo 320 y)
								(if (== room global159) (self show:))
							)
							(else (self freq: (* freq -1) setMotion: MoveFwd 40 self))
						)
					)
				)
			)
			((== loop 1)
				(cond 
					((and (== nearLarry 18) (self isNotHidden:)) (self turn: 2))
					((and (< 13 x) (< x 25))
						(cond 
							((or (== (Random 1 freq) 1) (== room 0)) (self turn: 2))
							((& signal $0080)
								(-- room)
								(= x (+ x 320))
								(self setMotion: MoveTo 0 y)
								(if (== room global159) (self show:))
							)
							(else (self freq: (* freq -1) setMotion: MoveFwd 40 self))
						)
					)
				)
			)
		)
	)
	
	(method (cue)
		(if (== loop 1)
			(-- room)
			(self
				x: (+ x 320)
				hide:
				setMotion: MoveTo 0 y
				freq: (if (< freq 0) (* freq -1) else freq)
				nearLarry: 0
			)
		else
			(++ room)
			(self
				x: (- x 320)
				hide:
				setMotion: MoveTo 320 y
				freq: (if (< freq 0) (* freq -1) else freq)
				nearLarry: 0
			)
		)
	)
	
	(method (turn param1)
		(switch param1
			(4
				(self setLoop: 1 setMotion: MoveTo 0 y)
			)
			(2
				(self setLoop: 0 setMotion: MoveTo 320 y)
			)
		)
	)
)

(instance lana of Skater
	(properties
		y 145
		description {Lana Luscious}
		view 769
		cycleSpeed 10
		xStep 6
		freq 99
	)
	
	(method (init &tmp temp0 temp1)
		(super init:)
		(= temp0 (Max 0 (- global159 4)))
		(= temp1 (Min 20 (+ global159 4)))
		(while (== (= room (Random temp0 temp1)) global159)
		)
		(if (> room global159)
			(self setLoop: 1)
		else
			(self setLoop: 0)
		)
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(== room global159)
				(not (& signal $0088))
				(not local0)
				(< 20 x)
				(< x 300)
			)
			(TimePrint 760 1)
			(= local0 1)
			(= freq 6)
		)
		(if
			(and
				local0
				(not local4)
				(or (== (ego y?) y) (ego sitting?))
				(< (ego distanceTo: self) 40)
				(self isNotHidden:)
			)
			(= local4 1)
			(ego setScript: sMeetLana)
		)
	)
	
	(method (dispose)
		(HandsOn)
		(super dispose:)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(2 (TimePrint 760 1))
			(5
				(if (or (< (lana x?) 25) (> (lana x?) 300))
					(TimePrint 760 2)
				else
					(= local4 1)
					(ego setScript: sMeetLana)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
	
	(method (cue)
		(cond 
			((not freq) (self dispose:))
			((== loop 1)
				(-- room)
				(self
					x: (+ x 320)
					hide:
					setMotion: MoveTo 0 y
					freq: (* freq -1)
				)
			)
			(else
				(++ room)
				(self
					x: (- x 320)
					hide:
					setMotion: MoveTo 320 y freq (* freq -1)
				)
			)
		)
	)
	
	(method (turn)
		(if nearLarry (= nearLarry 0) else (super turn: &rest))
	)
)

(instance skater1 of Skater
	(properties
		y 140
		description {Mister Z}
		lookStr {Isn't he the cute one?}
		view 771
		xStep 8
		room 3
		freq 4
	)
	
	(method (doit)
		(if (not paused) (super doit:))
	)
	
	(method (checkDetail)
		(if (> (theGame detailLevel:) 1)
			(if (>= z 1000) (self perform: zCode))
			(= paused 0)
		else
			(= paused 1)
			(if (< z 1000) (self perform: zCode))
		)
	)
)

(instance skater2 of Skater
	(properties
		y 121
		description {the old man}
		lookStr {He's a little old for a rollerskater, eh?}
		view 772
		xStep 8
		room 3
		freq 4
	)
	
	(method (doit)
		(if (not paused) (super doit:))
	)
	
	(method (checkDetail)
		(if (> (theGame detailLevel:) 1)
			(if (>= z 1000) (self perform: zCode))
			(= paused 0)
		else
			(= paused 1)
			(if (< z 1000) (self perform: zCode))
		)
	)
)

(instance skater3 of Skater
	(properties
		y 118
		z 10
		description {Hot Wheels}
		lookStr {This kid's a real showoff!}
		view 773
		xStep 8
		room 1
		freq 3
	)
	
	(method (doit)
		(if (not paused) (super doit:))
	)
	
	(method (checkDetail)
		(if (> (theGame detailLevel:) 2)
			(if (>= z 1000) (self perform: zCode))
			(= paused 0)
		else
			(= paused 1)
			(if (< z 1000) (self perform: zCode))
		)
	)
)

(instance skater4 of Skater
	(properties
		y 116
		description {Rockin' Roller}
		lookStr {What's he listening to, anyway?}
		view 774
		xStep 6
		room 2
		freq 2
	)
	
	(method (doit)
		(if (not paused) (super doit:))
	)
	
	(method (checkDetail)
		(if (> (theGame detailLevel:) 2)
			(if (>= z 1000) (self perform: zCode))
			(= paused 0)
		else
			(= paused 1)
			(if (< z 1000) (self perform: zCode))
		)
	)
)

(instance skater5 of Skater
	(properties
		y 142
		description {Roller Pig}
		lookStr {What talent! He can eat AND skate at the same time!}
		view 775
		xStep 8
		room 2
		freq 2
	)
	
	(method (doit)
		(if (not paused) (super doit:))
	)
	
	(method (checkDetail)
		(if (> (theGame detailLevel:) 3)
			(if (>= z 1000) (self perform: zCode))
			(= paused 0)
		else
			(= paused 1)
			(if (< z 1000) (self perform: zCode))
		)
	)
)

(instance skater6 of Skater
	(properties
		y 117
		description {Bill the Suit}
		lookStr {He's way overdressed for THIS boardwalk!}
		view 776
		xStep 6
		room 2
		freq 2
	)
	
	(method (doit)
		(if (not paused) (super doit:))
	)
	
	(method (checkDetail)
		(if (> (theGame detailLevel:) 3)
			(if (>= z 1000) (self perform: zCode))
			(= paused 0)
		else
			(= paused 1)
			(if (< z 1000) (self perform: zCode))
		)
	)
)

(instance skater7 of Skater
	(properties
		y 131
		description {Kid Dork}
		lookStr {What a dorky lookin' little guy! You immediately take a liking to him!}
		view 777
		xStep 8
		room 2
		freq 2
	)
	
	(method (doit)
		(if (not paused) (super doit:))
	)
	
	(method (checkDetail)
		(if (> (theGame detailLevel:) 4)
			(if (>= z 1000) (self perform: zCode))
			(= paused 0)
		else
			(= paused 1)
			(if (< z 1000) (self perform: zCode))
		)
	)
)

(instance skater8 of Skater
	(properties
		y 110
		description {the skater}
		lookStr {You had no idea the boardwalk would have so many skaters!}
		view 778
		xStep 6
		room 2
		freq 2
	)
	
	(method (doit)
		(if (not paused) (super doit:))
	)
	
	(method (checkDetail)
		(if (> (theGame detailLevel:) 4)
			(if (>= z 1000) (self perform: zCode))
			(= paused 0)
		else
			(= paused 1)
			(if (< z 1000) (self perform: zCode))
		)
	)
)

(instance skater9 of Skater
	(properties
		y 91
		description {the skater}
		lookStr {Never straying far from the shops on the other side of the Boardwalk, a skater finds true happiness, blading his little heart out!}
		view 779
		xStep 6
		room 2
		freq 2
	)
	
	(method (doit)
		(if (not paused) (super doit:))
	)
	
	(method (checkDetail)
		(if (> (theGame detailLevel:) 4)
			(if (>= z 1000) (self perform: zCode))
			(= paused 0)
		else
			(= paused 1)
			(if (< z 1000) (self perform: zCode))
		)
	)
)

(instance zCode of Code
	(properties)
	
	(method (doit param1)
		(if (== (param1 z?) 1000)
			(param1 z: 0)
		else
			(param1 z: 1000)
		)
	)
)

(instance showSkaterCode of Code
	(properties)
	
	(method (doit param1)
		(if (param1 respondsTo: #freq)
			(if (== (param1 room?) global159)
				(param1 show:)
			else
				(param1 hide:)
			)
		)
	)
)

(instance theBuilding of PicView
	(properties
		y 80
		description {the building}
		view 760
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(2
				(switch loop
					(0
						(switch cel
							(0 (TimePrint 760 3))
							(1 (TimePrint 760 4))
							(2 (TimePrint 760 5))
							(3 (TimePrint 760 6))
							(4 (TimePrint 760 7))
							(5 (TimePrint 760 8))
							(6 (TimePrint 760 9))
							(7 (TimePrint 760 10))
							(8 (TimePrint 760 11))
						)
					)
					(1
						(switch cel
							(0 (TimePrint 760 12))
							(1 (TimePrint 760 13))
							(2 (TimePrint 760 14))
							(3 (TimePrint 760 15))
							(4 (TimePrint 760 16))
							(5 (TimePrint 760 17))
							(6 (TimePrint 760 18))
							(7 (TimePrint 760 19))
						)
					)
					(2 (TimePrint 760 20))
				)
			)
			(3
				(if
					(and
						(== (ego view?) local5)
						(== (ego x?) approachX)
						(== (ego y?) approachY)
					)
					(if (== loop 2)
						(if (ego skating?)
							(TimePrint 760 21)
						else
							(ego setHeading: 0 curRoom)
						)
					else
						(TimePrint 760 22)
						(TimePrint 760 23 #at -1 185)
					)
				else
					(curRoom
						handleEvent:
							(= temp0
								((Event new:)
									type: 1
									message: 1
									x: approachX
									y: approachY
								)
							)
					)
					(= thePicView self)
					(= local11 3)
					(temp0 dispose:)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance sSplash of Sound
	(properties
		number 761
	)
)

(instance splash of Prop
	(properties
		y 189
		view 770
		loop 2
	)
)

(instance rightEye of Prop
	(properties
		x 165
		y 180
		z 119
		description {Lana's right eye}
		view 795
	)
	
	(method (doVerb)
		(lanaFtr doVerb: &rest)
	)
)

(instance leftEye of Prop
	(properties
		x 140
		y 180
		z 123
		description {Lana's left eye}
		view 795
		loop 2
	)
	
	(method (doVerb)
		(lanaFtr doVerb: &rest)
	)
)

(instance nose of Prop
	(properties
		x 149
		y 180
		z 108
		description {Lana's nose}
		view 795
		loop 1
		cycleSpeed 18
	)
	
	(method (init)
		(super init:)
		(self setCycle: Forward)
	)
	
	(method (doVerb)
		(lanaFtr doVerb: &rest)
	)
)

(instance lanaMouth of Prop
	(properties
		x 149
		y 180
		z 102
		description {Lana's mouth}
		view 795
		loop 3
	)
	
	(method (doVerb)
		(lanaFtr doVerb: &rest)
	)
)

(instance Lana_Luscious_a of Talker
	(properties
		x 140
		y 160
		nsTop 70
		nsLeft 140
		view 1795
		loop 1
		name "Lana Luscious\_a"
	)
	
	(method (init)
		(= mouth lanaCMouth)
		(super init: &rest)
	)
)

(instance lanaCMouth of Prop
	(properties
		view 1795
	)
)

(instance Lana_Luscious_b of Talker
	(properties
		nsTop 100
		nsLeft 5
		view 1731
		loop 3
		viewInPrint 1
		name "Lana Luscious\_b"
	)
	
	(method (init)
		(= bust lanaFBust)
		(= mouth lanaFMouth)
		(= eyes lanaFEyes)
		(super init: &rest)
	)
)

(instance lanaFBust of View
	(properties
		view 1731
		loop 1
	)
)

(instance lanaFMouth of Prop
	(properties
		nsTop 40
		nsLeft 23
		view 1731
	)
)

(instance lanaFEyes of Prop
	(properties
		nsTop 31
		nsLeft 29
		view 1731
		loop 2
		cycleSpeed 12
	)
)

(instance sFromWest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego view: 764 posn: -10 145 setMotion: MoveFwd 40 self)
			)
			(1
				(HandsOn)
				((ego looper?) dispose:)
				(self dispose:)
			)
		)
	)
)

(instance sFall of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (IsObjectOnControl ego 16384)
			(ego setMotion: MoveTo (ego x?) 145)
			(HandsOn)
			(if (== (curRoom script?) sMoveLarry)
				(sMoveLarry dispose:)
			)
			(self dispose:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (< (ego y?) 145)
					(sMoveLarry next: self)
					(= pEventY 145)
					(client setScript: sMoveLarry)
				else
					(ego
						setLoop: 2
						setCycle: StopWalk -1
						setMotion: PolyPath (ego x?) 158 self
					)
				)
			)
			(1
				(ego
					view: 770
					setLoop: 0
					setCel: 0
					cycleSpeed: 10
					setCycle: EndLoop
				)
				(= cycles 10)
			)
			(2
				(ego setLoop: 1 setCel: 0 setCycle: EndLoop)
				(= ticks 90)
			)
			(3
				(sSplash play:)
				(splash init: x: (ego x?) setCycle: EndLoop self)
			)
			(4 (curRoom newRoom: 780))
		)
	)
)

(instance sScroll of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveFwd 40 self)
			)
			(1
				(if (== register 4)
					(-- global159)
					(ego x: (+ (ego x?) 320))
					(curRoom drawPic: (curRoom picture?) 11)
				else
					(++ global159)
					(ego x: (- (ego x?) 320))
					(curRoom drawPic: (curRoom picture?) 12)
				)
				(= cycles 1)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance sMeetLana of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (< (ego y?) 145)
					(= pEventY 145)
					(= thePicView self)
					(curRoom setScript: sMoveLarry)
				else
					(= cycles 1)
				)
			)
			(1
				(HandsOff)
				(if (== (curRoom script?) sMoveLarry)
					(sMoveLarry dispose:)
				)
				(SolvePuzzle 6 fTalkedToLana)
				(if (and (ego sitting?) (!= (ego loop?) 4))
					(self setScript: sStandUp self 0)
				else
					(= cycles 2)
				)
			)
			(2
				(lana setMotion: 0)
				(Say ego 760 24)
				(Say Lana_Luscious_b 760 25 108)
				(= ticks 30)
			)
			(3
				(Say ego 760 26)
				(Say Lana_Luscious_b 760 27 108)
				(= ticks 30)
			)
			(4
				(Say ego 760 28)
				(= ticks 30)
			)
			(5
				(ego
					setLoop: -1
					setCycle: StopWalk -1
					setMotion: MoveTo 145 145 self
				)
				(lana setScript: sLanaSits self)
			)
			(6
				(ego view: 791 setLoop: 4 setCel: 0 setCycle: EndLoop)
			)
			(7
				(ego setLoop: 5 setCel: 0 setCycle: EndLoop self sitting: 1)
			)
			(8
				(cast eachElementDo: #perform zCode)
				(InFirstPerson 1)
				(curRoom drawPic: 795 100)
				(HandsOn)
				(rightEye init:)
				(leftEye init:)
				(nose init:)
				(lanaMouth init:)
				(features addToFront: lanaFtr)
			)
			(9
				(HandsOff)
				(InFirstPerson 0)
				(curRoom drawPic: 760 100)
				(rightEye dispose:)
				(leftEye dispose:)
				(nose dispose:)
				(lanaMouth dispose:)
				(lanaFtr dispose:)
				(addToPics doit:)
				(cast eachElementDo: #perform zCode)
				(skater1 cue:)
				(skater2 cue:)
				(skater3 cue:)
				(skater4 cue:)
				(skater5 cue:)
				(skater6 cue:)
				(skater7 cue:)
				(skater8 cue:)
				(skater9 cue:)
				(= cycles 3)
			)
			(10
				(lana
					freq: 0
					setLoop: 5
					setCel: 255
					posn: 107 145
					setCycle: BegLoop self
				)
			)
			(11 (ego setCycle: BegLoop self))
			(12
				(ego setLoop: 4 setCel: 255)
				(lana
					setLoop: 1
					setCycle: Forward
					setMotion: MoveTo -20 145 lana
				)
				(skater5 setPri: 10)
				(Bset fMetLana)
				(self dispose:)
			)
		)
	)
)

(instance sLanaSits of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(lana setPri: 9)
				(if (< (lana x?) 51)
					(self changeState: 2)
				else
					(lana setLoop: 1 setMotion: MoveTo 50 145 self)
				)
			)
			(1
				(lana setLoop: 2 setCycle: ForwardCounter 2 self)
			)
			(2
				(lana
					setLoop: 3
					setCycle: EndLoop
					setMotion: MoveTo 93 145 self
				)
			)
			(3
				(lana
					setLoop: 4
					setCycle: Forward
					setMotion: MoveTo 107 145 self
				)
			)
			(4
				(skater5 setPri: 9)
				(lana posn: (lana x?) 148 setLoop: 5 setCycle: EndLoop self)
			)
			(5 (self dispose:))
		)
	)
)

(instance sSitDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= pEventX (if register 90 else 142))
				(= pEventY 145)
				(= thePicView self)
				(= local11 0)
				(curRoom setScript: sMoveLarry)
			)
			(1
				(if register
					(ego setMotion: MoveTo 90 149 self)
				else
					(= cycles 1)
				)
			)
			(2
				(if register
					(ego
						view: 791
						setLoop: (if (ego skating?) 2 else 0)
						setCel: 0
						setCycle: EndLoop self
						sitting: 1
					)
				else
					(ego
						view: 791
						setLoop: 4
						setCel: 0
						setCycle: EndLoop self
						sitting: 1
					)
				)
			)
			(3 (self dispose:))
		)
	)
)

(instance sDoSkates of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (ego skating?)
					(ego setLoop: 1 setCel: 255 setCycle: BegLoop self)
				else
					(SolvePuzzle 3 fPutOnSkates)
					(ego setLoop: 1 setCel: 0 setCycle: EndLoop self)
				)
			)
			(1
				(if (ego skating?)
					(ego skating: 0)
					(= local5 761)
					(= local6 764)
					((Inventory at: iRollerskates) state: 0)
					(ego setLoop: 0 setCel: 255)
					(LoadMany VIEW 763 762 761 764)
				else
					(ego skating: 1)
					(= local5 765)
					(= local6 768)
					((Inventory at: iRollerskates) state: 1)
					(ego setLoop: 2 setCel: 255)
					(LoadMany VIEW 767 766 765 768)
				)
				(self dispose:)
			)
		)
	)
)

(instance sStandUp of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (ego setCycle: BegLoop self))
			(1
				(ego
					sitting: 0
					normalize: local6
					setStep: 3 3
					setHeading: 0
					posn: (ego x?) 145
				)
				(if register (bench doVerb: 4 18))
				(self dispose:)
			)
		)
	)
)

(instance sMoveLarry of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(ego normalize: (ego view?) setStep: 3 3)
				(cond 
					((< pEventY (- (ego y?) 5)) (= register 1))
					((> pEventY (+ (ego y?) 5)) (= register 2))
					(else (= register 0))
				)
				(= cycles 1)
			)
			(1
				(switch register
					(0
						(User canControl: 1)
						(++ state)
						(ego
							setCycle: StopWalk -1
							setMotion: PolyPath pEventX (Min 145 (ego y?)) self
						)
					)
					(1
						(User canControl: 0)
						(if (== (ego view?) local5)
							(= register 0)
							(= cycles 1)
						else
							(= temp0 (- (ego view?) local5))
							(ego
								setLoop: 3
								setCel: 0
								setCycle: EndLoop
								setMotion: PolyPath (ego x?) [local12 temp0] self
							)
						)
					)
					(2
						(User canControl: 0)
						(if (== (ego view?) local6)
							(= register 0)
							(= cycles 1)
						else
							(= temp0 (- (ego view?) local5))
							(ego
								setLoop: 2
								setCel: 0
								setCycle: EndLoop
								setMotion: PolyPath (ego x?) [local16 temp0] self
							)
						)
					)
				)
			)
			(2
				(switch register
					(1
						(if (< (ego view?) 769)
							(ego view: (- (ego view?) 1) setLoop: -1 setCel: 255)
						)
						(if (< (- (ego y?) pEventY) 6) (= register 0))
					)
					(2
						(if (< (ego view?) 769)
							(ego view: (+ (ego view?) 1) setLoop: -1 setCel: 0)
						)
						(if (< (- pEventY (ego y?)) 6) (= register 0))
					)
				)
				(self changeState: 1)
			)
			(3
				(cond 
					(local11 (thePicView doVerb: local11))
					(thePicView (thePicView cue:))
				)
				(= thePicView (= local11 0))
				(User canControl: 1)
				(self dispose:)
			)
		)
	)
)

(instance sLanaTalks of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Say ego 760 29)
				(= ticks 60)
			)
			(1
				(Say Lana_Luscious_a 760 30 108)
				(= ticks 60)
			)
			(2)
			(3
				(Say ego 760 31)
				(= ticks 60)
			)
			(4
				(Say Lana_Luscious_a 760 32 108)
				(= ticks 60)
			)
			(5)
			(6
				(Say ego 760 33)
				(= ticks 60)
			)
			(7
				(Say Lana_Luscious_a 760 34 108)
				(= ticks 60)
			)
			(8)
			(9
				(Say ego 760 35)
				(= ticks 60)
			)
			(10
				(Say Lana_Luscious_a 760 36 108)
				(= ticks 60)
			)
			(11
				(TimePrint 760 37 #at -1 185)
				(= ticks 60)
			)
			(12)
			(13
				(SolvePuzzle 2 fAskedLanaOut)
				(Say ego 760 38)
				(= ticks 60)
			)
			(14
				(Say Lana_Luscious_a 760 39 108)
				(= ticks 60)
			)
			(15
				(TimePrint 760 40 #at -1 185)
				(= cycles 1)
			)
			(16
				(sMeetLana cue:)
				(self dispose:)
			)
		)
	)
)

(instance lanaFtr of Feature
	(properties
		x 160
		y 170
		nsBottom 189
		nsRight 319
		description {Lana Luscious}
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2 (TimePrint 760 41))
			(5
				(if (curRoom script?)
					((curRoom script?) cue:)
				else
					(curRoom setScript: sLanaTalks)
				)
			)
			(3
				(Say Lana_Luscious_a 760 42 108)
			)
			(10
				(Say Lana_Luscious_a 760 43 108)
			)
			(4
				(Say Lana_Luscious_a 760 44 108)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance buildings of Feature
	(properties
		x 159
		y 29
		nsBottom 59
		nsRight 319
		description {the boardwalk buildings}
		sightAngle 40
		lookStr {The far side of the Tramp Boardwalk is lined with tawdry, tacky tourist traps. A bit of blue sky peeks through the buildings.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(3 (TimePrint 760 45))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance bench of Feature
	(properties
		x 126
		y 140
		nsTop 129
		nsLeft 98
		nsBottom 151
		nsRight 154
		description {the bench}
		sightAngle 40
		approachX 88
		approachY 152
		lookStr {A long row of park benches await your sitting pleasure along the ocean side of the boardwalk.}
	)
	
	(method (doVerb theVerb theItem &tmp temp0)
		(switch theVerb
			(3
				(cond 
					((not (ego sitting?))
						(ego
							setScript: sSitDown 0 (if (sSitDown next:) 1 else 0)
						)
					)
					((== (ego loop?) 4) (ego setScript: sStandUp 0 (ego has: 18)))
					((ego has: 18) (ego setScript: sDoSkates))
					(else (super doVerb: theVerb theItem &rest))
				)
			)
			(4
				(if (== theItem 18)
					(sSitDown next: sDoSkates)
					(self doVerb: 3)
				else
					(super doVerb: theVerb theItem &rest)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance post1 of Feature
	(properties
		x 61
		y 160
		nsTop 131
		nsLeft 51
		nsBottom 189
		nsRight 72
		description {the post}
		sightAngle 40
		lookStr {These posts hold up the boardwalk.}
	)
)

(instance post2 of Feature
	(properties
		x 137
		y 163
		nsTop 138
		nsLeft 125
		nsBottom 189
		nsRight 149
		description {the post}
		sightAngle 40
		lookStr {These posts hold up the boardwalk.}
	)
)

(instance post3 of Feature
	(properties
		x 205
		y 164
		nsTop 139
		nsLeft 193
		nsBottom 189
		nsRight 221
		description {the post}
		sightAngle 40
		lookStr {These posts hold up the boardwalk.}
	)
)

(instance post4 of Feature
	(properties
		x 295
		y 165
		nsTop 141
		nsLeft 283
		nsBottom 189
		nsRight 309
		description {the post}
		sightAngle 40
		lookStr {These posts hold up the boardwalk.}
	)
)
