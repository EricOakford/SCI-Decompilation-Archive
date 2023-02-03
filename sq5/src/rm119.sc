;;; Sierra Script 1.0 - (do not remove this comment)
(script# 119)
(include game.sh) (include "119.shm")
(use Main)
(use GravMover)
(use starCon)
(use Print)
(use PAvoid)
(use Talker)
(use PolyPath)
(use Polygon)
(use Feature)
(use ForCount)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm119 0
	genTalker 1
	quirkTalker2 2
	beaTalker 10
	quirkTalker 14
	rogTalker 15
)

(local
	[local0 16] = [160 46 160 65 138 65 138 46 178 74 200 74 200 93 178 93]
	[local16 10]
	scrubberState
	conesOnFloor
	local28
	gEgoX
	gEgoY
	scrubberPoly
	local32
	local33 =  5
	local34 =  -1
	local35 =  5
	theTheCone1
	cleanSpotControl
	dirtyFloorControl
	local39
	cone
	local41
	theSeconds =  30
	[local43 104] = [-22 175 330 175 -22 1 330 1 29 143 0 -22 320 -22 175 330 175 -22 1 330 1 29 143 0 -22 320 29 -10 29 200 287 -10 287 200 81 231 0 -10 200 29 200 29 -10 287 200 287 -10 81 231 0 200 -10 0 100 79 189 228 0 319 62 27 130 100 -10 190 51 1 0 64 319 59 234 189 170 270 -170 -10 220 170 189 319 50 0 66 74 -1 0 120 140 200 -10 102 189 0 107 319 66 237 -10 200 300 -220 200 -10]
)
(procedure (OverheadEgo)
	(NormalEgo 129 &rest)
	(ego
		baseSetter: myBaseSetter
		setPri: 14
		setStep: 6 5
		setScale: 0
		noun: 19
		setAvoider: PAvoider
	)
)

(procedure (OverheadActor theActor theView theLoop theCel theX theY thePri)
	(theActor
		view: theView
		loop: theLoop
		cel: theCel
		x: theX
		y: theY
		setPri: thePri
		ignoreActors: TRUE
	)
)

(procedure (localproc_0264 &tmp temp0 temp1 temp2 [temp3 10])
	(= temp0 19)
	(while (< temp0 155)
		(= temp1 72)
		(while (< temp1 252)
			(if
				(and
					(& (= temp2 (OnControl cGREEN temp1 temp0)) $001e)
					(< (= temp2 (OnControl cBLUE temp1 temp0)) 4)
				)
				(return 0)
			)
			(= temp1 (+ temp1 10))
		)
		(= temp0 (+ temp0 10))
	)
	(return 1)
)

(procedure (localproc_02bb &tmp temp0 temp1 temp2 temp3 temp4)
	(p1 case: 9)
	(p2 case: 9)
	(p3 case: 9)
	(p4 case: 9)
	(= temp2 (- (ego x?) 4))
	(= temp1 (- (ego y?) 10))
	(= temp4 (+ (ego x?) 2))
	(= temp3 (+ (ego y?) 9))
	(Graph GFillRect temp1 temp2 temp3 temp4 2 -1 2 -1)
	(Graph
		GFillRect
		(+ temp1 1)
		(- temp2 2)
		(- temp3 1)
		(+ temp4 2)
		2
		-1
		2
		-1
	)
	(Graph
		GFillRect
		(+ temp1 2)
		(- temp2 3)
		(- temp3 2)
		(+ temp4 3)
		2
		-1
		2
		-1
	)
	(Graph
		GFillRect
		(+ temp1 3)
		(- temp2 4)
		(- temp3 3)
		(+ temp4 4)
		2
		-1
		2
		-1
	)
	(Graph
		GFillRect
		(+ temp1 4)
		(- temp2 5)
		(- temp3 4)
		(+ temp4 5)
		2
		-1
		2
		-1
	)
	(Graph
		GFillRect
		(+ temp1 6)
		(- temp2 6)
		(- temp3 6)
		(+ temp4 6)
		2
		-1
		2
		-1
	)
)

(procedure (localproc_03e7 param1 &tmp temp0 [temp1 30])
	(switch param1
		(0
			(if (< (= temp0 (- (ego heading?) 90)) 0)
				(= temp0 (+ temp0 360))
			)
			(if (> (= temp0 (- 450 temp0)) 359)
				(= temp0 (- temp0 360))
			)
		)
		(1
			(if
			(not (mod (= temp0 (- 360 (ego heading?))) 180))
				(= temp0 (- temp0 180))
			)
		)
		(2
			(if (< (= temp0 (- (ego heading?) 180)) 0)
				(= temp0 (+ temp0 360))
			)
		)
	)
	(ego setMotion: GravMover temp0)
)

(procedure (localproc_047b &tmp temp0 temp1 temp2 temp3 temp4 temp5)
	(cond 
		(
			(and
				conesOnFloor
				(InRect
					(- (cone1 nsLeft?) 10)
					(- (cone1 nsTop?) 10)
					(+ (cone1 nsRight?) 10)
					(+ (cone1 nsBottom?) 10)
					disk
				)
			)
			(= cone cone1)
		)
		(
			(and
				conesOnFloor
				(InRect
					(- (cone2 nsLeft?) 10)
					(- (cone2 nsTop?) 10)
					(+ (cone2 nsRight?) 10)
					(+ (cone2 nsBottom?) 10)
					disk
				)
			)
			(= cone cone2)
		)
		(
			(and
				conesOnFloor
				(InRect
					(- (cone3 nsLeft?) 10)
					(- (cone3 nsTop?) 10)
					(+ (cone3 nsRight?) 10)
					(+ (cone3 nsBottom?) 10)
					disk
				)
			)
			(= cone cone3)
		)
		(
			(and
				(cast contains: ap)
				(InRect
					(- (ap nsLeft?) 15)
					(- (ap nsTop?) 15)
					(+ (ap nsRight?) 15)
					(+ (ap nsBottom?) 15)
					disk
				)
			)
			(= cone ap)
			(if (and (not theTheCone1) (== scrubberState 2))
				(messager
					say: 10 2 0 (+ (= local34 (mod (++ local34) 8)) 1)
				)
			)
		)
		((not (if (< 5 (ego x?)) (< (ego x?) 315))) (= cone 2) (return -2))
		((not (if (< 5 (ego y?)) (< (ego y?) 185))) (= cone 3) (return -3))
		(else (return -1))
	)
	(if (OneOf cone cone1 cone2 cone3)
		(cone setLoop: 0 setCycle: ForwardCounter 3 cone)
	)
	(= temp1
		(GetAngle
			(cone x?)
			(cone y?)
			(ego x?)
			(ego y?)
		)
	)
	(= temp2 (SinMult temp1 500))
	(= temp3 (- (CosMult temp1 500)))
	(= temp4 (SinMult (ego heading?) 500))
	(= temp5 (- (CosMult (ego heading?) 500)))
	(= temp2 (+ temp2 temp4))
	(= temp3 (+ temp3 temp5))
	(return (= temp1 (GetAngle (ego x?) (ego y?) temp2 temp3)))
)

