;;; Sierra Script 1.0 - (do not remove this comment)
(script# 470)
(include sci.sh)
(use Main)
(use Procs)
(use GKEgo)
(use Inset)
(use Scaler)
(use Osc)
(use PolyPath)
(use Polygon)
(use Feature)
(use StopWalk)
(use Grooper)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm470 0
)

(local
	whichGrass
	eventX
	eventY
	local3
	local4
	gGKCurrentSpeed
)
(instance rm470 of Rm
	(properties
		noun 1
		picture 470
		style $000a
	)
	
	(method (init)
		(curRoom
			addObstacle:
				(if (Btst 11)
					((Polygon new:)
						type: 3
						init:
							56
							140
							108
							140
							141
							145
							208
							145
							216
							136
							311
							122
							311
							105
							300
							84
							255
							81
							253
							75
							231
							76
							223
							75
							181
							73
							136
							70
							53
							70
							89
							87
							98
							95
							96
							106
							43
							137
							1
							142
							0
							145
							41
							145
						yourself:
					)
				else
					((Polygon new:)
						type: 3
						init:
							56
							140
							108
							140
							141
							145
							208
							145
							216
							136
							311
							122
							311
							105
							300
							84
							255
							81
							253
							75
							231
							76
							223
							75
							202
							87
							160
							95
							134
							86
							89
							87
							98
							95
							99
							107
							95
							116
							44
							138
							42
							144
						yourself:
					)
				)
		)
		(super init:)
		(Load rsMESSAGE 470)
		(if (not (Btst 220))
			(= gGKCurrentSpeed (theGame currentSpeed?))
			(Bset 198)
		)
		(theGame handsOff:)
		(ego
			view: 4792
			loop: 2
			posn: 160 130
			setCycle: StopWalk -1
			ignoreActors: 1
			ignoreControl: -32768
			signal: 4096
			scaleSignal: 4
			init:
			hide:
		)
		(if (not (Btst 220)) (ego moveSpeed: 6 cycleSpeed: 6))
		(lake init:)
		(road init:)
		(clayBank init:)
		(reflectorClose init:)
		(reflectorMid init:)
		(reflectorFar init:)
		(rightTree init:)
		(leftTree init:)
		(leftGrass init:)
		(middleGrass init:)
		(rightGrass init:)
		(cageGrass init:)
		(sand init:)
		(myExitFeature init:)
		(if (not (Btst 11))
			(rightPoliceTape init:)
			(leftPoliceTape init:)
			(topPoliceTape init:)
			(polCar1 init:)
			(polCar2 init:)
			(ambulance init:)
			(ambulanceLights init:)
			(polCar1Lights init:)
			(polCar2Lights init:)
			(kneelingCop init:)
			(writingCop init:)
			(photoCop init:)
			(deadBody init: ignoreActors: 1)
			(mosley
				init:
				ignoreActors: 1
				ignoreControl: -32768
				stopUpd:
			)
			(coroner init: setPri: 6 stopUpd:)
			(patternOnGround init:)
		)
		(walkHandler add: lake)
		(walkHandler add: road)
		(walkHandler add: myExitFeature)
		(if
			(and
				(<= currentDay 3)
				(Btst 11)
				(not (== prevRoomNum 471))
			)
			(leftPoliceTape init:)
			(rightPoliceTape init:)
			(topPoliceTape init:)
			(patternOnGround init:)
		)
		(theMusic1
			number: 470
			loop: -1
			play:
			setVol: 0
			fade: 80 25 10 0
		)
		(cond 
			((== prevRoomNum 471) (curRoom setScript: afterTheCloseUp))
			((not (Btst 11)) (curRoom setScript: openingCartoon))
			(else
				(curRoom drawPic: 470 10)
				(ego
					show:
					posn: 169 180
					setScale: Scaler 100 57 109 64
					setScript: comeHereAgain
				)
			)
		)
	)
	
	(method (dispose)
		(walkHandler delete: lake)
		(walkHandler delete: road)
		(walkHandler delete: myExitFeature)
		(ego setScale: 0)
		(super dispose:)
		(DisposeScript 939)
		(DisposeScript 935)
	)
)

