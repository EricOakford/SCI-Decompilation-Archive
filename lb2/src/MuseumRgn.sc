;;; Sierra Script 1.0 - (do not remove this comment)
(script# 90)
(include sci.sh)
(use Main)
(use n027)
(use Scaler)
(use PolyPath)
(use StopWalk)
(use Timer)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	MuseumRgn 0
	aCountess 1
	aOlympia 2
	aORiley 3
	aTut 4
	aWatney 5
	aYvette 6
	aZiggy 7
	fumeTimer 13
	meetingTimer 15
)

(local
	[local0 100] = [350 632 4 0 1 360 0 638 0 0 370 0 0 0 635 420 7 -1 0 624 500 15 0 608 0 510 512 128 64 31 520 0 0 575 0 530 -1 0 127 0 430 32 -1 0 94 440 28 33 64 0 448 24 0 99 0 450 0 0 103 16 454 0 111 0 0 480 95 0 0 0 490 63 0 0 0 550 0 0 639 0 600 2 12 -1 0 610 8 0 3 0 630 0 7 0 0 650 0 0 13]
	[theTheOriginalDest 120] = [350 2 420 370 0 360 360 1 0 350 0 0 370 4 0 0 0 350 420 8 350 430 0 500 500 16 420 0 510 0 510 32 530 550 520 500 520 64 0 0 510 0 530 512 600 0 510 0 430 1 480 420 0 440 440 2 448 430 490 0 448 4 450 0 440 0 450 8 0 0 448 454 454 16 0 450 0 0 480 32 430 0 0 0 490 64 0 0 440 0 550 128 0 0 510 0 600 1 650 610 530 0 610 4 630 0 600 0 630 8 0 610 0 0 650 2 0 0 600]
	[local220 40] = [350 0 360 0 370 0 420 0 500 0 510 0 520 0 530 0 430 0 440 0 448 0 450 0 454 0 480 0 490 0 550 0 600 0 610 0 630 0 650]
)
(procedure (localproc_0202 param1 param2 &tmp temp0 temp1 temp2)
	(= temp1
		(switch param1
			(1 5)
			(2 6)
			(3 2)
		))
	(= temp0 0)
	(return
		(while (< temp0 21)
			(if
				(==
					(= temp2
						(switch param1
							(1 [local0 (* temp0 temp1)])
							(2
								[theTheOriginalDest (* temp0 temp1)]
							)
							(3 [local220 (* temp0 temp1)])
						)
					)
					param2
				)
				(return (* temp0 temp1))
			)
			(++ temp0)
		)
	)
)

(class MuseumRgn of Rgn
	(properties
		script 0
		number 0
		modNum -1
		noun 0
		timer 0
		keep 0
		initialized 0
		northList 0
		northModule 0
		eastList 0
		eastModule 0
		southList 0
		southModule 0
		westList 0
		westModule 0
		northPts 0
		eastPts 0
		southPts 0
		westPts 0
		midPts 0
		legalX 0
		legalY 0
	)
	
	(method (init)
		(super init:)
		(self loadPolyList:)
		(if (not (Btst 6)) ((ScriptID 90 1) init:))
		((ScriptID 90 2) init:)
		((ScriptID 90 3) init:)
		((ScriptID 90 4) init:)
		(if (not (Btst 3)) ((ScriptID 90 5) init:))
		(if (not (Btst 5)) ((ScriptID 90 6) init:))
		(if (not (Btst 2)) ((ScriptID 90 7) init:))
		(keyDownHandler addToFront: self)
		(if (not (timers contains: wanderTimer))
			(wanderTimer setReal: wanderTimer 120)
		)
	)
	
	(method (dispose)
		(if (timers contains: wanderTimer)
			(wanderTimer dispose: delete:)
		)
		(super dispose:)
	)
	
	(method (newRoom n)
		(= keep
			(OneOf
				n
				335
				340
				350
				355
				360
				370
				400
				420
				500
				510
				520
				525
				530
				540
				550
				560
				565
				430
				435
				440
				448
				450
				454
				455
				456
				460
				480
				490
				521
				600
				610
				620
				630
				640
				650
				666
				660
				700
				710
				715
				720
				730
				740
			)
		)
		(= initialized 0)
		(keyDownHandler delete: self)
		(if (curRoom obstacles?)
			(DisposeScript (+ 2000 curRoomNum))
		)
		(if northList
			(northList dispose:)
			(= northList 0)
			(if (IsObject northPts) (northPts dispose:))
			(= northPts 0)
			(DisposeScript northModule)
		)
		(if eastList
			(eastList dispose:)
			(= eastList 0)
			(if (IsObject eastPts) (eastPts dispose:))
			(= eastPts 0)
			(DisposeScript eastModule)
		)
		(if southList
			(southList dispose:)
			(= southList 0)
			(if (IsObject southPts) (southPts dispose:))
			(= southPts 0)
			(DisposeScript southModule)
		)
		(if westList
			(westList dispose:)
			(= westList 0)
			(if (IsObject westPts) (westPts dispose:))
			(= westPts 0)
			(DisposeScript westModule)
		)
	)
	
	(method (crowdInRoom param1 &tmp temp0 temp1 temp2 temp3 temp4)
		(return
			(if (< argc 2)
				(= temp0 (localproc_0202 3 [param1 0]))
				(return [local220 (++ temp0)])
			else
				(= temp3 [param1 0])
				(= temp4 [param1 1])
				(= temp1 1)
				(while (and temp3 (<= temp1 argc))
					(if (> temp3 0)
						(= [local220 (+ temp0 1)]
							(+
								[local220 (+ (= temp0 (localproc_0202 3 temp3)) 1)]
								temp4
							)
						)
					)
					(= temp3 [param1 (++ temp1)])
					(= temp4 [param1 (++ temp1)])
				)
			)
		)
	)
	
	(method (findLegalPoint param1 param2 param3 &tmp temp0 temp1 temp2 temp3)
		(if
			(not
				(= temp1
					(switch param1
						((curRoom north?)
							(MuseumRgn northList?)
						)
						((curRoom east?)
							(MuseumRgn eastList?)
						)
						((curRoom south?)
							(MuseumRgn southList?)
						)
						((curRoom west?)
							(MuseumRgn westList?)
						)
						(curRoomNum
							(curRoom obstacles?)
						)
						(else  0)
					)
				)
			)
			(return 1)
		)
		(= temp0 1)
		(= temp3 0)
		(while (and temp0 (< temp3 (temp1 size?)))
			(if
				(or
					(and
						(== ((= temp2 (temp1 at: temp3)) type?) 3)
						(not (AvoidPath param2 param3 temp2))
					)
					(and
						(!= (temp2 type?) 3)
						(AvoidPath param2 param3 temp2)
					)
				)
				(= temp0 0)
			)
			(++ temp3)
		)
		(if (not temp0)
			(switch param1
				((curRoom north?)
					(= legalX ((MuseumRgn northPts?) midPtX?))
					(= legalY ((MuseumRgn northPts?) midPtY?))
				)
				((curRoom east?)
					(= legalX ((MuseumRgn eastPts?) midPtX?))
					(= legalY ((MuseumRgn eastPts?) midPtY?))
				)
				((curRoom south?)
					(= legalX ((MuseumRgn southPts?) midPtX?))
					(= legalY ((MuseumRgn southPts?) midPtY?))
				)
				((curRoom west?)
					(= legalX ((MuseumRgn westPts?) midPtX?))
					(= legalY ((MuseumRgn westPts?) midPtY?))
				)
				(curRoomNum
					(= legalX ((MuseumRgn midPts?) midPtX?))
					(= legalY ((MuseumRgn midPts?) midPtY?))
				)
			)
		)
		(return temp0)
	)
	
	(method (loadPolyList)
		(if (curRoom obstacles?)
			((curRoom obstacles?) dispose:)
		)
		(if northList (northList dispose:))
		(if eastList (eastList dispose:))
		(if southList (southList dispose:))
		(if westList (westList dispose:))
		(curRoom obstacles: (List new:))
		((ScriptID (+ 2000 curRoomNum) 0)
			doit: (curRoom obstacles?)
		)
		(if
			(OneOf
				(curRoom north?)
				335
				340
				350
				355
				360
				370
				400
				420
				500
				510
				520
				525
				530
				540
				550
				560
				565
				430
				435
				440
				448
				450
				454
				455
				456
				460
				480
				490
				521
				600
				610
				620
				630
				640
				650
				666
				660
				700
				710
				715
				720
				730
				740
			)
			(= northModule (+ 2000 (curRoom north?)))
			((ScriptID northModule 0)
				doit: (= northList (List new:))
			)
		)
		(if
			(OneOf
				(curRoom east?)
				335
				340
				350
				355
				360
				370
				400
				420
				500
				510
				520
				525
				530
				540
				550
				560
				565
				430
				435
				440
				448
				450
				454
				455
				456
				460
				480
				490
				521
				600
				610
				620
				630
				640
				650
				666
				660
				700
				710
				715
				720
				730
				740
			)
			(= eastModule (+ 2000 (curRoom east?)))
			((ScriptID eastModule 0)
				doit: (= eastList (List new:))
			)
		)
		(if
			(OneOf
				(curRoom south?)
				335
				340
				350
				355
				360
				370
				400
				420
				500
				510
				520
				525
				530
				540
				550
				560
				565
				430
				435
				440
				448
				450
				454
				455
				456
				460
				480
				490
				521
				600
				610
				620
				630
				640
				650
				666
				660
				700
				710
				715
				720
				730
				740
			)
			(= southModule (+ 2000 (curRoom south?)))
			((ScriptID southModule 0)
				doit: (= southList (List new:))
			)
		)
		(if
			(OneOf
				(curRoom west?)
				335
				340
				350
				355
				360
				370
				400
				420
				500
				510
				520
				525
				530
				540
				550
				560
				565
				430
				435
				440
				448
				450
				454
				455
				456
				460
				480
				490
				521
				600
				610
				620
				630
				640
				650
				666
				660
				700
				710
				715
				720
				730
				740
			)
			(= westModule (+ 2000 (curRoom west?)))
			((ScriptID westModule 0)
				doit: (= westList (List new:))
			)
		)
		(= midPts (ScriptID (+ 2000 curRoomNum) 1))
		(if
			(OneOf
				(curRoom north?)
				335
				340
				350
				355
				360
				370
				400
				420
				500
				510
				520
				525
				530
				540
				550
				560
				565
				430
				435
				440
				448
				450
				454
				455
				456
				460
				480
				490
				521
				600
				610
				620
				630
				640
				650
				666
				660
				700
				710
				715
				720
				730
				740
			)
			(= northPts (ScriptID northModule 1))
		)
		(if
			(OneOf
				(curRoom east?)
				335
				340
				350
				355
				360
				370
				400
				420
				500
				510
				520
				525
				530
				540
				550
				560
				565
				430
				435
				440
				448
				450
				454
				455
				456
				460
				480
				490
				521
				600
				610
				620
				630
				640
				650
				666
				660
				700
				710
				715
				720
				730
				740
			)
			(= eastPts (ScriptID eastModule 1))
		)
		(if
			(OneOf
				(curRoom south?)
				335
				340
				350
				355
				360
				370
				400
				420
				500
				510
				520
				525
				530
				540
				550
				560
				565
				430
				435
				440
				448
				450
				454
				455
				456
				460
				480
				490
				521
				600
				610
				620
				630
				640
				650
				666
				660
				700
				710
				715
				720
				730
				740
			)
			(= southPts (ScriptID southModule 1))
		)
		(if
			(OneOf
				(curRoom west?)
				335
				340
				350
				355
				360
				370
				400
				420
				500
				510
				520
				525
				530
				540
				550
				560
				565
				430
				435
				440
				448
				450
				454
				455
				456
				460
				480
				490
				521
				600
				610
				620
				630
				640
				650
				666
				660
				700
				710
				715
				720
				730
				740
			)
			(= westPts (ScriptID westModule 1))
		)
	)
	
	(method (nobodyAround)
		(return
			(if
				(OneOf
					curRoomNum
					((ScriptID 90 1) room?)
					((ScriptID 90 2) room?)
					((ScriptID 90 3) room?)
					((ScriptID 90 4) room?)
					((ScriptID 90 5) room?)
					((ScriptID 90 6) room?)
					((ScriptID 90 7) room?)
					((ScriptID 32 0) room?)
				)
				(messager say: 16 46 0 0 0 0)
				(return 0)
			else
				(return 1)
			)
		)
	)
	
	(method (whichList param1)
		(switch param1
			(curRoomNum
				(curRoom obstacles?)
			)
			((curRoom north?) northList)
			((curRoom east?) eastList)
			((curRoom south?) southList)
			((curRoom west?) westList)
		)
	)
)