(instance rm119 of Room
	(properties
		noun N_ROOM
		picture 25
		style (| BLACKOUT FADEOUT)
	)
	
	(method (init)
		(self
			setRegions: rgStarCon
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						18 15
						18 25
						9 25
						9 15
					yourself:
				)
		)
		(LoadMany RES_VIEW 129 136 130 139 138 146)
		(LoadMany RES_SOUND 9 11 133 10 136 137 18)
		(NormalEgo 129)
		(ego init:)
		(OverheadEgo)
		(switch prevRoomNum
			(117 (ego posn: -10 10))
			(else  (ego posn: 330 170))
		)
		(super init:)
		(if (== ((inventory at: iSafetyCones) owner?) curRoomNum)
			(cone1 init:)
			(cone2 init:)
			(cone3 init:)
			(= conesOnFloor TRUE)
		)
		(if (Btst 122)
			(= scrubberState 0)
			(disk init:)
			(if (Btst 123)
				(= scrubberState 1)
				(disk setCel: (disk lastCel:))
			)
			(curRoom
				addObstacle:
					((= scrubberPoly (Polygon new:))
						type: PBarredAccess
						init:
							88 84
							108 84
							113 97
							113 107
							107 112
							95 112
							86 104
							86 96
						yourself:
					)
			)
		)
		(if (not (Btst fCleanedCrest))
			(p1 init:)
		)
		(walkHandler addToFront: self)
		(self setScript: enterRoom)
	)
	
	(method (doit &tmp temp0 temp1)
		(super doit: &rest)
		(cond 
			(script 0)
			(
				(and
					(not (InRect -20 -20 350 210 disk))
					(!= ((ego mover?) x?) 160)
					(!= ((ego mover?) y?) 90)
				)
				(ego setMotion: GravMover 160 90)
			)
			(
				(and
					(!= (= temp0 (localproc_047b)) -1)
					(== scrubberState 2)
				)
				(if (!= theTheCone1 cone)
					(= theTheCone1 cone)
					(if (< temp0 -1)
						(switch cone
							(2 (localproc_03e7 1 0))
							(3 (localproc_03e7 0 0))
						)
					else
						(ego setMotion: GravMover temp0)
					)
				)
			)
			((and (== temp0 -1) (== scrubberState 2))
				(= theTheCone1 0)
				(= cone 0)
			)
			((and (not (InRect 30 5 300 185 ego)) (!= scrubberState 2))
				(if
					(and
						(or
							(== ((inventory at: iFloorScrubber) owner?) ego)
							(== ((inventory at: iFloorScrubber) owner?) curRoomNum)
						)
						(or
							(== ((inventory at: iSafetyCones) owner?) ego)
							(== ((inventory at: iSafetyCones) owner?) curRoomNum)
						)
						(not (Btst fCleanedCrest))
					)
					(self setScript: bounceBack)
				else
					(if (not (Btst fCleanedCrest))
						(starCon setScript: (ScriptID 109 2))
					)
					(self setScript: exitRoom)
				)
			)
		)
	)
	
	(method (dispose)
		(walkHandler delete: self)
		(if (ego looper?) ((ego looper?) dispose:))
		(ap setMotion: 0)
		(ego looper: 0)
		(ego setMotion: 0)
		(ego setCycle: 0)
		(if (IsObject scrubberPoly)
			((curRoom obstacles?) delete: scrubberPoly)
			(scrubberPoly dispose:)
		)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(V_SAFETY_CONES
					(if (== scrubberState 2)
						(messager say: N_CONES NULL C_ON_SCRUBBER 0)
					else
						(curRoom setScript: sSetCones)
					)
				)
				(V_SCRUBBER
					(if (Btst fCleanedCrest)
						(messager say: N_DISK NULL C_CLEAN 0)
					else
						(self setScript: placeScrubber)
						(return 1)
					)
				)
				(V_DO
					(cond 
						(
							(and
								(not conesOnFloor)
								(cast contains: disk)
								(== scrubberState 2)
								(> (ego distanceTo: ap) 75)
							)
							(curRoom setScript: getOffScrubber)
						)
						((and conesOnFloor (cast contains: disk))
							(messager say: N_CREST V_DO C_NOT_YET 0)
						)
						((Btst fCleanedCrest)
							(messager say: N_CREST V_DO C_CLEAN 0)
						)
						(else
							(messager say: N_CREST V_DO C_NOT_YET 0)
						)
					)
				)
				(V_WALK
					(if (not cone)
						(if (== scrubberState 2)
							(ego setMotion: GravMover mouseX mouseY)
						else
							(super doVerb: theVerb &rest)
						)
					)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance enterRoom of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					setLoop: (if (< (ego x?) 160) 4 else 1)
					setMotion: MoveTo 150 129 self
				)
			)
			(1
				(ego setLoop: -1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance exitRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(cond 
					((< (ego y?) 21) (= gEgoY (- (ego y?) 50)))
					((> (ego y?) 169) (= gEgoY (+ (ego y?) 50)))
					(else (= gEgoY (ego y?)))
				)
				(cond 
					((< (ego x?) 31) (= gEgoX (- (ego x?) 50)))
					((> (ego x?) 299) (= gEgoX (+ (ego x?) 50)))
					(else (= gEgoX (ego x?)))
				)
				(ego ignoreHorizon: TRUE)
				(= cycles 5)
			)
			(1
				(ego setMotion: MoveTo gEgoX gEgoY self)
			)
			(2
				(if (< (ego x?) 160)
					(curRoom newRoom: 117)
				else
					(curRoom newRoom: 115)
				)
			)
		)
	)
)