(instance comeHereAgain of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 175 145 self)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance openingCartoon of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Load rsVIEW 4792)
				(messager say: 11 0 8 0 self)
				(kneelingCop setCycle: Osc 1 self)
				(writingCop xStep: 4 ignoreControl: -32768 setCycle: Fwd)
			)
			(1)
			(2
				(ego
					show:
					posn: 169 180
					setPri: 12
					setMotion: MoveTo 175 145 self
				)
				(photoCop setCycle: Osc 1 self)
				(kneelingCop setCycle: Osc 3)
			)
			(3)
			(4
				(ego stopUpd:)
				(photoCop loop: 4 cel: 0 setCycle: End self)
			)
			(5
				(photoCop loop: 5 cel: 0 setCycle: Osc 1 self)
				(kneelingCop loop: 7 cel: 0 setCycle: End)
			)
			(6
				(UnLoad 128 471)
				(UnLoad 128 4712)
				(writingCop loop: 1 cel: 0 setCycle: End)
				(photoCop
					view: 4791
					ignoreActors: 1
					ignoreControl: -32768
					setCycle: Walk
					setScale: Scaler 100 60 124 84
					setMotion: MoveTo 78 123 self
				)
				(kneelingCop
					view: 4791
					ignoreControl: -32768
					setCycle: Walk
					setScale: Scaler 100 60 124 84
					setMotion: MoveTo 182 93
				)
			)
			(7
				(photoCop setPri: 9 setMotion: MoveTo 89 110 self)
			)
			(8
				(photoCop setLoop: 6 setMotion: MoveTo 135 93 self)
			)
			(9
				(Load rsVIEW 479)
				(Load rsVIEW 4713)
				(writingCop loop: 0 cel: 0 setCycle: End self)
			)
			(10
				(writingCop
					view: 2373
					setLoop: 1
					setCycle: Walk
					setMotion: PolyPath 104 116 self
				)
				(polCar1 setScript: leaveTheRoom)
			)
			(11
				(writingCop
					view: 4713
					loop: 0
					cel: 1
					setPri: 9
					setScale: 0
					setCycle: CT 2 1 self
				)
			)
			(12
				(theSound1 number: 475 play:)
				(writingCop
					view: 4713
					loop: 0
					cel: 3
					setPri: 9
					setScale: 0
					setCycle: CT 10 1 self
				)
			)
			(13
				(writingCop loop: 0 cel: 11 setCycle: End self)
				(deadBody cel: 1)
			)
			(14
				(UnLoad 128 4713)
				(writingCop
					view: 2373
					setLoop: 0
					setPri: -1
					setCycle: Walk
					setMotion: PolyPath 215 120 self
				)
			)
			(15
				(ego setScript: enterEgo)
				(UnLoad 128 2373)
				(writingCop
					view: 4711
					loop: 0
					cel: 0
					setScale: 0
					setCycle: Osc 2 self
				)
			)
			(16
				(writingCop stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance leaveTheRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theSound2 number: 476 play:)
				(kneelingCop
					view: 479
					posn: 187 88
					loop: 0
					cel: 0
					setScale: 0
					ignoreControl: -32768
					ignoreActors: 1
					setMotion: 0
					setCycle: End self
				)
				(photoCop
					ignoreControl: -32768
					ignoreActors: 1
					setPri: 3
					setMotion: MoveTo 165 83 self
				)
			)
			(1
				(theSound2 number: 477 play:)
			)
			(2
				(theSound2 number: 476 play:)
				(photoCop
					view: 479
					posn: 159 75
					loop: 1
					cel: 0
					setPri: 5
					setScale: 0
					setCycle: End self
				)
				(UnLoad 128 4791)
			)
			(3
				(theSound2 number: 477 play:)
				(= seconds 2)
			)
			(4
				(photoCop dispose:)
				(kneelingCop dispose:)
				(polCar1Lights dispose:)
				(theSound1 number: 472 play:)
				(polCar1 view: 4704 loop: 2 cel: 0 setCycle: End self)
			)
			(5
				(UnLoad 128 4702)
				(UnLoad 128 4704)
				(polCar1
					view: 4705
					setPri: 2
					ignoreActors: 1
					ignoreControl: -32768
					setLoop: 0
					cel: 0
					setSpeed: 3
					setScale: Scaler 100 68 79 60
					setMotion: MoveTo 33 64 self
				)
			)
			(6
				(polCar1
					view: 478
					loop: 0
					cel: 0
					setPri: -1
					setSpeed: 6
					posn: 77 60
					setScale: 0
					setCycle: End self
				)
			)
			(7
				(polCar1 dispose:)
				(UnLoad 128 479)
				(UnLoad 128 4705)
				(UnLoad 128 478)
				(self dispose:)
			)
		)
	)
)