(class MuseumActor of Actor
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 0
		modNum -1
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		sightAngle 26505
		actions 0
		onMeCheck $6789
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		yStep 2
		view -1
		loop 0
		cel 0
		priority 0
		underBits 0
		signal $0000
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		scaleSignal $0000
		scaleX 128
		scaleY 128
		maxScale 128
		cycleSpeed 6
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
		illegalBits $8000
		xLast 0
		yLast 0
		xStep 3
		origStep 770
		moveSpeed 6
		blocks 0
		baseSetter 0
		mover 0
		looper 0
		viewer 0
		avoider 0
		code 0
		originalView 0
		beelining 0
		wandering 0
		room 0
		lastRoom 0
		destination 0
		originalDest 0
		roomIdx 0
		exitX 0
		exitY 0
		destX 0
		destY 0
		waitCount 0
	)
	
	(method (init &tmp [temp0 2] temp2 temp3 temp4 temp5 temp6 [temp7 5])
		(self
			view: (if (== room curRoomNum) originalView else 828)
			ignoreActors:
			ignoreHorizon:
			illegalBits: 0
			setCycle: StopWalk -1
		)
		(if (ego scaler?)
			(= temp2 (/ (* 110 ((ego scaler?) frontSize?)) 100))
			(= temp3 ((ego scaler?) frontY?))
			(= temp4
				(/ (* 110 (+ 1 ((ego scaler?) backSize?))) 100)
			)
			(= temp5 ((ego scaler?) backY?))
			(self setScale: Scaler temp2 temp4 temp3 temp5)
		else
			(self setScale: -1 ego)
			(= maxScale (/ (* 110 maxScale) 100))
		)
		(super init: &rest)
		(cond 
			((not beelining)
				(cond 
					(wandering
						(cond 
							((== room curRoomNum) 0)
							((self isAdjacent:)
								(if (not (MuseumRgn findLegalPoint: room x y))
									(self posn: (MuseumRgn legalX?) (MuseumRgn legalY?))
								)
							)
							(else (self posn: 160 165))
						)
						(if (> (MuseumRgn crowdInRoom: room) 1)
							(= temp6 (- temp6 15))
						)
						(self wander: originalDest self)
					)
					((and (== room -1) (Random 0 1))
						(cond 
							((== room curRoomNum)
								(if (not (MuseumRgn findLegalPoint: room x y))
									(self posn: (MuseumRgn legalX?) (MuseumRgn legalY?))
								)
							)
							((self isAdjacent:)
								(if (not (MuseumRgn findLegalPoint: room x y))
									(self posn: (MuseumRgn legalX?) (MuseumRgn legalY?))
								)
							)
							(else 0)
						)
						(self wander:)
					)
				)
			)
			(originalDest
				(cond 
					((== room curRoomNum)
						(if (not (MuseumRgn findLegalPoint: room x y))
							(self posn: (MuseumRgn legalX?) (MuseumRgn legalY?))
						)
					)
					((self isAdjacent:)
						(if (not (MuseumRgn findLegalPoint: room x y))
							(self posn: (MuseumRgn legalX?) (MuseumRgn legalY?))
						)
					)
					(else 0)
				)
				(self goTo: originalDest self)
			)
			((== room destination) (= destination 0))
		)
	)
	
	(method (onMe)
		(return (if (== view 828) (return 0) else (super onMe: &rest)))
	)
	
	(method (dirToRoom param1 &tmp temp0 temp1)
		(= temp0 (self setBits: param1))
		(= temp1 (* roomIdx 5))
		(return
			(cond 
				(
					(and
						(& [local0 (++ temp1)] temp0)
						(> [local0 temp1] 0)
					)
					(return 1)
				)
				(
					(and
						(& [local0 (++ temp1)] temp0)
						(> [local0 temp1] 0)
					)
					(return 2)
				)
				(
					(and
						(& [local0 (++ temp1)] temp0)
						(> [local0 temp1] 0)
					)
					(return 3)
				)
				(
					(and
						(& [local0 (++ temp1)] temp0)
						(> [local0 temp1] 0)
					)
					(return 4)
				)
				(else (return 0))
			)
		)
	)
	
	(method (findRendezvous param1)
		(cond 
			(
				(OneOf
					room
					420
					500
					510
					520
					525
					530
					540
					550
					560
					565
					335
					340
					350
					355
					360
					370
					400
				)
				(cond 
					(
						(OneOf
							param1
							430
							435
							440
							448
							450
							454
							455
							456
							460
							480
							490
							521
						)
						(= destination 420)
					)
					((OneOf param1 600 610 620 630 640 650 666) (= destination 530))
				)
			)
			(
				(OneOf
					room
					430
					435
					440
					448
					450
					454
					455
					456
					460
					480
					490
					521
				)
				(if
					(not
						(OneOf
							param1
							430
							435
							440
							448
							450
							454
							455
							456
							460
							480
							490
							521
						)
					)
					(= destination 430)
				)
			)
			(
				(and
					(OneOf room 600 610 620 630 640 650 666)
					(not (OneOf param1 600 610 620 630 640 650 666))
				)
				(= destination 600)
			)
		)
		(return destination)
	)
	
	(method (goTo theOriginalDest param2 theDestX theDestY)
		(= beelining 1)
		(if (OneOf room -1 -2) (self moveTo: originalDest))
		(= destination (= originalDest theOriginalDest))
		(if (< argc 2) (= param2 0))
		(if (> argc 2)
			(= destX theDestX)
			(= destY theDestY)
		else
			(= destY (= destX -1))
		)
		(self setScript: (TravelToRoom new:) param2)
	)
	
	(method (isAdjacent)
		(switch room
			((curRoom north?) 1)
			((curRoom south?) 3)
			((curRoom east?) 2)
			((curRoom west?) 4)
			(else  0)
		)
	)
	
	(method (moveTo theRoom &tmp temp0 theRoom_2)
		(if (OneOf theRoom -1 -2)
			(if (MuseumRgn crowdInRoom: room)
				(MuseumRgn crowdInRoom: room -1)
			)
			(= originalDest room)
			(= room theRoom)
			(= beelining (= wandering 0))
			(self setScript: 0)
			(return)
		else
			(= wandering 0)
			(self setScript: 0)
			(= theRoom_2 room)
			(= room theRoom)
			(if
				(and
					(> theRoom_2 0)
					(!= theRoom_2 355)
					(!= theRoom 355)
				)
				(self setIndex: room)
				(= lastRoom
					(self nextRoom: (self dirToRoom: theRoom_2))
				)
				(MuseumRgn crowdInRoom: lastRoom -1)
			)
			(= view (if (== room curRoomNum) originalView else 828))
			(MuseumRgn crowdInRoom: room 1)
		)
	)
	
	(method (nextRoom param1)
		(return [theTheOriginalDest (+ (* roomIdx 6) param1 1)])
	)
	
	(method (setBits param1 &tmp temp0)
		(= temp0 (localproc_0202 2 param1))
		(return [theTheOriginalDest (++ temp0)])
	)
	
	(method (setIndex &tmp temp0)
		(= roomIdx (/ (= temp0 (localproc_0202 2 room)) 6))
	)
	
	(method (wander theTheOriginalDest_2 param2 theDestX theDestY &tmp theOriginalDest)
		(if (not argc)
			(while
				(==
					room
					(= theOriginalDest
						[theTheOriginalDest (* (Random 0 22) 6)]
					)
				)
				0
			)
		else
			(= theOriginalDest theTheOriginalDest_2)
		)
		(= beelining 0)
		(= wandering 1)
		(= destination (= originalDest theOriginalDest))
		(if (< argc 2) (= param2 0))
		(if (> argc 2)
			(= destX theDestX)
			(= destY theDestY)
		else
			(= destY (= destX -1))
		)
		(= waitCount 10)
		(self setScript: (TravelToRoom new:) param2)
	)
)