(instance bounceBack of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: N_ROOM NULL C_BOUNCE_BACK 0 self)
			)
			(1
				(= gEgoX (ego x?))
				(= gEgoY (ego y?))
				(cond 
					((< (ego y?) 20) (= gEgoY (+ (ego y?) 50)))
					((> (ego y?) 180) (= gEgoY (- (ego y?) 50)))
				)
				(cond 
					((< (ego x?) 31) (= gEgoX (+ (ego x?) 50)))
					((> (ego x?) 299) (= gEgoX (- (ego x?) 50)))
				)
				(= cycles 5)
			)
			(2
				(ego setMotion: PolyPath gEgoX gEgoY self)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance quirkYells of Script
	
	(method (dispose)
		(= theSeconds seconds)
		(super dispose: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(starCon setScript: 0)
				(if theSeconds
					(if (not conesOnFloor)
						(= seconds theSeconds)
					else
						(= seconds (* theSeconds 6))
					)
				)
			)
			(1
				(= seconds 15)
				(= local41 1)
			)
			(2
				(theMusic1 fade: 0 10 5 1)
				(theMusic2 fade: 0 10 5 1)
				(theGame handsOff:)
				(ego setMotion: MoveTo 160 100 self)
			)
			(3
				(if (ego looper?)
					((ego looper?) dispose:)
					(ego looper: 0)
				)
				(ego setCycle: 0 setCel: 10)
				(quirk
					init:
					posn: 250 -1
					setCycle: Walk
					setLoop: 0
					setPri: 14
					setMotion: MoveTo 221 96 self
				)
			)
			(4
				(quirk
					view: 132
					loop: 1
					cel: 0
					posn: 219 99
					setCycle: EndLoop self
				)
			)
			(5
				(= local28 -1)
				(messager say: N_QUIRK_YELLS NULL C_DIRTY 0 self)
			)
			(6
				(theMusic1 number: 18 loop: 1 play: self)
			)
			(7 (curRoom newRoom: 195))
		)
	)
)

(instance placeScrubber of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego put: iFloorScrubber curRoomNum)
				(ego setMotion: PolyPath 87 108 self)
			)
			(1 (ego setHeading: 0 self))
			(2
				(OverheadActor ego 130 6 5 83 114 14)
				(ego setCycle: BegLoop self)
			)
			(3
				(= scrubberState 0)
				(theMusic2 number: 416 loop: 1 play:)
				(disk init:)
				(ego setCycle: EndLoop self)
			)
			(4
				(OverheadEgo)
				(Bset fScrubberPlaced)
				(if (not (IsObject scrubberPoly))
					(curRoom
						addObstacle:
							((= scrubberPoly (Polygon new:))
								type: PBarredAccess
								init:
									88 84
									108 84
									113 97
									113 107
									107 112
									95 112
									86 104
									86 96
								yourself:
							)
					)
				)
				(ego setPri: 14 posn: 87 108)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance activateScrubber of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 87 108 self)
			)
			(1 (ego setHeading: 0 self))
			(2
				(OverheadActor ego 130 6 5 83 114 14)
				(ego setCycle: BegLoop self)
			)
			(3
				(theMusic2 number: 124 loop: 1 play:)
				(ego setCycle: EndLoop self)
			)
			(4
				(theMusic2 number: 127 loop: 1 play:)
				(disk setCycle: EndLoop self)
				(OverheadEgo)
				(ego posn: 87 108)
			)
			(5
				(Bset fScrubberOn)
				(= scrubberState 1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance getOnScrubber of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((curRoom obstacles?) delete: scrubberPoly)
				(ego setMotion: PolyPath 115 101 self)
			)
			(1 (ego setHeading: 270 self))
			(2
				(disk ignoreActors: TRUE setPri: 2)
				(OverheadActor ego 136 3 0 109 108 14)
				(ego setCycle: EndLoop self)
			)
			(3
				(p1 stopUpd:)
				(ego
					view: 136
					setStep: 5 5
					setPri: 3
					setLoop: 0
					setCycle: SpecialCycler
					cel: 0
					setAvoider: 0
					ignoreActors: 0
					posn: 100 100
				)
				(= scrubberState 2)
				(if (not local32)
					(= local32 1)
					(messager say: N_DISK V_DO C_GETTING_ON_SCRUBBER 0 self)
				else
					(= cycles 3)
				)
			)
			(4
				(= next 0)
				(if (not (Btst fIsVGA))
					(theGame setCursor: ARROW_CURSOR)
					(switch
						(Print
							font: userFont
							width: 200
							addText: N_EGA NULL NULL 1 0 0 119
							mode: teJustCenter
							addColorButton: 1 N_EGA NULL NULL 2 0 85 119 0 15 23 5 5 5
							addColorButton: 2 N_EGA NULL NULL 3 0 100 119 0 15 23 5 5 5
							init:
						)
						(1
							(Bset fCleanedCrest)
							(= next itsClean)
						)
					)
				)
				(= cycles 2)
			)
			(5
				(theGame setCursor: INVIS_CURSOR)
				(theMusic1 number: 9 loop: -1 play:)
				(theMusic2 number: 132 loop: -1 play:)
				(ego setScript: quirkYells)
				(ego setMotion: GravMover 225)
				(if (not next)
					(theGame handsOn:)
					(theIconBar curIcon: (theIconBar at: ICON_WALK))
					(theGame setCursor: ((theIconBar curIcon?) cursor?))
					(if (not conesOnFloor)
						(ap init: 0)
					)
				)
				(self dispose:)
			)
		)
	)
)