(instance enterEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setPri: 12 setMotion: MoveTo 175 117 self)
			)
			(1
				(UnLoad 128 472)
				(deadBody stopUpd:)
				(mosley
					view: 4793
					loop: 8
					cel: 0
					setLoop: Grooper
					setCycle: StopWalk -1
				)
				(Face ego mosley self)
			)
			(2
				(ego setPri: -1)
				(messager say: 11 0 1 1 self)
			)
			(3 (Face mosley ego self))
			(4
				(messager say: 11 0 1 2 7 self)
			)
			(5
				(mosley
					view: 4793
					setCycle: StopWalk -1
					moveSpeed: 9
					cycleSpeed: 9
					ignoreActors: 1
					ignoreControl: -32768
					setMotion: PolyPath 134 127 self
				)
				(ego
					normalize: 0 4792
					moveSpeed: 6
					cycleSpeed: 6
					setMotion: PolyPath 98 114 self
				)
			)
			(6
				(ego view: 475 loop: 0 cel: 0 setPri: 7 setCycle: End)
			)
			(7
				(mosley
					moveSpeed: 6
					cycleSpeed: 6
					view: 4793
					setCycle: StopWalk -1
					setMotion: PolyPath 105 127 self
				)
			)
			(8
				(deadBody startUpd:)
				(Face mosley topPoliceTape self)
			)
			(9
				(mosley view: 472 loop: 3 cel: 1 setCycle: CT 2 1 self)
			)
			(10
				(theSound1 number: 475 play:)
				(mosley view: 472 loop: 3 cel: 3 setCycle: End self)
			)
			(11
				(deadBody cel: 0)
				(= cycles 1)
			)
			(12
				(messager say: 11 0 1 8 self)
			)
			(13
				(messager say: 11 0 1 9 self)
				(ego view: 475 loop: 1 cel: 0 setCycle: Osc 1 self)
			)
			(14)
			(15
				(UnLoad 128 478)
				(Load rsVIEW 473)
				(messager say: 11 0 1 10 self)
			)
			(16
				(theSound1 number: 473 play:)
				(= cycles 5)
			)
			(17
				(leftPoliceTape setPri: 7 startUpd:)
				(limo
					init:
					setPri: (- (leftPoliceTape priority?) 1)
					setCycle: End self
				)
			)
			(18
				(moveTheTape dispose:)
				(leftPoliceTape stopUpd:)
				(limo stopUpd:)
				(= cycles 1)
			)
			(19
				(mosley view: 472 loop: 4 cel: 0 setCycle: End self)
				(ego view: 473 loop: 3 cel: 0 setCycle: End self)
			)
			(20)
			(21
				(messager say: 11 0 1 11 self)
			)
			(22
				(theMusic1 fade:)
				(UnLoad 128 475)
				(UnLoad 128 4791)
				(UnLoad 128 4791)
				(UnLoad 128 472)
				(mosley
					view: 4793
					loop: 8
					cel: 1
					setCycle: StopWalk -1
					setMotion: MoveTo 80 127
				)
				(ego
					view: 4792
					loop: 8
					cel: 1
					setPri: 8
					setCycle: StopWalk -1
					setMotion: MoveTo 88 114 self
				)
			)
			(23
				(Bset 220)
				(curRoom newRoom: 471)
				(self dispose:)
			)
		)
	)
)

(instance afterTheCloseUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 4792
					loop: 8
					cel: 1
					setPri: 8
					setCycle: StopWalk -1
					posn: 88 114
					show:
					init:
				)
				(mosley
					view: 4793
					loop: 8
					cel: 1
					setCycle: StopWalk -1
					posn: 80 127
					init:
				)
				(limo init: setPri: 7 cel: 4)
				(limoWindow init: setPri: 9 cel: 3)
				(coroner init: stopUpd:)
				(polCar2 init:)
				(polCar2Lights init:)
				(ambulance init:)
				(ambulanceLights init:)
				(rightPoliceTape init:)
				(leftPoliceTape init:)
				(topPoliceTape init:)
				(writingCop
					view: 4711
					loop: 0
					cel: 0
					posn: 215 120
					xStep: 4
					init:
					ignoreControl: -32768
					stopUpd:
				)
				(deadBody init:)
				(patternOnGround init:)
				(= cycles 1)
			)
			(1
				(messager say: 11 0 1 12 15 self)
			)
			(2
				(Face ego 0 0)
				(theSound1 number: 474 play:)
				(limoWindow cycleSpeed: 11 setCycle: Beg self)
			)
			(3
				(limoWindow dispose:)
				(theSound1 number: 472 play:)
				(limo loop: 2 cel: 0 setCycle: End self)
			)
			(4
				(limo dispose:)
				(ego view: 473 loop: 3 cel: 6 setCycle: Beg self)
				(mosley view: 472 loop: 5 cel: 3)
			)
			(5
				(messager say: 11 0 1 16 21 self)
			)
			(6
				(writingCop
					view: 2371
					setCycle: StopWalk -1
					setMotion: PolyPath 125 131 self
				)
			)
			(7
				(mosley loop: 5 cel: 0)
				(= cycles 5)
			)
			(8
				(messager say: 11 0 1 22 24 self)
			)
			(9
				(mosleyHead
					init:
					view: 472
					cel: 0
					setPri: 10
					ignoreActors: 1
					setCycle: End self
				)
			)
			(10
				(messager say: 11 0 1 25 26 self)
			)
			(11
				(mosleyHead dispose:)
				(mosley
					view: 4793
					setCycle: Walk
					ignoreActors: 1
					ignoreControl: -32768
					setMotion: PolyPath 134 127 self
				)
				(ego
					normalize: 0 4792
					setPri: 8
					moveSpeed: 9
					cycleSpeed: 9
					setMotion: MoveTo 173 117 self
				)
			)
			(12
				(writingCop setScript: takeTheBody)
				(mosley
					view: 4793
					setCycle: StopWalk -1
					setMotion: PolyPath 192 116 self
				)
			)
			(13
				(Face mosley leftPoliceTape)
			)
			(14
				(mosley view: 472 loop: 5 cel: 1 stopUpd:)
				(messager say: 11 0 1 27 28)
			)
			(15
				(Face ego polCar2)
				(ego setPri: 10)
				(mosley
					view: 4793
					setCycle: Walk
					setPri: 9
					setScale: Scaler 90 40 109 64
					setMotion: MoveTo 130 77 self
				)
			)
			(16
				(theSound1 number: 476 play:)
				(mosley
					posn: 125 80
					view: 479
					loop: 4
					cel: 0
					setScale: 0
					setCycle: End self
				)
			)
			(17
				(theSound1 number: 477 play:)
				(= cycles 5)
			)
			(18
				(if (== local3 1) (polCar2 setScript: takeOff))
				(= local3 1)
				(ego
					setScale: Scaler 100 57 109 64
					setPri: -1
					moveSpeed: 6
					cycleSpeed: 6
				)
				(leftPoliceTape setPri: 6)
				(Bset 65)
				(self dispose:)
			)
		)
	)
)