(instance aCountess of MuseumActor
	(properties
		noun 1
		modNum 1884
		origStep 514
		originalView 813
	)
	
	(method (doVerb theVerb theItem &tmp temp0 temp1 temp2)
		(if (OneOf theVerb 6 14)
			(if
				(==
					(= temp0
						(if (== argc 2)
							theItem
						else
							(curRoom setInset: (ScriptID 20 0))
						)
					)
					-1
				)
				(return)
			)
			(= temp2 (& temp0 $00ff))
			(= temp1
				(switch (& temp0 $ff00)
					(256 (+ temp2 1))
					(512 (+ temp2 18))
					(768 (+ temp2 26))
					(1024 (+ temp2 61))
				)
			)
			(switch temp0
				(258
					(cond 
						((Btst 134)
							(if (proc27_0 0 global364)
								(messager say: noun 6 1 0 0 modNum)
							else
								(messager say: noun 6 72 0 0 modNum)
								(proc27_1 0 @global364)
							)
						)
						((proc27_0 0 global297) (messager say: noun 6 1 0 0 modNum))
						(else
							(messager say: noun 6 3 0 0 modNum)
							(proc27_1 0 @global297)
						)
					)
				)
				(264
					(cond 
						((Btst 143)
							(if (proc27_0 0 global366)
								(messager say: noun 6 1 0 0 modNum)
							else
								(messager say: noun 6 74 0 0 modNum)
								(proc27_1 0 @global366)
							)
						)
						((proc27_0 0 global303) (messager say: noun 6 1 0 0 modNum))
						(else
							(messager say: noun 6 9 0 0 modNum)
							(proc27_1 0 @global303)
						)
					)
				)
				(267
					(cond 
						((Btst 158)
							(if (proc27_0 0 global365)
								(messager say: noun 6 1 0 0 modNum)
							else
								(messager say: noun 6 71 0 0 modNum)
								(proc27_1 0 @global365)
							)
						)
						((proc27_0 0 global306) (messager say: noun 6 1 0 0 modNum))
						(else
							(messager say: noun 6 12 0 0 modNum)
							(proc27_1 0 @global306)
						)
					)
				)
				(else 
					(cond 
						((not (Message msgGET modNum noun 6 temp1 1)) (messager say: noun 6 81 0 0 modNum))
						((proc27_0 0 [global296 (- temp1 2)]) (messager say: noun 6 1 0 0 modNum))
						(else
							(messager say: noun 6 temp1 0 0 modNum)
							(proc27_1 0 @[global296 (- temp1 2)])
						)
					)
				)
			)
		else
			(super doVerb: theVerb)
		)
	)
	
	(method (cue)
		(self wander:)
	)
	
	(method (wander param1 param2 &tmp temp0 temp1)
		(asm
			lap      argc
			not     
			bt       code_1394
			lap      param1
			not     
			bnt      code_1437
code_1394:
			pToa     room
			sat      temp1
code_1398:
			lst      temp1
			pToa     room
			eq?     
			bnt      code_13ef
			ldi      0
			sat      temp1
			pushi    2
			pushi    1
			pushi    100
			callk    Random,  4
			sat      temp0
			lsg      currentAct
			ldi      3
			eq?     
			bnt      code_13e1
			lst      temp0
			ldi      5
			le?     
			bnt      code_13c4
			ldi      450
			sat      temp1
			jmp      code_1398
code_13c4:
			lst      temp0
			ldi      10
			le?     
			bnt      code_13d3
			ldi      454
			sat      temp1
			jmp      code_1398
code_13d3:
			lst      temp0
			ldi      50
			le?     
			bnt      code_1398
			ldi      500
			sat      temp1
			jmp      code_1398
code_13e1:
			lst      temp0
			ldi      40
			le?     
			bnt      code_1398
			ldi      500
			sat      temp1
			jmp      code_1398
code_13ef:
			lat      temp1
			bnt      code_141d
			pTos     room
			ldi      65535
			eq?     
			bnt      code_1410
			pTos     originalDest
			ldi      0
			gt?     
			bnt      code_140c
			pushi    #moveTo
			pushi    1
			pTos     originalDest
			self     6
code_140c:
			ldi      0
			aTop     originalDest
code_1410:
			pushi    #wander
			pushi    2
			lst      temp1
			pushSelf
			super    MuseumActor,  8
			jmp      code_1442
code_141d:
			pTos     room
			ldi      65535
			ne?     
			bnt      code_1428
			pToa     room
			aTop     originalDest
code_1428:
			pushi    #moveTo
			pushi    1
			pushi    65535
			self     6
			ldi      0
			aTop     wandering
			jmp      code_1442
code_1437:
			pushi    #wander
			pushi    1
			lsp      param1
			&rest    param2
			super    MuseumActor,  6
code_1442:
			ret     
		)
	)
)