(instance getOffScrubber of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== client curRoom) (theGame handsOff:))
				(theMusic1 number: 5 loop: -1 play:)
				(ego
					setMotion: 0
					setScript: 0
					setLoop: SpecialLooper
					setMotion: MoveTo 115 101 self
				)
			)
			(1
				(theMusic2 stop:)
				(= scrubberState 3)
				(disk setCycle: 0)
				(OverheadActor disk 136 2 10 100 100 2)
				(OverheadActor ego 136 3 6 109 108 14)
				(ego setCycle: BegLoop self)
			)
			(2
				(if (ego looper?) ((ego looper?) dispose:))
				(ego setCycle: 0)
				(OverheadEgo)
				(ego posn: 115 101 setMotion: PolyPath 87 108 self)
			)
			(3 (ego setHeading: 0 self))
			(4
				(OverheadActor ego 130 6 5 83 114 14)
				(ego setCycle: BegLoop self)
			)
			(5
				(theMusic2 number: 124 loop: 1 play:)
				(ego setCycle: EndLoop self)
			)
			(6
				(theMusic2 number: 127 loop: 1 play:)
				(disk setCycle: BegLoop self)
			)
			(7 (ego setCycle: BegLoop self))
			(8
				(disk dispose:)
				(Bclr fScrubberPlaced)
				(ego get: 1 setCycle: EndLoop self)
			)
			(9
				(OverheadEgo)
				(ego posn: 87 108)
				(if (== client curRoom) (theGame handsOn:))
				(self dispose:)
			)
		)
	)
)

(instance cleanSpot of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (& cleanSpotControl cBLUE)
					(p1 startUpd:)
				)
				(if (& cleanSpotControl cGREEN)
					(p2 startUpd:)
				)
				(if (& cleanSpotControl cCYAN)
					(p3 startUpd:)
				)
				(if (& cleanSpotControl cRED)
					(p4 startUpd:)
				)
				(= cycles 2)
			)
			(1
				(localproc_02bb)
				(= cycles 2)
			)
			(2
				(p1 stopUpd:)
				(if (localproc_0264)
					(Bset fCleanedCrest)
					(curRoom setScript: itsClean)
				)
				(= cycles 2)
			)
			(3 (self dispose:))
		)
	)
)

(instance itsClean of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (not conesOnFloor)
					(quirkYells seconds: 0 state: 1 cue:)
					(= local41 1)
					(self dispose:)
				else
					(p1 dispose:)
					(theMusic1 fade: 0 10 5)
					(ego
						setLoop: SpecialLooper
						setMotion: PolyPath 218 128 self
					)
				)
			)
			(1
				(SolvePuzzle f119CrestClean 50)
				(ego setHeading: 315)
				(twinkle init:)
				(= seconds 5)
			)
			(2
				(crestFeature init: setOnMeCheck: ftrControl cBLUE cGREEN cCYAN cRED)
				(= next sMeetQuirk)
				(self dispose:)
			)
		)
	)
)