(instance takeTheBody of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 6))
			(1
				(leftPoliceTape setScript: moveTheTape)
				(writingCop
					view: 2371
					setCycle: Walk
					ignoreActors: 1
					ignoreControl: -32768
					setMotion: PolyPath 105 126 self
				)
				(coroner
					view: 4743
					setLoop: 0
					setCycle: Walk
					setScale: Scaler 100 60 124 84
					ignoreActors: 1
					ignoreControl: -32768
					setMotion: MoveTo 135 123 self
				)
			)
			(2
				(writingCop
					view: 474
					loop: 4
					cel: 1
					setScale: 0
					setCycle: CT 2 1 self
				)
			)
			(3
				(theSound1 number: 475 play:)
				(writingCop
					view: 474
					loop: 4
					cel: 2
					setScale: 0
					setCycle: CT 9 1 self
				)
			)
			(4
				(deadBody cel: 1)
				(writingCop
					view: 474
					loop: 4
					cel: 10
					setScale: 0
					setCycle: End self
				)
			)
			(5)
			(6
				(leftPoliceTape setPri: 5)
				(writingCop
					view: 2371
					setCycle: StopWalk -1
					setMotion: PolyPath 85 126 self
				)
			)
			(7
				(Face writingCop ambulance)
				(coroner
					view: 474
					posn: 133 125
					loop: 1
					cel: 0
					setCycle: End self
				)
			)
			(8
				(writingCop view: 474 loop: 3 cel: 0 setCycle: End self)
			)
			(9
				(deadBody dispose:)
				(writingCop hide:)
				(coroner loop: 2 cel: 0 setCycle: End self)
			)
			(10
				(writingCop show: setCycle: Beg self)
				(coroner view: 4740 loop: 0 cel: 0 setCycle: End self)
			)
			(11)
			(12
				(ambulance setScript: putBodyAway)
				(= seconds 3)
			)
			(13
				(writingCop
					view: 2371
					setCycle: Walk
					setScale: Scaler 100 60 124 84
					ignoreActors: 1
					ignoreControl: -32768
					setPri: 8
					setLoop: 6
					setMotion: MoveTo 167 81 self
				)
			)
			(14
				(writingCop
					view: 2371
					setCycle: Walk
					setScale: Scaler 100 60 124 84
					setPri: 1
					setLoop: -1
					ignoreActors: 1
					ignoreControl: -32768
					setMotion: MoveTo 163 76 self
				)
			)
			(15
				(theSound2 number: 476 play:)
				(writingCop
					posn: 165 79
					view: 479
					loop: 3
					cel: 0
					setScale: 0
					setCycle: End self
				)
			)
			(16
				(theSound2 number: 477 play:)
				(if (== local3 1) (polCar2 setScript: takeOff))
				(= local3 1)
				(self dispose:)
			)
		)
	)
)

(instance takeOff of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(polCar2Lights dispose:)
				(mosley dispose:)
				(writingCop hide:)
				(= cycles 1)
			)
			(1
				(theSound2 number: 472 play:)
				(polCar2
					view: 4703
					posn: 112 83
					loop: 8
					cel: 0
					ignoreActors: 1
					ignoreControl: -32768
					setCycle: End self
				)
			)
			(2
				(polCar2
					posn: 77 60
					view: 478
					loop: 0
					cel: 0
					setSpeed: 6
					setCycle: End self
				)
			)
			(3
				(Bclr 198)
				(leftPoliceTape setPri: 6)
				(UnLoad 128 479)
				(polCar2 dispose:)
				(writingCop dispose:)
				(theGame handsOn:)
				(ego setSpeed: gGKCurrentSpeed getPoints: 126 5)
				(self dispose:)
			)
		)
	)
)