(instance aOlympia of MuseumActor
	(properties
		noun 1
		modNum 1892
		scaleSignal $0001
		originalView 820
	)
	
	(method (doVerb theVerb theItem &tmp temp0 temp1 temp2)
		(if (OneOf theVerb 6 14)
			(if
				(==
					(= temp0
						(if (== argc 2)
							theItem
						else
							(curRoom setInset: (ScriptID 20 0))
						)
					)
					-1
				)
				(return)
			)
			(= temp2 (& temp0 $00ff))
			(= temp1
				(switch (& temp0 $ff00)
					(256 (+ temp2 1))
					(512 (+ temp2 18))
					(768 (+ temp2 26))
					(1024 (+ temp2 61))
				)
			)
			(switch temp0
				(258
					(cond 
						((Btst 134)
							(if (proc27_0 3 global364)
								(messager say: noun 6 1 0 0 modNum)
							else
								(messager say: noun 6 72 0 0 modNum)
								(proc27_1 3 @global364)
							)
						)
						((proc27_0 3 global297) (messager say: noun 6 1 0 0 modNum))
						(else
							(messager say: noun 6 3 0 0 modNum)
							(proc27_1 3 @global297)
						)
					)
				)
				(259
					(cond 
						((Btst 171)
							(if (proc27_0 3 global363)
								(messager say: noun 6 1 0 0 modNum)
							else
								(messager say: noun 6 69 0 0 modNum)
								(proc27_1 3 @global363)
							)
						)
						((proc27_0 3 global298) (messager say: noun 6 1 0 0 modNum))
						(else
							(messager say: noun 6 4 0 0 modNum)
							(proc27_1 3 @global298)
						)
					)
				)
				(264
					(cond 
						((Btst 143)
							(if (proc27_0 3 global366)
								(messager say: noun 6 1 0 0 modNum)
							else
								(messager say: noun 6 74 0 0 modNum)
								(proc27_1 3 @global366)
							)
						)
						((proc27_0 3 global303) (messager say: noun 6 1 0 0 modNum))
						(else
							(messager say: noun 6 9 0 0 modNum)
							(proc27_1 3 @global303)
						)
					)
				)
				(266
					(cond 
						((Btst 161)
							(if (proc27_0 3 global367)
								(messager say: noun 6 1 0 0 modNum)
							else
								(messager say: noun 6 73 0 0 modNum)
								(proc27_1 3 @global367)
							)
						)
						((proc27_0 3 global305) (messager say: noun 6 1 0 0 modNum))
						(else
							(messager say: noun 6 11 0 0 modNum)
							(proc27_1 3 @global305)
						)
					)
				)
				(267
					(cond 
						((Btst 158)
							(if (proc27_0 3 global365)
								(messager say: noun 6 1 0 0 modNum)
							else
								(messager say: noun 6 71 0 0 modNum)
								(proc27_1 3 @global365)
							)
						)
						((proc27_0 3 global306) (messager say: noun 6 1 0 0 modNum))
						(else
							(messager say: noun 6 12 0 0 modNum)
							(proc27_1 3 @global306)
						)
					)
				)
				(else 
					(cond 
						((not (Message msgGET modNum noun 6 temp1 1)) (messager say: noun 6 81 0 0 modNum))
						((proc27_0 3 [global296 (- temp1 2)]) (messager say: noun 6 1 0 0 modNum))
						(else
							(messager say: noun 6 temp1 0 0 modNum)
							(proc27_1 3 @[global296 (- temp1 2)])
						)
					)
				)
			)
		else
			(super doVerb: theVerb)
		)
	)
	
	(method (cue)
		(self wander:)
	)
	
	(method (wander param1 param2 &tmp temp0 temp1)
		(asm
			lap      argc
			not     
			bt       code_1729
			lap      param1
			not     
			bnt      code_17eb
code_1729:
			pToa     room
			sat      temp1
code_172d:
			lst      temp1
			pToa     room
			eq?     
			bnt      code_17a2
			ldi      0
			sat      temp1
			pushi    2
			pushi    1
			pushi    100
			callk    Random,  4
			sat      temp0
			lsg      currentAct
			ldi      3
			eq?     
			bnt      code_1785
			lst      temp0
			ldi      15
			le?     
			bnt      code_1759
			ldi      650
			sat      temp1
			jmp      code_172d
code_1759:
			lst      temp0
			ldi      25
			le?     
			bnt      code_1768
			ldi      450
			sat      temp1
			jmp      code_172d
code_1768:
			lst      temp0
			ldi      35
			le?     
			bnt      code_1777
			ldi      454
			sat      temp1
			jmp      code_172d
code_1777:
			lst      temp0
			ldi      85
			le?     
			bnt      code_172d
			ldi      520
			sat      temp1
			jmp      code_172d
code_1785:
			lst      temp0
			ldi      30
			le?     
			bnt      code_1794
			ldi      650
			sat      temp1
			jmp      code_172d
code_1794:
			lst      temp0
			ldi      80
			le?     
			bnt      code_172d
			ldi      520
			sat      temp1
			jmp      code_172d
code_17a2:
			lat      temp1
			bnt      code_17d0
			pTos     room
			ldi      65535
			eq?     
			bnt      code_17c3
			pTos     originalDest
			ldi      0
			gt?     
			bnt      code_17bf
			pushi    #moveTo
			pushi    1
			pTos     originalDest
			self     6
code_17bf:
			ldi      0
			aTop     originalDest
code_17c3:
			pushi    #wander
			pushi    2
			lst      temp1
			pushSelf
			super    MuseumActor,  8
			jmp      code_17f6
code_17d0:
			pTos     room
			ldi      65535
			ne?     
			bnt      code_17dc
			pToa     room
			aTop     originalDest
code_17dc:
			pushi    #moveTo
			pushi    1
			pushi    65535
			self     6
			ldi      0
			aTop     wandering
			jmp      code_17f6
code_17eb:
			pushi    #wander
			pushi    1
			lsp      param1
			&rest    param2
			super    MuseumActor,  6
code_17f6:
			ret     
		)
	)
)