(instance dirtyFloor of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (& dirtyFloorControl cBLUE)
					(p1 startUpd:)
				)
				(if (& dirtyFloorControl cGREEN)
					(p2 startUpd:)
				)
				(if (& dirtyFloorControl cCYAN)
					(p3 startUpd:)
				)
				(if (& dirtyFloorControl cRED)
					(p4 startUpd:)
				)
				(= cycles 2)
			)
			(1
				(Graph
					GFillRect
					(+ (ap lsTop?) 5)
					(+ (ap lsLeft?) 5)
					(+ (ap lsTop?) 10)
					(+ (ap lsLeft?) 8)
					2
					-1
					1
					-1
				)
				(Graph
					GFillRect
					(- (ap lsBottom?) 10)
					(- (ap lsRight?) 8)
					(- (ap lsBottom?) 5)
					(- (ap lsRight?) 5)
					2
					-1
					1
					-1
				)
				(Graph
					GFillRect
					(+ (ap lsTop?) 3)
					(+ (ap lsLeft?) 7)
					(+ (ap lsTop?) 6)
					(+ (ap lsLeft?) 12)
					2
					-1
					1
					-1
				)
				(Graph
					GFillRect
					(- (ap lsBottom?) 12)
					(- (ap lsRight?) 10)
					(- (ap lsBottom?) 7)
					(- (ap lsRight?) 3)
					2
					-1
					1
					-1
				)
				(= cycles 3)
			)
			(2
				(p1 stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance sSetCones of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setScript: 0)
				(= conesOnFloor TRUE)
				(= register cone1)
				(= cycles 1)
			)
			(1
				(ego
					setMotion: PolyPath (- (register x?) 11) (+ (register y?) 6) self
				)
			)
			(2 (ego setHeading: 0 self))
			(3
				(ego
					posn: (- (register x?) 15) (+ (register y?) 12)
					view: 130
					loop: 3
					cel: 0
					setCycle: EndLoop self
				)
			)
			(4
				(register init:)
				(theMusic2 number: 133 loop: -1 setVol: 64 play:)
				(ego loop: 6 cel: 0 setCycle: EndLoop self)
			)
			(5
				(OverheadEgo)
				(ego posn: (- (register x?) 11) (+ (register y?) 6))
				(switch register
					(cone1
						(= register cone2)
						(= state (- state 5))
					)
					(cone2
						(= register cone3)
						(= state (- state 5))
					)
				)
				(= cycles 1)
			)
			(6
				(ego setMotion: MoveTo 98 116 self)
			)
			(7
				(ego put: iSafetyCones curRoomNum)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sMeetQuirk of Script
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setScript: 0)
				(LoadMany RES_VIEW 132 133 134 132 131)
				(LoadMany RES_PIC 26 27)
				(theMusic2 fade: 0 10 5 1)
				(ap ignoreActors: TRUE)
				(p1 dispose:)
				(twinkle dispose:)
				(quirk
					init:
					posn: 250 -1
					setCycle: Walk
					setLoop: 0
					setPri: 14
					setMotion: MoveTo 211 106 self
				)
				(bea
					init:
					posn: 273 4
					setLoop: 0
					setCycle: Walk
					setPri: 14
					setMotion: MoveTo 248 116 self
				)
			)
			(1)
			(2
				(theMusic2 stop:)
				(quirk
					view: 132
					loop: 1
					cel: 0
					posn: 209 109
					setCycle: EndLoop self
				)
				(bea setCel: 6)
				(ego setHeading: 45)
			)
			(3
				(cast eachElementDo: #startUpd)
				(if (ego looper?) ((ego looper?) dispose:))
				(= cycles 2)
			)
			(4
				(theMusic1 number: 10 loop: -1 play:)
				(cast eachElementDo: #hide)
				(curRoom drawPic: 26 -32759)
				(OverheadActor quirk 133 0 0 39 81 14)
				(quirk show: addToPic:)
				(OverheadActor bea 133 8 0 200 99 14)
				(bea show: addToPic:)
				(OverheadActor quirkHead 133 1 0 94 38 2)
				(quirkHead init:)
				(OverheadActor extra1 133 6 1 148 87 -1)
				(extra1 init:)
				(OverheadActor extra2 133 7 0 170 125 -1)
				(extra2 init:)
				(OverheadActor beaHead 133 9 1 220 60 -1)
				(beaHead init:)
				(= cycles 10)
			)
			(5 (messager say: N_MEET_QUIRK NULL NULL 1 self))
			(6 (messager say: N_MEET_QUIRK NULL NULL 2 self))
			(7
				(OverheadActor extra1 133 6 0 148 87 -1)
				(extra1 init:)
				(extra2 dispose:)
				(quirkTalker cel: 1)
				(quirkEyes loop: 4 nsLeft: 15 nsTop: 16)
				(quirkMouth loop: 2 nsLeft: 5 nsTop: 24)
				(= cycles 4)
			)
			(8 (messager say: N_MEET_QUIRK NULL NULL 3 self))
			(9
				(OverheadActor quirkHead 133 1 1 94 38 2)
				(quirkHead init:)
				(messager say: N_MEET_QUIRK NULL NULL 4 self)
			)
			(10
				(OverheadActor beaHead 133 9 0 220 60 -1)
				(beaHead init:)
				(= seconds 2)
			)
			(11
				(quirkTalker talkWidth: 0)
				(messager say: N_MEET_QUIRK NULL NULL 5 self)
			)
			(12
				(quirkTalker talkWidth: 280)
				(beaTalker cel: 0)
				(beaEyes loop: 13 nsLeft: 6 nsTop: 18)
				(beaMouth loop: 11 nsLeft: 7 nsTop: 28)
				(messager say: N_MEET_QUIRK NULL NULL 6 self)
			)
			(13
				(theMusic1 fade: 0 5 5 1 self)
			)
			(14
				(theMusic1 number: 11 loop: -1 play: 0 fade: 127 5 5 0)
				(curRoom drawPic: 27 3)
				(OverheadActor bea 134 0 0 82 34 1)
				(bea addToPic:)
				(quirk dispose:)
				(quirkHead dispose:)
				(beaHead dispose:)
				(extra2 dispose:)
				(extra1 dispose:)
				(= seconds 3)
			)
			(15
				(OverheadActor extra1 134 1 0 128 90 14)
				(extra1 init:)
				(extra1 show:)
				(= temp0 0)
				(while (< temp0 4)
					(= [local16 temp0]
						((Prop new:)
							view: 134
							loop: 3
							cel: (Random 0 10)
							signal: 16400
							init:
							setCycle: Forward
							yourself:
						)
					)
					(++ temp0)
				)
				([local16 0] posn: 223 48)
				([local16 1] posn: 229 101)
				([local16 2] posn: 114 44)
				([local16 3] posn: 102 130)
				(= seconds 2)
			)
			(16
				(extra1 hide:)
				(OverheadActor extra2 134 2 0 144 98 15)
				(extra2 init: setCycle: EndLoop self)
			)
			(17 (extra2 setCycle: BegLoop self))
			(18
				(extra2 dispose:)
				(= seconds 2)
			)
			(19
				(extra1 show:)
				(= temp0 4)
				(while (< temp0 8)
					(= [local16 temp0]
						((Prop new:)
							view: 134
							loop: 3
							cel: (Random 0 10)
							signal: 16400
							init:
							setCycle: Forward
							yourself:
						)
					)
					(++ temp0)
				)
				([local16 4] posn: 202 134)
				([local16 5] posn: 232 113)
				([local16 6] posn: 93 87)
				([local16 7] posn: 80 112)
				(= seconds 2)
			)
			(20
				(extra1 hide:)
				(= temp0 8)
				(while (< temp0 10)
					(= [local16 temp0]
						((Prop new:)
							view: 134
							loop: 3
							cel: (Random 0 10)
							signal: 16400
							init:
							setCycle: Forward
							yourself:
						)
					)
					(++ temp0)
				)
				([local16 8] posn: 241 68)
				([local16 9] posn: 84 64)
				(= seconds 2)
			)
			(21
				(extra1 dispose:)
				(= cycles 3)
			)
			(22
				(messager say: N_MEET_QUIRK NULL NULL 7 self)
			)
			(23
				(messager say: N_MEET_QUIRK NULL NULL 8 self)
			)
			(24
				(messager say: N_MEET_QUIRK NULL NULL 9 self)
			)
			(25
				(theMusic1 fade: 0 10 5 1 self)
			)
			(26
				(= temp0 0)
				(while (< temp0 10)
					([local16 temp0] dispose:)
					(++ temp0)
				)
				(bea dispose:)
				(= cycles 1)
			)
			(27
				(theMusic1 number: 10 loop: -1 play:)
				(curRoom drawPic: 26 9)
				(OverheadActor quirk 133 0 0 39 81 -1)
				(quirk addToPic:)
				(OverheadActor bea 133 8 0 200 99 -1)
				(bea addToPic:)
				(OverheadActor quirkHead 133 1 0 94 38 2)
				(quirkHead init:)
				(OverheadActor extra1 133 6 1 148 87 -1)
				(extra1 init:)
				(OverheadActor extra2 133 7 0 170 125 -1)
				(extra2 init:)
				(OverheadActor beaHead 133 9 1 220 60 -1)
				(beaHead init:)
				(= seconds 2)
			)
			(28
				(messager say: N_MEET_QUIRK NULL NULL 10 self)
			)
			(29
				(messager say: N_MEET_QUIRK NULL NULL 11 self)
			)
			(30
				(extra1 setCel: 1)
				(extra2 setCel: 1)
				(quirkTalker talkWidth: 0)
				(messager say: N_MEET_QUIRK NULL NULL 12 self)
			)
			(31 (= seconds 3))
			(32
				(quirkHead dispose:)
				(beaHead dispose:)
				(extra1 dispose:)
				(extra2 dispose:)
				(curRoom drawPic: 27 3)
				(OverheadActor quirk 134 4 0 97 62 -1)
				(quirk addToPic:)
				(= local28 1)
				(quirkTalker loop: 14 x: 146 y: 119)
				(quirkEyes loop: 14 nsLeft: 0 nsRight: 0)
				(quirkMouth view: 134 loop: 5 nsLeft: 0 nsTop: 0)
				(= seconds 2)
			)
			(33
				(messager say: N_MEET_QUIRK NULL NULL 13 self)
			)
			(34
				(messager say: N_MEET_QUIRK NULL NULL 14 self)
			)
			(35
				(messager say: N_MEET_QUIRK NULL NULL 18 self)
			)
			(36
				(quirkTalker talkWidth: 280)
				(curRoom drawPic: 25 -32761)
				(cone1 show:)
				(cone2 show:)
				(cone3 show:)
				(ego show:)
				(disk show:)
				(OverheadActor quirk 132 2 0 209 109 15)
				(quirk signal: 16400 init:)
				(OverheadActor bea 131 0 6 248 116 15)
				(bea signal: 16400 init:)
				(= seconds 2)
			)
			(37
				(theMusic1 stop:)
				(theMusic2 number: 152 loop: 1 play:)
				(quirk setCycle: EndLoop self)
			)
			(38
				(theMusic2 number: 136 loop: 1 play: self)
			)
			(39
				(theMusic1 number: 12 loop: -1 play:)
				(cast eachElementDo: #hide)
				(curRoom drawPic: 26 -32761)
				(OverheadActor quirk 135 1 0 263 88 -1)
				(quirk addToPic:)
				(OverheadActor bea 135 0 0 67 159 -1)
				(bea addToPic:)
				(OverheadActor extra1 135 4 0 228 142 -1)
				(extra1 init: ignoreActors: 1 setLoop: 4)
				(OverheadActor extra2 135 2 0 263 88 -1)
				(extra2 init: ignoreActors: 1 setLoop: 2)
				(OverheadActor extra3 135 3 0 262 94 -1)
				(extra3 init: ignoreActors: 1 setLoop: 3)
				(beaHead dispose:)
				(quirkHead dispose:)
				(= local28 2)
				(= seconds 2)
			)
			(40
				(rogTalker talkWidth: 90)
				(messager say: N_MEET_QUIRK NULL NULL 19 self)
			)
			(41
				(messager say: N_MEET_QUIRK NULL NULL 15 self)
			)
			(42
				(rogTalker talkWidth: 180)
				(extra1 setCycle: EndLoop self)
				(extra3 setCycle: EndLoop self)
			)
			(43)
			(44
				(extra1 stopUpd:)
				(extra3 dispose:)
				(extra2 setCycle: EndLoop self)
			)
			(45
				(extra2 stopUpd:)
				(quirkTalker view: 135 loop: 6 cel: 0 x: 225 y: 130)
				(quirkEyes view: 135 loop: 6 nsLeft: 0 nsTop: 0)
				(quirkMouth view: 135 loop: 5 nsLeft: -11 nsTop: -11)
				(= cycles 4)
			)
			(46
				(messager say: N_MEET_QUIRK NULL NULL 16 self)
			)
			(47
				(messager say: N_MEET_QUIRK NULL NULL 17 self)
			)
			(48
				(theMusic1 number: 10 loop: -1 play:)
				(curRoom drawPic: 25 -32761)
				(OverheadActor bea 131 1 0 246 110 15)
				(bea show: signal: 16400 init:)
				(OverheadActor quirk 132 3 0 207 91 15)
				(quirk show: signal: 16400 init:)
				(extra1 dispose:)
				(extra2 dispose:)
				(extra3 dispose:)
				(beaHead dispose:)
				(quirkHead dispose:)
				(cone1 show:)
				(cone2 show:)
				(cone3 show:)
				(ego show:)
				(disk show:)
				(= cycles 2)
			)
			(49 (bea setCycle: EndLoop self))
			(50
				(bea
					setLoop: 2
					cel: 0
					setCycle: Walk
					ignoreHorizon: 1
					setMotion: MoveTo 150 4
				)
				(theMusic2 number: 132 loop: -1 play:)
				(quirk posn: 207 91 setCycle: EndLoop self)
				(theMusic1 fade: 0 10 5 1)
			)
			(51 (= seconds 1))
			(52
				(quirk
					setLoop: 4
					cel: 0
					posn: 207 91
					setCycle: Walk
					ignoreHorizon: 1
					setMotion: MoveTo 97 -10 self
				)
			)
			(53
				(theMusic1 number: 5 loop: -1 play: 0 fade: 127 10 5 0)
				(bea hide:)
				(quirk hide:)
				(= cycles 2)
			)
			(54
				(bea dispose: delete:)
				(cast eachElementDo: #stopUpd)
				(ego startUpd:)
				(quirk dispose: delete:)
				(self setScript: getOffScrubber self)
			)
			(55
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance disk of Prop
	(properties
		x 100
		y 100
		noun N_DISK
		view 136
		loop 2
		priority 2
		signal (| fixedLoop fixPriOn)
		cycleSpeed 1
	)
	
	(method (doit)
		(if (== scrubberState 2)
			(self setLoop: 1 x: (ego x?) y: (ego y?))
			(if (not cycler) (self setCycle: Forward))
			(if
				(and
					(not (Btst fCleanedCrest))
					(&
						(= cleanSpotControl (OnControl cGREEN nsLeft nsTop nsRight nsBottom))
						$001e
					)
					(& (OnControl cBLUE nsLeft nsTop nsRight nsBottom) $0002)
					(not script)
					(not (quirkYells state?))
				)
				(self setScript: cleanSpot)
			)
		)
		(super doit: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(switch scrubberState
					(0
						(curRoom setScript: activateScrubber)
					)
					(1
						(curRoom setScript: getOnScrubber)
					)
					(2
						(cond 
							(conesOnFloor
								(messager say: N_DISK V_DO C_CONES_SET 0 self)
							)
							((>= (ego distanceTo: ap) 75)
								(curRoom setScript: getOffScrubber)
							)
							(else
								(super doVerb: theVerb &rest)
							)
						)
					)
				)
			)
			(1
				(switch scrubberState
					(0
						(messager say: noun V_LOOK C_FOLDED 0)
					)
					(1
						(messager say: noun V_LOOK C_READY 0)
					)
					(else 
						(super doVerb: theVerb &rest)
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(class CrestPiece of View
	(properties
		noun N_CREST
		priority 1
		underBits 0
		signal (| ignrAct skipCheck fixPriOn)
		case C_NOT_YET
	)
	
	(method (init)
		(super init: &rest)
		(self stopUpd:)
	)
	
	(method (handleEvent event)
		(return
			(cond 
				((event claimed?) (return TRUE))
				(
					(and
						(& (event type?) userEvent)
						(self onMe: event)
						(self isNotHidden:)
						(OneOf (event message?) V_SCRUBBER V_DO)
					)
					(self doVerb: (event message?))
					(return TRUE)
				)
				(else (super handleEvent: event))
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(crestFeature doVerb: theVerb)
			)
			(V_LOOK
				(messager say: noun theVerb case 0)
			)
			(else 
				(curRoom doVerb: theVerb &rest)
			)
		)
	)
)

(instance p1 of CrestPiece
	(properties
		x 71
		y 18
	)
	
	(method (init)
		(super init: &rest)
		(p2 init:)
		(p3 init:)
		(p4 init:)
	)
	
	(method (dispose)
		(p2 dispose:)
		(p3 dispose:)
		(p4 dispose:)
		(super dispose: &rest)
	)
	
	(method (stopUpd)
		(p2 stopUpd:)
		(p3 stopUpd:)
		(p4 stopUpd:)
		(super stopUpd:)
	)
)

(instance p2 of CrestPiece
	(properties
		x 84
		y 57
		cel 1
	)
)

(instance p3 of CrestPiece
	(properties
		x 171
		y 57
		cel 2
	)
)

(instance p4 of CrestPiece
	(properties
		x 100
		y 107
		cel 3
	)
)

(class MyCones of Prop
	(properties
		noun N_CONES
		view 138
		priority 2
		signal (| ignrAct fixPriOn)
	)
	
	(method (init)
		(super init: &rest)
		(if (== ((inventory at: iSafetyCones) owner?) curRoomNum)
			(self cue:)
		else
			(self setCycle: ForwardCounter 1 self)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(if (Btst fCleanedCrest)
					(messager say: noun theVerb C_CREST_PIECE 0)
				else
					(messager say: noun theVerb C_DIRTY 0)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(self setLoop: 1 cel: 0 stopUpd:)
		(if (!= (ego view?) 136) (theMusic2 stop:))
	)
)

(instance cone1 of MyCones
	(properties
		x 58
		y 23
	)
	
	(method (init)
		(super init: &rest)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						69 14
						69 34
						47 34
						47 14
					yourself:
				)
		)
	)
)

(instance cone2 of MyCones
	(properties
		x 264
		y 67
	)
	
	(method (init)
		(super init: &rest)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						250 58
						276 58
						276 79
						250 79
					yourself:
				)
		)
	)
)

(instance cone3 of MyCones
	(properties
		x 144
		y 154
	)
	
	(method (init)
		(super init: &rest)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						128 146
						158 146
						158 165
						128 165
					yourself:
				)
		)
	)
)

(instance twinkle of Prop
	(properties
		view 138
		loop 3
		priority 10
		signal (| ignrAct fixPriOn)
	)
	
	(method (init)
		(super init: &rest)
		(self
			cel: 0
			posn: (Random 72 253) (Random 19 100)
			setCycle: EndLoop self
		)
	)
	
	(method (cue)
		(self init:)
	)
)

(instance extra1 of Actor
	(properties
		view 134
	)
)

(instance extra2 of Actor
	(properties
		view 134
	)
)

(instance extra3 of Actor
	(properties
		view 134
	)
)

(instance quirkHead of Prop
	(properties
		view 133
	)
)

(instance beaHead of Prop
	(properties
		view 133
	)
)

(instance bea of Actor
	(properties
		view 131
	)
)

(instance quirk of Actor
	(properties
		view 132
	)
)

(instance ap of Actor
	(properties
		view 146
		priority 14
		signal (| ignrHrz fixPriOn)
	)
	
	(method (init param1 &tmp temp0 temp1)
		(super init: &rest)
		(self baseSetter: myBaseSetter)
		(= temp0 param1)
		(self setLoop: param1 setCycle: Walk)
		(if loop (= noun N_PERSON) else (= noun N_ALIEN))
		(= param1 (* param1 13))
		(if conesOnFloor
			(= param1 (+ param1 (* (Random 0 1) 4)))
			(self
				posn: [local43 param1] [local43 (+ param1 1)]
				setMotion: PolyPath [local43 (+ param1 2)] [local43 (+ param1 3)] self
			)
		else
			(= param1 (+ param1 8))
			(= temp1
				(Random [local43 param1] [local43 (+ param1 1)])
			)
			(if (< temp0 2)
				(self posn: -22 temp1 setMotion: PolyPath 320 temp1 self)
			else
				(self
					posn: temp1 [local43 (+ param1 3)]
					setMotion:
						PolyPath
						(+ temp1 [local43 (+ param1 2)])
						[local43 (+ param1 4)]
						self
				)
			)
		)
	)
	
	(method (doit)
		(super doit: &rest)
		(if
			(and
				(not conesOnFloor)
				(not script)
				(& (OnControl 2 x y) $0004)
			)
			(= dirtyFloorControl (OnControl 4 nsLeft nsTop nsRight nsBottom))
			(self setScript: dirtyFloor)
		)
	)
	
	(method (cue)
		(if
			(or
				(== (ego view?) 129)
				(== (curRoom script?) sMeetQuirk)
				(cast contains: quirk)
				local41
			)
			(self dispose:)
		else
			(self init: (= local39 (mod (++ local39) 8)))
		)
	)
)

(instance quirkMouth of Prop
	(properties
		nsTop 25
		view 133
		loop 3
		signal ignrAct
	)
)

(instance quirkEyes of Prop
	(properties
		nsTop 15
		nsLeft 5
		view 133
		loop 5
		signal ignrAct
	)
)

(instance quirkTalker of Talker
	(properties
		x 94
		y 38
		view 133
		loop 1
		talkWidth 280
		textX -70
		textY -30
	)
	
	(method (init)
		(= font userFont)
		(= systemWindow theSpeakWindow)
		(switch local28
			(1
				(systemWindow tailX: 92 tailY: 64 xOffset: -3 isBottom: 1)
			)
			(0
				(systemWindow
					tailX: 132
					tailY: (FindLanguage 38 38 38 38 35)
					xOffset: 40
					isBottom: 1
				)
			)
			(2
				(self talkWidth: 200 textX: -40)
				(systemWindow
					tailX: 160
					tailY: (FindLanguage 80 80 80 80 65)
					xOffset: -30
					isBottom: 1
				)
			)
			(-1
				(self loop: 4 cel: 1 talkWidth: 100)
				(systemWindow
					tailX: (quirk x?)
					tailY: (- (quirk y?) 40)
					xOffset: 0
				)
			)
		)
		(if (>= local28 0)
			(super init: 0 quirkEyes quirkMouth &rest)
		else
			(super init: 0 &rest)
		)
	)
	
	(method (dispose)
		(= systemWindow gSq5Win_2)
		(super dispose: &rest)
	)
)

(instance beaMouth of Prop
	(properties
		nsTop 24
		nsLeft 2
		view 133
		loop 10
		signal ignrAct
	)
)

(instance beaEyes of Prop
	(properties
		nsTop 13
		view 133
		loop 15
		signal ignrAct
	)
)

(instance beaTalker of Talker
	(properties
		x 220
		y 60
		view 133
		loop 9
		cel 1
		talkWidth 200
		textX -160
		textY -40
	)
	
	(method (init)
		(= font userFont)
		(= systemWindow theSpeakWindow)
		(if (== local28 2)
			(systemWindow
				tailX: 100
				tailY: 40
				xOffset: 30
				isBottom: 1
			)
			(self cel: 0 loop: 14 talkWidth: 0)
			(super init: 0 0 0 &rest)
		else
			(systemWindow
				tailX: 210
				tailY: 55
				xOffset: -60
				isBottom: 1
			)
			(= talkWidth 200)
			(super init: 0 beaEyes beaMouth &rest)
		)
	)
	
	(method (dispose)
		(= systemWindow gSq5Win_2)
		(super dispose: &rest)
	)
)

(instance rogTalker of Narrator
	(properties
		x 20
		y 160
		talkWidth 180
	)
	
	(method (init)
		(= font userFont)
		(= systemWindow theSpeakWindow)
		(if (== (curRoom script?) sMeetQuirk)
			(systemWindow
				tailX: 110
				tailY: 170
				xOffset: 0
				isBottom: 1
			)
		else
			(systemWindow
				tailX: (ego x?)
				tailY: (+ (ego y?) 15)
				xOffset: 0
				isBottom: 0
			)
		)
		(super init: &rest)
	)
	
	(method (dispose)
		(= systemWindow gSq5Win_2)
		(super dispose: &rest)
	)
)

(instance quirkTalker2 of Narrator
	(properties
		talkWidth 100
	)
	
	(method (init)
		(= font userFont)
		(= systemWindow theSpeakWindow)
		(systemWindow
			tailX: (- (quirk x?) 50)
			xOffset: -20
			tailY: (quirk y?)
		)
		(super init: &rest)
	)
	
	(method (dispose)
		(= systemWindow gSq5Win_2)
		(super dispose: &rest)
	)
)

(instance genTalker of Narrator
	(properties
		talkWidth 100
	)
	
	(method (init)
		(= font userFont)
		(= systemWindow gSq5Win_2)
		(super init: &rest)
	)
	
	(method (dispose)
		(= systemWindow gSq5Win_2)
		(super dispose: &rest)
	)
)

(instance myBaseSetter of Code
	
	(method (doit theActor &tmp temp0 temp1 temp2 temp3)
		(= temp0 (- (theActor nsBottom?) (theActor nsTop?)))
		(= temp1 (- (theActor nsRight?) (theActor nsLeft?)))
		(= temp2 (- (theActor x?) (/ temp1 2)))
		(= temp3 (- (theActor y?) (/ temp0 2)))
		(theActor
			brLeft: temp2
			brRight: (+ temp2 temp1)
			brTop: temp3
			brBottom: (+ temp3 temp0)
		)
	)
)

(instance crestFeature of Feature
	(properties
		x 160
		y 100
		noun N_CREST
		onMeCheck $001e
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(if (Btst fCleanedCrest)
					(messager say: noun theVerb C_CLEAN 0)
				else
					(curRoom doVerb: theVerb &rest)
				)
			)
			(V_LOOK
				(if (Btst fCleanedCrest)
					(messager say: noun theVerb C_CLEAN 0)
				else
					(messager say: noun theVerb (p1 case?) 0)
				)
			)
			(else 
				(curRoom doVerb: theVerb &rest)
			)
		)
	)
)