(instance putBodyAway of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(coroner
					view: 4741
					loop: 1
					cel: 0
					setPri: 6
					setCycle: End self
				)
			)
			(1
				(coroner
					posn: 114 125
					setLoop: 2
					setCycle: Walk
					ignoreActors: 1
					ignoreControl: -32768
					setPri: -1
					setMotion: MoveTo 130 90 self
				)
			)
			(2
				(coroner
					view: 4742
					loop: 3
					cel: 0
					setScale: 0
					setCycle: End self
				)
			)
			(3
				(ambulance cel: 1 startUpd:)
				(coroner
					loop: 4
					cel: 0
					posn: 111 62
					setPri: 8
					setCycle: CT 3 1 self
				)
			)
			(4
				(theSound1 number: 477 play:)
				(coroner cel: 4 setCycle: CT 8 1 self)
			)
			(5
				(theSound1 number: 477 play:)
				(coroner cel: 9 setCycle: End self)
			)
			(6
				(ambulance setPri: 2 stopUpd:)
				(ambulanceLights setPri: 3)
				(afterTheCloseUp cue:)
				(coroner
					loop: 6
					posn: 120 90
					setCycle: Walk
					moveSpeed: 5
					cycleSpeed: 5
					setPri: 4
					setMotion: MoveTo 81 84 self
				)
			)
			(7
				(theSound1 number: 476 play:)
				(coroner loop: 5 cel: 0 setCycle: End self)
			)
			(8
				(theSound2 number: 477 play:)
				(= cycles 5)
			)
			(9
				(coroner dispose:)
				(ambulanceLights dispose:)
				(theSound1 number: 472 play:)
				(ambulance view: 4703 loop: 2 cel: 0 setCycle: End self)
			)
			(10
				(ambulance dispose:)
				(UnLoad 128 479)
				(self dispose:)
			)
		)
	)
)

(instance moveTheTape of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(rightPoliceTape stopUpd:)
				(topPoliceTape stopUpd:)
				(leftPoliceTape cel: 0 cycleSpeed: 10 setCycle: End self)
			)
			(1
				(leftPoliceTape stopUpd:)
				(= seconds (Random 3 6))
			)
			(2
				(rightPoliceTape cel: 0 cycleSpeed: 10 setCycle: End self)
			)
			(3
				(rightPoliceTape stopUpd:)
				(= seconds (Random 3 6))
			)
			(4
				(topPoliceTape cel: 0 cycleSpeed: 10 setCycle: End self)
			)
			(5
				(topPoliceTape stopUpd:)
				(= seconds (Random 3 6))
			)
			(6 (self init:))
		)
	)
)

(instance getSomeClay of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 7 12 3 1 self)
			)
			(1
				(ego setMotion: PolyPath 289 103 self)
			)
			(2
				(ego
					view: 4751
					loop: 3
					cel: 0
					setScale: 0
					setCycle: End self
				)
			)
			(3
				(messager say: 7 12 3 2 self)
			)
			(4
				(ego
					normalize: 0 4792
					setScale: Scaler 100 57 109 64
					get: 26
					getPoints: 127 1
				)
				(self cue:)
			)
			(5 (self dispose:))
		)
	)
)

(instance lookAtTheGrass of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath eventX eventY self)
				(= eventY (- eventY 15))
			)
			(1
				(ego
					view: 4751
					loop: 2
					cel: 0
					setScale: Scaler 82 47 119 84
					setCycle: End self
				)
			)
			(2
				(cond 
					((== whichGrass leftGrass) (grassInset init: self rm470))
					((== whichGrass middleGrass) (grassInset init: self rm470))
					((== whichGrass rightGrass) (grassInset init: self rm470))
				)
				(theGame handsOn:)
				(messager say: 14 7 0 0)
			)
			(3
				(theGame handsOff:)
				(ego setCycle: Beg self)
			)
			(4
				(theGame handsOn:)
				(GKEgo normalize: 2 4792)
				(ego setScale: Scaler 100 57 109 64)
				(self dispose:)
			)
		)
	)
)

(instance lookAtSand of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath eventX eventY self)
				(= eventY (- eventY 30))
			)
			(1
				(ego
					view: 4751
					loop: 2
					cel: 0
					setScale: Scaler 82 64 119 84
					setCycle: End self
				)
			)
			(2
				(sandInset init: self rm470)
				(theGame handsOn:)
				(messager say: 16 7 0 0)
			)
			(3
				(theGame handsOff:)
				(ego setCycle: Beg self)
			)
			(4
				(theGame handsOn:)
				(GKEgo normalize: 2 4792)
				(ego setScale: Scaler 100 57 109 64)
				(self dispose:)
			)
		)
	)
)