(instance aORiley of MuseumActor
	(properties
		noun 1
		modNum 1888
		originalView 818
	)
	
	(method (init)
		(= origStep (if (== view 818) 1282 else 770))
		(super init: &rest)
	)
	
	(method (doVerb theVerb theItem &tmp temp0 temp1 temp2)
		(if (OneOf theVerb 6 14)
			(if
				(==
					(= temp0
						(if (== argc 2)
							theItem
						else
							(curRoom setInset: (ScriptID 20 0))
						)
					)
					-1
				)
				(return)
			)
			(= temp2 (& temp0 $00ff))
			(= temp1
				(switch (& temp0 $ff00)
					(256 (+ temp2 1))
					(512 (+ temp2 18))
					(768 (+ temp2 26))
					(1024 (+ temp2 61))
				)
			)
			(switch temp0
				(258
					(cond 
						((Btst 134)
							(if (proc27_0 4 global364)
								(messager say: noun 6 1 0 0 modNum)
							else
								(messager say: noun 6 72 0 0 modNum)
								(proc27_1 4 @global364)
							)
						)
						((proc27_0 4 global297) (messager say: noun 6 1 0 0 modNum))
						(else
							(messager say: noun 6 3 0 0 modNum)
							(proc27_1 4 @global297)
						)
					)
				)
				(259
					(cond 
						((Btst 171)
							(if (proc27_0 4 global363)
								(messager say: noun 6 1 0 0 modNum)
							else
								(messager say: noun 6 69 0 0 modNum)
								(proc27_1 4 @global363)
							)
						)
						((proc27_0 4 global298) (messager say: noun 6 1 0 0 modNum))
						(else
							(messager say: noun 6 4 0 0 modNum)
							(proc27_1 4 @global298)
						)
					)
				)
				(264
					(cond 
						((Btst 143)
							(if (proc27_0 4 global366)
								(messager say: noun 6 1 0 0 modNum)
							else
								(messager say: noun 6 74 0 0 modNum)
								(proc27_1 4 @global366)
							)
						)
						((proc27_0 4 global303) (messager say: noun 6 1 0 0 modNum))
						(else
							(messager say: noun 6 9 0 0 modNum)
							(proc27_1 4 @global303)
						)
					)
				)
				(266
					(cond 
						((Btst 161)
							(if (proc27_0 4 global367)
								(messager say: noun 6 1 0 0 modNum)
							else
								(messager say: noun 6 73 0 0 modNum)
								(proc27_1 4 @global367)
							)
						)
						((proc27_0 4 global305) (messager say: noun 6 1 0 0 modNum))
						(else
							(messager say: noun 6 11 0 0 modNum)
							(proc27_1 4 @global305)
						)
					)
				)
				(267
					(cond 
						((Btst 158)
							(if (proc27_0 4 global365)
								(messager say: noun 6 1 0 0 modNum)
							else
								(messager say: noun 6 71 0 0 modNum)
								(proc27_1 4 @global365)
							)
						)
						((proc27_0 4 global306) (messager say: noun 6 1 0 0 modNum))
						(else
							(messager say: noun 6 12 0 0 modNum)
							(proc27_1 4 @global306)
						)
					)
				)
				(780
					(cond 
						((Btst 155)
							(if (proc27_0 4 global368)
								(messager say: noun 6 1 0 0 modNum)
							else
								(messager say: noun 6 75 0 0 modNum)
								(proc27_1 4 @global368)
							)
						)
						((proc27_0 4 global332) (messager say: noun 6 1 0 0 modNum))
						(else
							(messager say: noun 6 38 0 0 modNum)
							(proc27_1 4 @global332)
						)
					)
				)
				(else 
					(cond 
						((not (Message msgGET modNum noun 6 temp1 1)) (messager say: noun 6 81 0 0 modNum))
						((proc27_0 4 [global296 (- temp1 2)]) (messager say: noun 6 1 0 0 modNum))
						(else
							(messager say: noun 6 temp1 0 0 modNum)
							(proc27_1 4 @[global296 (- temp1 2)])
						)
					)
				)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance aTut of MuseumActor
	(properties
		noun 1
		modNum 1883
		scaleSignal $0001
		originalView 821
	)
	
	(method (doVerb theVerb theItem &tmp temp0 temp1 temp2)
		(if (OneOf theVerb 6 14)
			(if
				(==
					(= temp0
						(if (== argc 2)
							theItem
						else
							(curRoom setInset: (ScriptID 20 0))
						)
					)
					-1
				)
				(return)
			)
			(= temp2 (& temp0 $00ff))
			(= temp1
				(switch (& temp0 $ff00)
					(256 (+ temp2 1))
					(512 (+ temp2 18))
					(768 (+ temp2 26))
					(1024 (+ temp2 61))
				)
			)
			(switch temp0
				(258
					(cond 
						((Btst 134)
							(if (proc27_0 7 global364)
								(messager say: noun 6 1 0 0 modNum)
							else
								(messager say: noun 6 72 0 0 modNum)
								(proc27_1 7 @global364)
							)
						)
						((proc27_0 7 global297) (messager say: noun 6 1 0 0 modNum))
						(else
							(messager say: noun 6 3 0 0 modNum)
							(proc27_1 7 @global297)
						)
					)
				)
				(266
					(cond 
						((Btst 161)
							(if (proc27_0 7 global367)
								(messager say: noun 6 1 0 0 modNum)
							else
								(messager say: noun 6 73 0 0 modNum)
								(proc27_1 7 @global367)
							)
						)
						((proc27_0 7 global305) (messager say: noun 6 1 0 0 modNum))
						(else
							(messager say: noun 6 11 0 0 modNum)
							(proc27_1 7 @global305)
						)
					)
				)
				(else 
					(cond 
						((not (Message msgGET modNum noun 6 temp1 1)) (messager say: noun 6 81 0 0 modNum))
						((proc27_0 7 [global296 (- temp1 2)]) (messager say: noun 6 1 0 0 modNum))
						(else
							(messager say: noun 6 temp1 0 0 modNum)
							(proc27_1 7 @[global296 (- temp1 2)])
						)
					)
				)
			)
		else
			(super doVerb: theVerb)
		)
	)
	
	(method (cue)
		(self wander:)
	)
	
	(method (wander param1 param2 &tmp temp0 temp1)
		(asm
			lap      argc
			not     
			bt       code_20f7
			lap      param1
			not     
			bnt      code_217d
code_20f7:
			pToa     room
			sat      temp1
code_20fb:
			lst      temp1
			pToa     room
			eq?     
			bnt      code_2134
			ldi      0
			sat      temp1
			pushi    2
			pushi    1
			pushi    100
			callk    Random,  4
			sat      temp0
			lsg      currentAct
			ldi      3
			eq?     
			bnt      code_20fb
			lst      temp0
			ldi      5
			le?     
			bnt      code_2126
			ldi      450
			sat      temp1
			jmp      code_20fb
code_2126:
			lst      temp0
			ldi      10
			le?     
			bnt      code_20fb
			ldi      454
			sat      temp1
			jmp      code_20fb
code_2134:
			lat      temp1
			bnt      code_2162
			pTos     room
			ldi      65535
			eq?     
			bnt      code_2155
			pTos     originalDest
			ldi      0
			gt?     
			bnt      code_2151
			pushi    #moveTo
			pushi    1
			pTos     originalDest
			self     6
code_2151:
			ldi      0
			aTop     originalDest
code_2155:
			pushi    #wander
			pushi    2
			lst      temp1
			pushSelf
			super    MuseumActor,  8
			jmp      code_2188
code_2162:
			pTos     room
			ldi      65535
			ne?     
			bnt      code_216e
			pToa     room
			aTop     originalDest
code_216e:
			pushi    #moveTo
			pushi    1
			pushi    65535
			self     6
			ldi      0
			aTop     wandering
			jmp      code_2188
code_217d:
			pushi    #wander
			pushi    1
			lsp      param1
			&rest    param2
			super    MuseumActor,  6
code_2188:
			ret     
		)
	)
)

(instance aWatney of MuseumActor
	(properties
		noun 1
		modNum 1886
		originalView 815
	)
	
	(method (doVerb theVerb theItem &tmp temp0 temp1 temp2)
		(if (OneOf theVerb 6 14)
			(if
				(==
					(= temp0
						(if (== argc 2)
							theItem
						else
							(curRoom setInset: (ScriptID 20 0))
						)
					)
					-1
				)
				(return)
			)
			(= temp2 (& temp0 $00ff))
			(= temp1
				(switch (& temp0 $ff00)
					(256 (+ temp2 1))
					(512 (+ temp2 18))
					(768 (+ temp2 26))
					(1024 (+ temp2 61))
				)
			)
			(switch temp0
				(258
					(cond 
						((Btst 134)
							(if (proc27_0 8 global364)
								(messager say: noun 6 1 0 0 modNum)
							else
								(messager say: noun 6 72 0 0 modNum)
								(proc27_1 8 @global364)
							)
						)
						((proc27_0 8 global297) (messager say: noun 6 1 0 0 modNum))
						(else
							(messager say: noun 6 3 0 0 modNum)
							(proc27_1 8 @global297)
						)
					)
				)
				(else 
					(cond 
						((not (Message msgGET modNum noun 6 temp1 1)) (messager say: noun 6 81 0 0 modNum))
						((proc27_0 8 [global296 (- temp1 2)]) (messager say: noun 6 1 0 0 modNum))
						(else
							(messager say: noun 6 temp1 0 0 modNum)
							(proc27_1 8 @[global296 (- temp1 2)])
						)
					)
				)
			)
		else
			(super doVerb: theVerb)
		)
	)
	
	(method (cue)
		(self wander:)
	)
	
	(method (wander param1 param2 &tmp temp0 temp1)
		(asm
			lap      argc
			not     
			bt       code_23e4
			lap      param1
			not     
			bnt      code_246a
code_23e4:
			pToa     room
			sat      temp1
code_23e8:
			lst      temp1
			pToa     room
			eq?     
			bnt      code_2421
			ldi      0
			sat      temp1
			pushi    2
			pushi    1
			pushi    100
			callk    Random,  4
			sat      temp0
			lsg      currentAct
			ldi      3
			eq?     
			bnt      code_23e8
			lst      temp0
			ldi      40
			le?     
			bnt      code_2413
			ldi      500
			sat      temp1
			jmp      code_23e8
code_2413:
			lst      temp0
			ldi      85
			le?     
			bnt      code_23e8
			ldi      530
			sat      temp1
			jmp      code_23e8
code_2421:
			lat      temp1
			bnt      code_244f
			pTos     room
			ldi      65535
			eq?     
			bnt      code_2442
			pTos     originalDest
			ldi      0
			gt?     
			bnt      code_243e
			pushi    #moveTo
			pushi    1
			pTos     originalDest
			self     6
code_243e:
			ldi      0
			aTop     originalDest
code_2442:
			pushi    #wander
			pushi    2
			lst      temp1
			pushSelf
			super    MuseumActor,  8
			jmp      code_2475
code_244f:
			pTos     room
			ldi      65535
			ne?     
			bnt      code_245b
			pToa     room
			aTop     originalDest
code_245b:
			pushi    #moveTo
			pushi    1
			pushi    65535
			self     6
			ldi      0
			aTop     wandering
			jmp      code_2475
code_246a:
			pushi    #wander
			pushi    1
			lsp      param1
			&rest    param2
			super    MuseumActor,  6
code_2475:
			ret     
		)
	)
)

(instance aYvette of MuseumActor
	(properties
		noun 1
		modNum 1885
		scaleSignal $0001
		originalView 817
	)
	
	(method (doVerb theVerb theItem &tmp temp0 temp1 temp2)
		(cond 
			((== theVerb 2) (messager say: noun theVerb 27 0 0 modNum))
			((OneOf theVerb 6 14)
				(if
					(==
						(= temp0
							(if (== argc 2)
								theItem
							else
								(curRoom setInset: (ScriptID 20 0))
							)
						)
						-1
					)
					(return)
				)
				(= temp2 (& temp0 $00ff))
				(= temp1
					(switch (& temp0 $ff00)
						(256 (+ temp2 1))
						(512 (+ temp2 18))
						(768 (+ temp2 26))
						(1024 (+ temp2 61))
					)
				)
				(switch temp0
					(267
						(cond 
							((Btst 158)
								(if (proc27_0 9 global365)
									(messager say: noun 6 1 0 0 modNum)
								else
									(messager say: noun 6 71 0 0 modNum)
									(proc27_1 9 @global365)
								)
							)
							((proc27_0 9 global306) (messager say: noun 6 1 0 0 modNum))
							(else
								(messager say: noun 6 12 0 0 modNum)
								(proc27_1 9 @global306)
							)
						)
					)
					(259
						(cond 
							((Btst 171)
								(if (proc27_0 9 global363)
									(messager say: noun 6 1 0 0 modNum)
								else
									(messager say: noun 6 69 0 0 modNum)
									(proc27_1 9 @global363)
								)
							)
							((proc27_0 9 global298) (messager say: noun 6 1 0 0 modNum))
							(else
								(messager say: noun 6 4 0 0 modNum)
								(proc27_1 9 @global298)
							)
						)
					)
					(258
						(cond 
							((Btst 134)
								(if (proc27_0 9 global364)
									(messager say: noun 6 1 0 0 modNum)
								else
									(messager say: noun 6 72 0 0 modNum)
									(proc27_1 9 @global364)
								)
							)
							((proc27_0 9 global297) (messager say: noun 6 1 0 0 modNum))
							(else
								(messager say: noun 6 3 0 0 modNum)
								(proc27_1 9 @global297)
							)
						)
					)
					(271
						(if (Btst 134)
							(messager say: noun 6 30 0 0 modNum)
						else
							(messager say: noun 6 16 0 0 modNum)
						)
					)
					(263
						(if (proc27_0 9 global302)
							(messager say: noun 6 1 0 0 modNum)
						else
							(messager say: noun 6 24 0 0 modNum)
							(proc27_1 9 @global302)
						)
					)
					(else 
						(cond 
							((not (Message msgGET modNum noun 6 temp1 1)) (messager say: noun 6 81 0 0 modNum))
							((proc27_0 9 [global296 (- temp1 2)]) (messager say: noun 6 1 0 0 modNum))
							(else
								(messager say: noun 6 temp1 0 0 modNum)
								(proc27_1 9 @[global296 (- temp1 2)])
							)
						)
					)
				)
			)
			(else (super doVerb: theVerb))
		)
	)
)

(instance aZiggy of MuseumActor
	(properties
		noun 1
		modNum 1890
		originalView 816
	)
	
	(method (doVerb theVerb theItem &tmp temp0 temp1 temp2)
		(if (OneOf theVerb 6 14)
			(if
				(==
					(= temp0
						(if (== argc 2)
							theItem
						else
							(curRoom setInset: (ScriptID 20 0))
						)
					)
					-1
				)
				(return)
			)
			(= temp2 (& temp0 $00ff))
			(= temp1
				(switch (& temp0 $ff00)
					(256 (+ temp2 1))
					(512 (+ temp2 18))
					(768 (+ temp2 26))
					(1024 (+ temp2 61))
				)
			)
			(cond 
				((not (Message msgGET modNum noun 6 temp1 1)) (messager say: noun 6 81 0 0 modNum))
				((proc27_0 11 [global296 (- temp1 2)]) (messager say: noun 6 1 0 0 modNum))
				(else
					(messager say: noun 6 temp1 0 0 modNum)
					(proc27_1 11 @[global296 (- temp1 2)])
				)
			)
		else
			(super doVerb: theVerb)
		)
	)
	
	(method (cue)
		(self wander:)
	)
	
	(method (wander param1 param2 &tmp temp0 temp1)
		(asm
			lap      argc
			not     
			bt       code_29c0
			lap      param1
			not     
			bnt      code_2a46
code_29c0:
			pToa     room
			sat      temp1
code_29c4:
			lst      temp1
			pToa     room
			eq?     
			bnt      code_29fd
			ldi      0
			sat      temp1
			pushi    2
			pushi    1
			pushi    100
			callk    Random,  4
			sat      temp0
			lsg      currentAct
			ldi      3
			eq?     
			bnt      code_29c4
			lst      temp0
			ldi      5
			le?     
			bnt      code_29ef
			ldi      450
			sat      temp1
			jmp      code_29c4
code_29ef:
			lst      temp0
			ldi      10
			le?     
			bnt      code_29c4
			ldi      454
			sat      temp1
			jmp      code_29c4
code_29fd:
			lat      temp1
			bnt      code_2a2b
			pTos     room
			ldi      65535
			eq?     
			bnt      code_2a1e
			pTos     originalDest
			ldi      0
			gt?     
			bnt      code_2a1a
			pushi    #moveTo
			pushi    1
			pTos     originalDest
			self     6
code_2a1a:
			ldi      0
			aTop     originalDest
code_2a1e:
			pushi    #wander
			pushi    2
			lst      temp1
			pushSelf
			super    MuseumActor,  8
			jmp      code_2a51
code_2a2b:
			pTos     room
			ldi      65535
			ne?     
			bnt      code_2a37
			pToa     room
			aTop     originalDest
code_2a37:
			pushi    #moveTo
			pushi    1
			pushi    65535
			self     6
			ldi      0
			aTop     wandering
			jmp      code_2a51
code_2a46:
			pushi    #wander
			pushi    1
			lsp      param1
			&rest    param2
			super    MuseumActor,  6
code_2a51:
			ret     
		)
	)
)

(class TravelToRoom of Script
	(properties
		client 0
		state $ffff
		start 0
		timer 0
		cycles 0
		seconds 0
		lastSeconds 0
		ticks 0
		lastTicks 0
		register 0
		script 0
		caller 0
		next 0
		useDoor 0
		fromRendezvous 0
	)
	
	(method (dispose param1)
		(if (or (not argc) (not param1)) (= caller 0))
		(super dispose:)
	)
	
	(method (changeState newState &tmp clientSetIndex temp1 museumRgnMidPts clientRoom clientExitX clientDestY clientIsAdjacent temp7 theRegister theRegister_2)
		(switch (= state newState)
			(0 (= register 0) (= cycles 1))
			(1
				(if
					(and
						(==
							(= temp1
								(client findRendezvous: (client originalDest?))
							)
							(client room?)
						)
						(not fromRendezvous)
					)
					(self changeState: 9)
				else
					(if fromRendezvous (++ state))
					(= cycles 1)
				)
			)
			(2
				(= clientSetIndex (* (client setIndex:) 11))
				(if (!= (client destination?) (client room?))
					(= register (client dirToRoom: (client destination?)))
					(= clientIsAdjacent (client isAdjacent:))
				)
				(= museumRgnMidPts
					(switch clientIsAdjacent
						(1 (MuseumRgn northPts?))
						(2 (MuseumRgn eastPts?))
						(3 (MuseumRgn southPts?))
						(4 (MuseumRgn westPts?))
						(else 
							(if (== (client room?) curRoomNum)
								(MuseumRgn midPts?)
							else
								0
							)
						)
					)
				)
				(switch register
					(1
						(client
							exitX: (if museumRgnMidPts (museumRgnMidPts northPtX?) else 160)
							exitY: (if museumRgnMidPts (museumRgnMidPts northPtY?) else 0)
						)
					)
					(2
						(client
							exitX: (if museumRgnMidPts (museumRgnMidPts eastPtX?) else 320)
							exitY: (if museumRgnMidPts (museumRgnMidPts eastPtY?) else 95)
						)
					)
					(3
						(client
							exitX: (if museumRgnMidPts (museumRgnMidPts southPtX?) else 160)
							exitY: (if museumRgnMidPts (museumRgnMidPts southPtY?) else 190)
						)
					)
					(4
						(client
							exitX: (if museumRgnMidPts (museumRgnMidPts westPtX?) else 0)
							exitY: (if museumRgnMidPts (museumRgnMidPts westPtY?) else 95)
						)
					)
					(else 
						(client exitX: 0 exitY: 0)
					)
				)
				(if (>= (= clientExitX (client exitX?)) 1000)
					(client exitX: (- clientExitX 1000))
					(= useDoor register)
				)
				(= cycles 1)
			)
			(3
				(if fromRendezvous
					(= clientRoom (client room?))
				else
					(= clientRoom (client nextRoom: register))
				)
				(cond 
					(
						(and
							(== (client room?) 530)
							(== clientRoom 600)
							(== curRoomNum 530)
						)
						(= ticks 600)
					)
					(fromRendezvous (= cycles 1))
					((> (MuseumRgn crowdInRoom: clientRoom) 1)
						(if (not (= clientIsAdjacent (client waitCount?)))
							(-- state)
							(= ticks 15)
						else
							(client waitCount: (-- clientIsAdjacent))
							(-- state)
							(= ticks (* (Random 3 8) 60))
						)
					)
					((== (client room?) curRoomNum) (= ticks 60))
					((not (client beelining?)) (= ticks (* (Random 3 15) 60)))
					(else (= cycles 1))
				)
			)
			(4
				(if fromRendezvous
					(= cycles 1)
				else
					(= museumRgnMidPts
						(MuseumRgn whichList: (client room?))
					)
					(client
						setMotion:
							PolyPath
							(client exitX?)
							(client exitY?)
							self
							1
							museumRgnMidPts
					)
				)
			)
			(5
				(if (and useDoor (== (client room?) curRoomNum))
					(self setScript: (sUseDoor new:) self 1)
				else
					(= cycles 1)
				)
			)
			(6
				(= clientSetIndex (* (client roomIdx?) 5))
				(if fromRendezvous
					(client
						view:
							(if (== (client room?) curRoomNum)
								(client originalView?)
							else
								828
							)
					)
					(= fromRendezvous 0)
				else
					(= clientRoom (client nextRoom: register))
					(client
						lastRoom: (client room?)
						room: clientRoom
						view: (if (== clientRoom curRoomNum)
							(client originalView?)
						else
							828
						)
						setIndex:
					)
					(MuseumRgn
						crowdInRoom: (client room?) 1
						crowdInRoom: (client lastRoom?) -1
					)
				)
				(= theRegister register)
				(= clientSetIndex (* (client roomIdx?) 6))
				(if (> (client lastRoom?) 0)
					(= clientRoom (client lastRoom?))
					(= register 1)
					(while (< register 5)
						(if
							(==
								[theTheOriginalDest (+ clientSetIndex register 1)]
								clientRoom
							)
							(break)
						)
						(++ register)
					)
				else
					(= register -1)
				)
				(= theRegister_2 register)
				(= clientSetIndex (* (client roomIdx?) 11))
				(cond 
					((== (client room?) curRoomNum) (= museumRgnMidPts (MuseumRgn midPts?)))
					((client isAdjacent:)
						(= museumRgnMidPts
							(switch theRegister
								(1 (MuseumRgn northPts?))
								(2 (MuseumRgn eastPts?))
								(3 (MuseumRgn southPts?))
								(4 (MuseumRgn westPts?))
							)
						)
					)
					(else (= museumRgnMidPts 0))
				)
				(switch theRegister_2
					(1
						(if museumRgnMidPts
							(= clientExitX (museumRgnMidPts northPtX?))
							(= clientDestY (museumRgnMidPts northPtY?))
						else
							(= clientExitX 160)
							(= clientDestY 50)
						)
					)
					(2
						(if museumRgnMidPts
							(= clientExitX (museumRgnMidPts eastPtX?))
							(= clientDestY (museumRgnMidPts eastPtY?))
						else
							(= clientExitX 320)
							(= clientDestY 120)
						)
					)
					(3
						(if museumRgnMidPts
							(= clientExitX (museumRgnMidPts southPtX?))
							(= clientDestY (museumRgnMidPts southPtY?))
						else
							(= clientExitX 160)
							(= clientDestY 190)
						)
					)
					(4
						(if museumRgnMidPts
							(= clientExitX (museumRgnMidPts westPtX?))
							(= clientDestY (museumRgnMidPts westPtY?))
						else
							(= clientExitX 0)
							(= clientDestY 120)
						)
					)
					(-1
						(if museumRgnMidPts
							(= clientExitX (museumRgnMidPts midPtX?))
							(= clientDestY (museumRgnMidPts midPtY?))
						else
							(= clientExitX 160)
							(= clientDestY 165)
						)
					)
					(else 
						(= clientExitX (= clientDestY 0))
					)
				)
				(client posn: clientExitX clientDestY)
				(if (>= clientExitX 1000)
					(= clientExitX (- clientExitX 1000))
					(= useDoor register)
				)
				(= cycles 1)
			)
			(7
				(if (and useDoor (== (client room?) curRoomNum))
					(self setScript: (sUseDoor new:) self -1)
				else
					(= cycles 1)
				)
			)
			(8
				(if (== (client destX?) -1)
					(cond 
						(
							(and
								(== curRoomNum 510)
								(or
									(and
										(== (client lastRoom?) 520)
										(< (client originalDest?) 510)
									)
									(and
										(== (client lastRoom?) 500)
										(== (client originalDest?) 520)
									)
								)
							)
							(= clientExitX 80)
							(= clientDestY 165)
						)
						((> (MuseumRgn crowdInRoom: (client room?)) 1)
							(= clientExitX (- ((MuseumRgn midPts?) midPtX?) 15))
							(= clientDestY (- ((MuseumRgn midPts?) midPtY?) 15))
						)
						((== (client room?) curRoomNum)
							(= clientExitX ((MuseumRgn midPts?) midPtX?))
							(= clientDestY ((MuseumRgn midPts?) midPtY?))
						)
						(else (= clientExitX 160) (= clientDestY 165))
					)
				else
					(= clientExitX (client destX?))
					(= clientDestY (client destY?))
				)
				(= museumRgnMidPts
					(MuseumRgn whichList: (client room?))
				)
				(client
					setMotion: PolyPath clientExitX clientDestY self 1 museumRgnMidPts
				)
			)
			(9
				(cond 
					((!= (client room?) (client destination?)) (= register 0) (self changeState: 2))
					(
					(!= (client originalDest?) (client destination?))
						(cond 
							(
								(==
									[local0 (+ (* (= clientSetIndex (client setIndex:)) 5) 1)]
									-1
								)
								(= register 1)
								(client
									exitX: ((MuseumRgn midPts?) northPtX?)
									exitY: ((MuseumRgn midPts?) northPtY?)
								)
							)
							((== [local0 (+ (* clientSetIndex 5) 2)] -1)
								(= register 2)
								(client
									exitX: ((MuseumRgn midPts?) eastPtX?)
									exitY: ((MuseumRgn midPts?) eastPtY?)
								)
							)
							((== [local0 (+ (* clientSetIndex 5) 3)] -1)
								(= register 3)
								(client
									exitX: ((MuseumRgn midPts?) southPtX?)
									exitY: ((MuseumRgn midPts?) southPtY?)
								)
							)
							((== [local0 (+ (* clientSetIndex 5) 4)] -1)
								(= register 4)
								(client
									exitX: ((MuseumRgn midPts?) westPtX?)
									exitY: ((MuseumRgn midPts?) westPtY?)
								)
							)
						)
						(= museumRgnMidPts
							(MuseumRgn whichList: (client room?))
						)
						(client
							setMotion:
								PolyPath
								(client exitX?)
								(client exitY?)
								self
								1
								museumRgnMidPts
						)
					)
					(else (client beelining: 0) (self dispose: 1))
				)
			)
			(10
				(= clientRoom
					[theTheOriginalDest (+
						(* (= clientSetIndex (client roomIdx?)) 6)
						register
						1
					)]
				)
				(client
					lastRoom: (client room?)
					room: clientRoom
					destination: (client originalDest?)
					setIndex:
				)
				(MuseumRgn
					crowdInRoom: (client room?) 1
					crowdInRoom: (client lastRoom?) -1
				)
				(= fromRendezvous 1)
				(self changeState: 1)
			)
		)
	)
)

(instance sUseDoor of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(user canControl: 0 canInput: 0)
				(theGame setCursor: waitCursor)
				(if (== register -1)
					((client client?)
						setPri: (-
							((ScriptID curRoomNum (client useDoor?)) priority?)
							1
						)
						posn:
							((ScriptID curRoomNum (client useDoor?)) moveToX?)
							((ScriptID curRoomNum (client useDoor?)) moveToY?)
					)
				)
				((ScriptID curRoomNum (client useDoor?))
					setCycle: End self
				)
				(altPolyList
					delete: ((ScriptID curRoomNum (client useDoor?)) doorPoly?)
				)
			)
			(1
				(= temp0
					(if (== register 1)
						((ScriptID curRoomNum (client useDoor?)) moveToX?)
					else
						((ScriptID curRoomNum (client useDoor?)) approachX?)
					)
				)
				(= temp1
					(if (== register 1)
						((ScriptID curRoomNum (client useDoor?)) moveToY?)
					else
						((ScriptID curRoomNum (client useDoor?)) approachY?)
					)
				)
				((client client?)
					setPri: -1
					setMotion: PolyPath temp0 temp1 self
				)
			)
			(2
				((ScriptID curRoomNum (client useDoor?)) setCycle: Beg)
				(altPolyList
					add: ((ScriptID curRoomNum (client useDoor?)) doorPoly?)
				)
				(client useDoor: 0)
				(theGame setCursor: ((theIconBar curIcon?) cursor?))
				(user canControl: 1 canInput: 1)
				(self dispose:)
			)
		)
	)
)

(instance fumeTimer of Timer
	(properties)
	
	(method (setReal)
		(MuseumRgn timer: self)
		(super setReal: &rest)
	)
	
	(method (cue)
		(Bclr 20)
	)
)

(instance meetingTimer of Timer
	(properties)
	
	(method (setReal param1 param2 param3 param4 param5)
		(= global125 param5)
		(MuseumRgn timer: self)
		(super setReal: param1 param2 param3 param4)
	)
	
	(method (cue)
		(curRoom notify:)
		((ScriptID 22 0) doit: global125)
	)
)

(instance wanderTimer of Timer
	(properties)
	
	(method (cue &tmp temp0 temp1)
		(= temp0 0)
		(while (< temp0 (cast size?))
			(if
				(and
					((= temp1 (cast at: temp0)) respondsTo: #room)
					(== (temp1 room?) -1)
				)
				(temp1 wander:)
			)
			(++ temp0)
		)
		(self setReal: self 120)
	)
)