(instance walkOff of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 11 0 10 0)
				(ego setMotion: PolyPath 169 140 self)
			)
			(1
				(theMusic1 fade: 0 10 25 1)
				(ego setMotion: MoveTo 169 160 self)
			)
			(2
				(if modelessDialog (modelessDialog dispose:))
				(curRoom newRoom: 205)
				(self dispose:)
			)
		)
	)
)

(instance copyThePattern of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 169 124 self)
			)
			(1
				(Face ego patternOnGround self)
			)
			(2
				(messager say: 10 15 5 0)
				(ego
					view: 4751
					loop: 0
					cel: 0
					setScale: 0
					setCycle: End self
				)
			)
			(3
				(ego loop: 1 cel: 0 setCycle: Osc 2 self)
			)
			(4
				(ego view: 4751 loop: 0 cel: 6 setCycle: Beg self)
			)
			(5
				(theGame handsOn:)
				(ego
					get: 28
					normalize: 4 4792
					setScale: Scaler 100 57 109 64
				)
				(self dispose:)
			)
		)
	)
)

(instance lookAtTheCageMarks of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 283 126 self)
			)
			(1
				(ego
					view: 4751
					loop: 2
					cel: 0
					setScale: Scaler 82 47 119 84
					setCycle: End self
				)
			)
			(2
				(theGame handsOn:)
				(cageInset init: self rm470)
				(messager say: 9 4 0 0)
			)
			(3
				(theGame handsOff:)
				(ego setCycle: Beg self)
			)
			(4
				(theGame handsOn:)
				(ego normalize: 0 4792)
				(ego setScale: Scaler 100 57 109 64)
				(self dispose:)
			)
		)
	)
)

(instance grassInset of Inset
	(properties
		view 476
		x 43
		y 64
		disposeNotOnMe 1
		noun 14
	)
	
	(method (init)
		(if (> eventX 255) (= eventX 255))
		(if (< eventX 65) (= eventX 65))
		(Bset 199)
		(self x: eventX y: (= eventY (- eventY 30)))
		(super init: &rest)
		(magFrame
			init:
			setPri: 13
			stopUpd:
			x: (= eventX (+ eventX 48))
			y: (= eventY (+ eventY 37))
		)
	)
	
	(method (dispose)
		(Bclr 199)
		(super dispose: &rest)
		(magFrame dispose:)
		(if (theGame keepBar?) (theIconBar draw:))
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(7
				(messager say: 14 theVerb 0 0)
			)
			(12
				(messager say: 14 theVerb 0 0)
			)
			(3
				(messager say: 14 theVerb 0 0)
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (refresh)
		(if view
			(DrawPic 470 dpOPEN_NO_TRANSITION)
		else
			(DrawPic 470 style)
		)
		(curRoom style: oldStyle)
		(if (!= overlays -1)
			(DrawPic overlays dpOPEN_NO_TRANSITION FALSE)
		)
		(if (curRoom inset:) ((curRoom inset:) restore:))
	)
)

(instance cageInset of Inset
	(properties
		view 477
		x 241
		y 90
		disposeNotOnMe 1
		noun 14
	)
	
	(method (init)
		(Bset 199)
		(super init: &rest)
		(cageMarks init:)
		(magFrame init: setPri: 13 stopUpd: posn: 289 128)
		(if (not (ego has: 21)) (lakeScale init:))
	)
	
	(method (dispose)
		(Bclr 199)
		(cageMarks dispose:)
		(magFrame dispose:)
		(if (not (ego has: 21)) (lakeScale dispose:))
		(super dispose:)
		(if (theGame keepBar?) (theIconBar draw:))
	)
	
	(method (refresh)
		(if view
			(DrawPic 470 dpOPEN_NO_TRANSITION)
		else
			(DrawPic 470 style)
		)
		(curRoom style: oldStyle)
		(if (!= overlays -1)
			(DrawPic overlays dpOPEN_NO_TRANSITION FALSE)
		)
		(if (curRoom inset:) ((curRoom inset:) restore:))
	)
)

(instance cageMarks of Feature
	(properties
		y 91
		noun 12
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 191 74 205 58 224 47 253 46 276 57 288 70 251 106 188 90
					yourself:
				)
		)
		(super init:)
	)
)

(instance sandInset of Inset
	(properties
		view 4771
		disposeNotOnMe 1
		noun 16
	)
	
	(method (init)
		(Bset 199)
		(if (< eventY 80) (= eventY 80))
		(self x: eventX y: eventY)
		(super init: &rest)
		(magFrame
			init:
			setPri: 13
			stopUpd:
			x: (= eventX (+ eventX 48))
			y: (= eventY (+ eventY 39))
		)
	)
	
	(method (dispose)
		(Bclr 199)
		(magFrame dispose:)
		(super dispose:)
		(if (theGame keepBar?) (theIconBar draw:))
	)
	
	(method (refresh)
		(if view
			(DrawPic 470 dpOPEN_NO_TRANSITION)
		else
			(DrawPic 470 style)
		)
		(curRoom style: oldStyle)
		(if (!= overlays -1)
			(DrawPic overlays dpOPEN_NO_TRANSITION FALSE)
		)
		(if (curRoom inset:) ((curRoom inset:) restore:))
	)
)

(instance coroner of Actor
	(properties
		x 98
		y 86
		view 470
		loop 2
	)
)

(instance mosley of Actor
	(properties
		x 192
		y 116
		view 472
		loop 5
	)
)

(instance polCar1 of Actor
	(properties
		x 188
		y 79
		view 4702
		loop 2
		signal $0001
	)
)

(instance polCar2 of Actor
	(properties
		x 153
		y 72
		view 470
		loop 5
		signal $0001
	)
	
	(method (init)
		(self setPri: 3)
		(super init:)
	)
)

(instance ambulance of Actor
	(properties
		x 76
		y 51
		view 470
		signal $0001
	)
	
	(method (init)
		(self setPri: 5)
		(super init:)
	)
)

(instance photoCop of Actor
	(properties
		x 91
		y 133
		view 471
		loop 3
	)
)

(instance kneelingCop of Actor
	(properties
		x 283
		y 117
		view 4712
		loop 6
	)
)

(instance writingCop of Actor
	(properties
		x 145
		y 119
		view 4711
	)
)

(instance ambulanceLights of Prop
	(properties
		x 76
		y 51
		view 470
		loop 1
		signal $4000
	)
	
	(method (init)
		(self setPri: 6 setCycle: Fwd)
		(super init:)
	)
)

(instance polCar1Lights of Prop
	(properties
		x 188
		y 79
		view 4702
		loop 1
		signal $4000
	)
	
	(method (init)
		(self setCycle: Fwd)
		(super init:)
	)
)

(instance polCar2Lights of Prop
	(properties
		x 153
		y 72
		view 470
		loop 6
		signal $4000
	)
	
	(method (init)
		(self setCycle: Fwd)
		(super init:)
	)
)

(instance leftPoliceTape of Prop
	(properties
		x 37
		y 117
		noun 15
		view 4701
		signal $1001
	)
	
	(method (init)
		(super init:)
		(if (not (== prevRoomNum 471))
			(self setScript: moveTheTape)
		)
	)
)

(instance rightPoliceTape of Prop
	(properties
		x 299
		y 69
		noun 15
		view 4701
		loop 2
		signal $1001
	)
	
	(method (init)
		(super init:)
		(self setPri: 13)
	)
)

(instance topPoliceTape of Prop
	(properties
		x 82
		y 94
		noun 15
		view 4701
		loop 1
		signal $1001
	)
	
	(method (init)
		(super init:)
		(self setPri: 6)
	)
)

(instance limo of Prop
	(properties
		x 4
		y 86
		view 473
	)
)

(instance limoWindow of Prop
	(properties
		x 4
		y 86
		view 473
		loop 1
	)
)

(instance deadBody of Prop
	(properties
		x 110
		y 121
		view 4700
	)
)

(instance mosleyHead of Prop
	(properties
		x 78
		y 96
		view 472
	)
)

(instance lakeScale of View
	(properties
		x 243
		y 91
		noun 13
		view 477
		loop 1
	)
	
	(method (init)
		(self setPri: 15)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(= local4 1)
				(Bset 40)
				(ego get: 21 getPoints: 128 2)
				(cageInset dispose:)
				(super doVerb: theVerb)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance magFrame of View
	(properties
		view 1
		signal $1000
	)
)

(instance patternOnGround of View
	(properties
		x 187
		y 119
		noun 10
		sightAngle 20
		view 4700
		loop 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(15
				(if (ego has: 28)
					(messager say: noun theVerb 6 0)
				else
					(ego setScript: copyThePattern)
				)
			)
			(7
				(messager say: noun theVerb 0 0)
				(Bset 109)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance lake of Feature
	(properties
		x 247
		y 67
		noun 2
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init:
						319
						82
						274
						80
						240
						72
						207
						68
						198
						64
						230
						60
						229
						57
						191
						50
						162
						48
						146
						46
						89
						46
						95
						44
						318
						43
					yourself:
				)
		)
		(super init:)
	)
)

(instance road of Feature
	(properties
		x 15
		y 89
		noun 3
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 1 68 66 86 80 93 74 101 0 136
					yourself:
				)
		)
		(super init:)
	)
)

(instance clayBank of Feature
	(properties
		x 267
		y 200
		noun 7
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 299 85 303 92 305 98 274 92 242 83 232 74 249 76 262 80 266 83
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(12
				(if (not (ego has: 26))
					(ego setScript: getSomeClay)
				else
					(messager say: noun theVerb 4 0)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance reflectorClose of Feature
	(properties
		x 40
		y 132
		noun 6
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 37 134 36 125 34 125 34 109 41 109 41 123 40 134
					yourself:
				)
		)
		(super init:)
	)
)

(instance reflectorMid of Feature
	(properties
		x 74
		y 84
		noun 6
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 72 85 72 80 70 80 70 71 75 71 75 85
					yourself:
				)
		)
		(super init:)
	)
)

(instance reflectorFar of Feature
	(properties
		x 37
		y 74
		noun 6
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 37 74 33 72 33 67 38 67
					yourself:
				)
		)
		(super init:)
	)
)

(instance rightTree of Feature
	(properties
		x 318
		y 117
		noun 4
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init:
						319
						128
						309
						120
						311
						103
						303
						93
						300
						81
						279
						70
						272
						69
						267
						59
						255
						55
						254
						47
						224
						51
						208
						43
						175
						22
						186
						22
						202
						30
						202
						22
						238
						22
						235
						25
						208
						27
						208
						33
						224
						42
						245
						37
						257
						39
						263
						32
						271
						29
						267
						25
						258
						29
						258
						22
						319
						22
						313
						31
						280
						31
						278
						36
						269
						44
						285
						57
						309
						66
						319
						77
					yourself:
				)
		)
		(super init:)
	)
)

(instance leftTree of Feature
	(properties
		x 46
		y 38
		noun 4
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init:
						109
						47
						112
						42
						73
						43
						69
						46
						68
						57
						54
						66
						45
						52
						38
						54
						28
						73
						21
						66
						0
						83
						0
						22
						139
						22
						151
						35
						117
						44
						120
						48
					yourself:
				)
		)
		(super init:)
	)
)

(instance leftGrass of Feature
	(properties
		x 85
		y 140
		noun 8
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init:
						45
						139
						50
						136
						54
						134
						61
						136
						69
						133
						74
						129
						78
						132
						88
						131
						93
						126
						94
						131
						98
						131
						104
						135
						112
						128
						116
						133
						127
						126
						142
						132
						145
						137
						138
						145
						41
						145
					yourself:
				)
		)
		(super init:)
	)
	
	(method (handleEvent event)
		(if (== (event message?) JOY_DOWNRIGHT)
			(= eventX (event x?))
			(= eventY (event y?))
		)
		(super handleEvent: event &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(= whichGrass leftGrass)
				(ego setScript: lookAtTheGrass)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance middleGrass of Feature
	(properties
		x 157
		y 131
		noun 8
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init:
						147
						133
						139
						128
						146
						124
						152
						127
						154
						117
						158
						117
						160
						124
						164
						119
						164
						128
						169
						122
						169
						128
						163
						133
						162
						137
						147
						137
					yourself:
				)
		)
		(super init:)
	)
	
	(method (handleEvent event)
		(if (== (event message?) JOY_DOWNRIGHT)
			(= eventX (event x?))
			(= eventY (event y?))
		)
		(super handleEvent: event &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(= whichGrass middleGrass)
				(ego setScript: lookAtTheGrass)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance rightGrass of Feature
	(properties
		x 280
		y 131
		noun 8
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init:
						212
						137
						208
						131
						215
						129
						221
						124
						228
						127
						235
						120
						238
						128
						234
						138
						237
						139
						241
						139
						247
						121
						256
						126
						269
						114
						283
						114
						294
						115
						311
						109
						309
						121
						319
						128
						319
						145
						208
						144
					yourself:
				)
		)
		(super init:)
	)
	
	(method (handleEvent event)
		(if (== (event message?) JOY_DOWNRIGHT)
			(= eventX (event x?))
			(= eventY (event y?))
		)
		(super handleEvent: event &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(= whichGrass rightGrass)
				(ego setScript: lookAtTheGrass)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance cageGrass of Feature
	(properties
		x 302
		y 200
		noun 9
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 307 118 314 128 295 135 286 119
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(ego setScript: lookAtTheCageMarks)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance sand of Feature
	(properties
		noun 17
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 151 78 221 82 245 102 233 122 188 123 141 121 102 130 108 109 104 91
					yourself:
				)
		)
		(super init:)
	)
	
	(method (handleEvent event)
		(if (== (event message?) JOY_DOWNRIGHT)
			(= eventX (event x?))
			(= eventY (event y?))
		)
		(super handleEvent: event &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4 (ego setScript: lookAtSand))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance myExitFeature of Feature
	(properties
		y 200
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 141 136 207 136 207 149 141 148
					yourself:
				)
				((Polygon new:)
					type: 0
					init: 0 145 0 134 45 134 45 145
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13
				(if (== prevRoomNum 471)
					(ego setScript: walkOff)
				else
					(ego setScript: walkOff)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)
