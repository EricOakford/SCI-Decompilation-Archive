;;; Sierra Script 1.0 - (do not remove this comment)
(script# 20600)
(include sci.sh)
(use Main)
(use ScrollExit)
(use TPRoom)
(use TPScript)
(use TPSound)
(use Script)
(use ExitFeature)
(use Plane)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)

(public
	roSeraglio 0
)

(local
	local0
	local1
	theSel_15 =  2
)
(procedure (localproc_2946)
	(oHaremTalk stop:)
	(= local1 0)
	(soHaremGirlsChatter changeState: 1)
)

(procedure (localproc_2979)
	(oHaremTalk stop:)
	(oChatter stop:)
	(soHaremGirlsChatter dispose:)
	(= local1 0)
)

(procedure (localproc_29b8)
	(poNull setScript: soHaremGirlsChatter)
)

(instance foToCliffExit of ExitFeature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 6))
		(= nsTop 0)
		(= nsBottom 314)
		(= nsLeft 0)
		(= nsRight 30)
	)
	
	(method (doVerb)
		(if local0
			(curRoom setScript: soBoogleBackUpAndExit)
		else
			(ego setScript: soClimbOut)
		)
	)
)

(instance foExitCU of CUExitFeature
	(properties)
	
	(method (doVerb)
		(curRoom arrowDown: oSignCUPlane)
	)
)

(instance soClimbIn of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(poTorin
					view: 20600
					loop: 0
					cel: 0
					posn: 102 280
					init:
					setCycle: End self
				)
			)
			(1
				(poTorin dispose:)
				(ego posn: 102 280 oPanner: 1 -5436 4 show:)
				(theGame handsOn:)
			)
		)
	)
)

(instance soClimbOut of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 106 281 self)
			)
			(1
				(theGame handsOff:)
				(ego setHeading: 315 self)
			)
			(2
				(ego hide:)
				(poTorin
					view: 20600
					loop: 1
					cel: 0
					posn: 102 280
					init:
					setCycle: End self
				)
			)
			(3
				(curRoom newRoom: 20100)
				(self dispose:)
			)
		)
	)
)

(instance poTorin of Prop
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setPri: -1)
	)
)

(instance poBoogle of Prop
	(properties)
)

(instance voTableTop of View
	(properties
		x 366
		y 272
		view 20611
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
		(= signal (| signal $1000))
	)
	
	(method (doVerb)
		(ego setScript: soTakeTableTop)
	)
)

(instance soTakeTableTop of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 362 288 self)
			)
			(1
				(theGame handsOff:)
				(ego setHeading: 0 self)
			)
			(2
				(self setScript: (ScriptID 64007 2) self self)
			)
			(3
				(ego get: ((ScriptID 64001 0) get: 18))
				((ScriptID 64017 0) set: 46)
				(voTableTop dispose:)
			)
			(4
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance voPillow of View
	(properties
		x 664
		y 297
		view 20613
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
		(= signal (| signal $1000))
	)
	
	(method (doVerb)
		(ego setScript: soTakePillow)
	)
)

(instance soTakePillow of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 591 288 self)
			)
			(1
				(theGame handsOff:)
				(ego setHeading: 135 self)
			)
			(2
				(ego hide:)
				(voPillow dispose:)
				(poTorin
					view: 20601
					loop: 2
					cel: 0
					posn: 591 288
					init:
					setCycle: End self
				)
			)
			(3
				(poTorin dispose:)
				(ego show:)
				(ego get: ((ScriptID 64001 0) get: 27))
				((ScriptID 64017 0) set: 55)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance voFan of View
	(properties
		x 565
		y 294
		view 20615
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
		(= signal (| signal $1000))
	)
	
	(method (doVerb)
		(ego setScript: soTakeFan)
	)
)

(instance soTakeFan of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 620 294 self)
			)
			(1
				(theGame handsOff:)
				(ego setHeading: 225 self)
			)
			(2
				(ego hide:)
				(poTorin
					view: 20601
					loop: 0
					cel: 0
					posn: 620 294
					init:
					setCycle: CT 4 1 self
				)
			)
			(3
				(voFan dispose:)
				(poTorin setCycle: End self)
			)
			(4
				(poTorin dispose:)
				(ego posn: 630 296 oPanner: 1 -5436 2 show:)
				(ego get: ((ScriptID 64001 0) get: 30))
				((ScriptID 64017 0) set: 58)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance foLedge of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 39 46)
		(if ((ScriptID 64017 0) test: 90)
			(self setVisibleRange: 1)
		)
		(= noun 8)
		(self setRect: 82 410 158 477)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(39
				(ego setScript: soDropStinkyCarpet)
			)
			(40
				(if ((ScriptID 64017 0) test: 90)
					(ego setScript: soFanStink)
				else
					(MonoOut {Forgot to remove hotspot for fan on ledge.})
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance soDropStinkyCarpet of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 117 269 self)
			)
			(1
				(theGame handsOff:)
				(ego setHeading: 225 self)
				((curRoom plane?) sitNSpin: 0 0 self 1)
			)
			(2)
			(3
				(ego hide:)
				(poTorin
					view: 20600
					loop: 2
					cel: 0
					posn: 117 269
					init:
					setPri: 500
					setCycle: End self
				)
			)
			(4
				((ScriptID 64017 0) set: 90)
				(ego put: ((ScriptID 64001 0) get: 29) 20600)
				(voStinkyCarpetOnLedge init:)
				((curRoom plane?) sitNSpin: -32000 264 self 1)
			)
			(5
				(= ticks 60)
				(ego posn: 120 270 oPanner: 1 -5436 2 show:)
				(poTorin dispose:)
			)
			(6
				((curRoom plane?) sitNSpin: 0 0 self 1)
			)
			(7
				(foLedge setVisibleRange: 40)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance voStinkyCarpetOnLedge of View
	(properties
		x 54
		y 489
		priority 446
		fixPriority 1
		view 20614
		loop 1
	)
	
	(method (init)
		(super init: &rest)
		(poCarpetStench init:)
	)
)

(instance poCarpetStench of Prop
	(properties
		x 115
		y 474
		priority 450
		fixPriority 1
		view 20614
		loop 2
		cycleSpeed 8
	)
	
	(method (init)
		(super init: &rest)
		(self setCycle: Fwd)
	)
	
	(method (doit)
		(if (and (!= loop theSel_15) (== cel 0))
			(= loop theSel_15)
		)
		(super doit: &rest)
	)
)

(instance soFanStink of TPScript
	(properties
		oMessageList 1
		nTextWidth 1
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(((ScriptID 64001 0) get: 30) moveTo: -3)
				(ego setMotion: PolyPath 122 268 self)
			)
			(1
				(theGame handsOff:)
				(ego setHeading: 180 self)
			)
			(2
				(ego hide:)
				(poTorin
					view: 20600
					loop: 3
					cel: 0
					posn: 120 217
					init:
					setPri: 500
					setCycle: CT 23 1 self
				)
			)
			(3
				(localproc_2946)
				(= local1 1)
				(messager say: 8 40 11 1 self)
			)
			(4
				(= local1 0)
				(poTorin setCycle: CT 33 1 self)
			)
			(5
				(poTorin setScript: soKeepTorinFanning)
				(= theSel_15 3)
				((curRoom plane?) sitNSpin: -32000 288 self 2 6 7)
			)
			(6 (= ticks 60))
			(7
				((curRoom plane?) sitNSpin: 264 -32000 self 2 6 7)
			)
			(8
				(poGirl1 setScript: soGirl1Runs self)
				(poGirl2 setScript: soGirl2Runs self)
				(poGirl3 setScript: soGirl3Runs self)
				(poGirl4 setScript: soGirl4Runs self)
				(= theSel_15 2)
				(poTorin dispose:)
				(ego put: ((ScriptID 64001 0) get: 30))
				(ego show:)
			)
			(9)
			(10)
			(11)
			(12
				((curRoom plane?) sitNSpin: 0 0 self 1 8 7)
			)
			(13
				(messager say: 8 40 11 8 self)
			)
			(14
				((ScriptID 64017 0) set: 91)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
	
	(method (rememberMessage)
		(localproc_2979)
		(poGirl1 dispose:)
		(poGirl2 dispose:)
		(poGirl3 dispose:)
		(poGirl4 dispose:)
		(poTorin dispose:)
		(ego posn: 122 268 show:)
		((curRoom plane?) fadeRel: 0 0)
		(sound1 stop:)
		((ScriptID 64017 0) set: 91)
		(ego put: ((ScriptID 64001 0) get: 30))
		(self dispose:)
	)
	
	(method (sayMessage)
		(poGirl1
			view: 20608
			loop: 0
			cel: 0
			posn: 620 446
			init:
			setScript: 0
		)
		(poGirl2
			view: 20608
			loop: 2
			cel: 0
			posn: 407 454
			init:
			setPri: 100
			setScript: 0
		)
		(poGirl3
			view: 20608
			loop: 4
			cel: 0
			posn: 492 449
			init:
			setScript: 0
		)
		(poGirl4
			view: 20608
			loop: 6
			cel: 0
			posn: 568 452
			init:
			setScript: 0
		)
		(sound1 stop:)
		(localproc_29b8)
		(poTorin dispose:)
		(ego posn: 122 268 show:)
		((curRoom plane?) fadeRel: 0 0)
		(ego setScript: soFanStink)
	)
)

(instance soGirl1Runs of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(poGirl1
					view: 20608
					loop: 0
					cel: 0
					posn: 620 446
					setCycle: CT 5 1 self
				)
			)
			(1
				(localproc_2979)
				(sound1 lThumbLoop: 20606)
				(poGirl1 setCycle: End self)
			)
			(2
				(poGirl1 loop: 1 cel: 0 posn: 675 485 setCycle: End self)
			)
			(3 (poGirl1 dispose:))
		)
	)
)

(instance soGirl2Runs of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(poGirl2
					view: 20608
					loop: 2
					cel: 0
					posn: 407 454
					setCycle: CT 21 1 self
				)
			)
			(1
				(poGirl2 setPri: 500 setCycle: End self)
			)
			(2
				(poGirl2
					loop: 3
					cel: 0
					posn: 407 519
					setCycle: CT 14 1 self
				)
			)
			(3
				(poGirl2 setPri: 5 setCycle: End self)
			)
			(4 (poGirl2 dispose:))
		)
	)
)

(instance soGirl3Runs of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(poGirl3
					view: 20608
					loop: 4
					cel: 0
					posn: 492 449
					setCycle: End self
				)
			)
			(1
				(poGirl3 loop: 5 cel: 0 posn: 627 519 setCycle: End self)
			)
			(2 (poGirl3 dispose:))
		)
	)
)

(instance soGirl4Runs of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(poGirl4
					view: 20608
					loop: 6
					cel: 0
					posn: 568 452
					setCycle: End self
				)
			)
			(1
				(poGirl4 loop: 7 cel: 0 posn: 570 452 setCycle: End self)
			)
			(2 (poGirl4 dispose:))
		)
	)
)

(instance soKeepTorinFanning of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(poTorin cel: 34 setCycle: CT 39 1 self)
			)
			(1 (self changeState: 0))
		)
	)
)

(instance voCarpet of View
	(properties
		x 546
		y 283
		priority 10
		fixPriority 1
		view 20614
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
		(= signal (| signal $1000))
	)
	
	(method (doVerb)
		(ego setScript: soTakeCarpet)
	)
)

(instance soTakeCarpet of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 459 289 self)
			)
			(1
				(theGame handsOff:)
				(ego setHeading: 45 self)
			)
			(2
				(ego hide:)
				(poTorin
					view: 20601
					loop: 1
					cel: 0
					posn: 459 289
					init:
					setCycle: CT 7 1 self
				)
			)
			(3
				(voCarpet dispose:)
				(poTorin setCycle: End self)
			)
			(4
				(ego show:)
				(poTorin dispose:)
				(ego get: ((ScriptID 64001 0) get: 28))
				((ScriptID 64017 0) set: 56)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance voLadder of View
	(properties
		x 834
		y 294
		view 20610
	)
	
	(method (init)
		(super init: &rest)
		(= signal (| signal $1000))
		(self setPri: 10)
	)
	
	(method (doVerb)
		(foLadderHole doVerb: &rest)
	)
	
	(method (setSpeedDirect)
		(foLadderHole setSpeedDirect: &rest)
	)
)

(instance foLadderHole of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1 46)
		(= onMeCheck
			((Polygon new:)
				type: 0
				init: 814 274 914 268 927 283 932 292 830 291
				yourself:
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(ego setScript: soLadderDeath)
			)
			(46
				(ego setScript: soYoYoBoogle)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance soLadderDeath of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 836 292 self)
			)
			(1
				(theGame handsOff:)
				(ego setHeading: 45 self)
			)
			(2
				(localproc_2946)
				(= local1 1)
				(messager sayRange: 7 1 0 1 2 self)
			)
			(3
				(= local1 0)
				(ego hide:)
				(voLadder hide:)
				(poTorin
					view: 20603
					loop: 0
					cel: 0
					posn: 834 294
					init:
					setCycle: CT 10 1 self
				)
			)
			(4
				((curRoom plane?) sitNSpin: -32000 177 0 1)
				(poTorin setPri: 10 setCycle: End self)
				(sound1 lThumbLoop: 20601 self)
				(if (not ((ScriptID 64017 0) test: 91))
					(localproc_2946)
				)
			)
			(5)
			(6
				(sound1 lThumbLoop: 20602 self)
			)
			(7
				(theGame handsOn:)
				(if ((ScriptID 64019 0) show: 0 42 13)
					(poTorin dispose:)
					(voLadder show:)
					(if (not ((ScriptID 64017 0) test: 91))
						(localproc_29b8)
					)
					(ego show:)
					((curRoom plane?) fadeRel: 2000 0)
				)
				(self dispose:)
			)
		)
	)
)

(instance soYoYoBoogle of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(((ScriptID 64001 1) get: 3) moveTo: -3)
				(ego setMotion: PolyPath 780 290 self)
			)
			(1
				(theGame handsOff:)
				(ego setHeading: 90 self)
			)
			(2
				(localproc_2946)
				(= local1 1)
				(messager say: 7 46 1 0 self)
			)
			(3
				(= local1 0)
				(ego hide:)
				(poTorin
					view: 20603
					loop: 1
					cel: 0
					posn: 782 290
					init:
					setCycle: CT 10 1 self
				)
			)
			(4
				(poTorin setCycle: End self)
				((curRoom plane?) sitNSpin: -32000 316 0 1 5 5)
			)
			(5
				(poTorin dispose:)
				(ego show:)
				(poBoogle
					view: 20603
					loop: 2
					cel: 0
					posn: 670 497
					init:
					setCycle: End self
				)
			)
			(6
				(poBoogle dispose:)
				((ScriptID 64018 0)
					bSwing: 0
					posn: 670 497
					setLoop: 1
					show:
				)
				(if (not ((ScriptID 64017 0) test: 91))
					(self setScript: soGirlsPlayBall self)
				else
					((ScriptID 64017 0) set: 92)
					(= local0 1)
					(foBackToTop init:)
					(self cue:)
				)
			)
			(7
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soGirlsPlayBall of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_2979)
				(poGirl1 setScript: 0 view: 20604 loop: 3 cel: 0)
				(poGirl2 setScript: 0 view: 20604 loop: 0 cel: 0)
				(poGirl3 setScript: 0 view: 20604 loop: 1 cel: 0)
				(poGirl4 setScript: 0 view: 20604 loop: 2 cel: 0)
				((ScriptID 64018 0) setMotion: MoveTo 645 524)
				(poGirl3 loop: 4 cel: 0 setCycle: CT 13 1 self)
			)
			(1
				((ScriptID 64018 0) hide:)
				(poGirl3 cel: 14)
				(poGirl3 setCycle: End self)
			)
			(2
				(poGirl1 loop: 6 cel: 0 setCycle: CT 6 1 self)
			)
			(3
				(poGirl1 setCycle: End self)
				(poGirl3 loop: 5 cel: 0 setCycle: End self)
			)
			(4)
			(5
				(poGirl2 loop: 7 cel: 0 setCycle: CT 6 1 self)
			)
			(6
				(voGongBonger hide:)
				(poGirl2 setCycle: End self)
			)
			(7
				(poGirl1 loop: 8 cel: 0 setCycle: CT 6 1 self)
			)
			(8
				(poGirl1 setCycle: End self)
				(poGirl2 loop: 9 cel: 0 setCycle: End self)
			)
			(9)
			(10
				(if ((ScriptID 64019 0) show: 0 42 1)
					((curRoom plane?) fadeRel: 400 0)
					((ScriptID 64018 0) hide:)
					(voGongBonger show:)
					(poGirl1
						view: 20606
						loop: 7
						cel: 0
						setScript: (soWaitRandomForFidget new:)
					)
					(poGirl2
						view: 20606
						loop: 4
						cel: 0
						setScript: (soWaitRandomForFidget new:)
					)
					(poGirl3
						view: 20606
						loop: 5
						cel: 0
						setScript: (soWaitRandomForFidget new:)
					)
					(poGirl4
						view: 20606
						loop: 6
						cel: 0
						setScript: (soWaitRandomForFidget new:)
					)
					(localproc_29b8)
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance foBackToTop of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
		(self setSpeedFraction: (ScriptID 64006 1))
		(= onMeCheck
			((Polygon new:)
				type: 0
				init: 725 452 727 389 741 374 755 374 753 445
				yourself:
			)
		)
	)
	
	(method (doVerb)
		(ego setScript: soBoogleBackUp)
	)
)

(instance soBoogleBackUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((curRoom plane?) nInitCursorY: foBackToTop)
				(messager say: 10 1 10 0 self)
			)
			(1
				((ScriptID 64018 0)
					bSwing: 0
					setPri: 0
					setMotion: PolyPath 708 474 self
				)
			)
			(2
				((ScriptID 64018 0) setMotion: MoveTo 780 451 self)
			)
			(3
				(sound1 lThumbLoop: 20607 self)
				(ego setMotion: MoveTo 608 277 self)
				((curRoom plane?) sitNSpin: 216 0 self 2)
				((ScriptID 64018 0) hide: setPri: -1)
			)
			(4)
			(5)
			(6
				(ego setHeading: 270 self)
				(poBoogle
					view: 20603
					loop: 4
					cel: 0
					posn: 532 277
					init:
					setCycle: End self
				)
			)
			(7)
			(8
				(poBoogle dispose:)
				((ScriptID 64018 0) posn: 532 277 bSwing: 0 show:)
				(= local0 0)
				(self setScript: (ScriptID 64018 1) self)
			)
			(9
				(foBackToTop dispose:)
				(if ((ScriptID 64017 0) test: 89)
					(ego get: ((ScriptID 64001 0) get: 20))
					((ScriptID 64017 0) set: 48)
				else
					((ScriptID 64017 0) clear: 92)
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soBoogleBackUpAndExit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setScript: soBoogleBackUp self)
			)
			(1 (foToCliffExit doVerb: 1))
		)
	)
)

(instance soWaitRandomForFidget of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks (Random 200 400)))
			(1
				(client setScript: (soFidgetGirls new:))
			)
		)
	)
)

(instance soFidgetGirls of Script
	(properties)
	
	(method (dispose)
		(client setCycle: 0)
		(super dispose: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (client setCycle: End self))
			(1
				(client cel: 0)
				(client setScript: (soWaitRandomForFidget new:))
			)
		)
	)
)

(instance poGirl1 of Prop
	(properties
		x 620
		y 446
		view 20606
		loop 7
	)
	
	(method (init)
		(super init: &rest)
		(self setScript: (soWaitRandomForFidget new:))
		(self setPri: 4)
	)
)

(instance poGirl2 of Prop
	(properties
		x 407
		y 454
		view 20606
		loop 4
	)
	
	(method (init)
		(super init: &rest)
		(self setScript: (soWaitRandomForFidget new:))
		(self setPri: 100)
	)
)

(instance poGirl3 of Prop
	(properties
		x 492
		y 449
		view 20606
		loop 5
	)
	
	(method (init)
		(super init: &rest)
		(self setScript: (soWaitRandomForFidget new:))
		(self setPri: 5)
	)
)

(instance poGirl4 of Prop
	(properties
		x 568
		y 452
		view 20606
		loop 6
	)
	
	(method (init)
		(super init: &rest)
		(self setScript: (soWaitRandomForFidget new:))
		(self setPri: 3)
	)
)

(instance voWarningSign of View
	(properties
		x 913
		y 246
		view 20612
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
		(= signal (| signal $1000))
	)
	
	(method (doVerb)
		(ego setScript: soShowSign)
	)
)

(instance soShowSign of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 850 288 self)
			)
			(1 (= cycles 1))
			(2
				(curRoom initThumb: oSignCUPlane)
				(self dispose:)
			)
		)
	)
)

(instance voGongBonger of View
	(properties
		x 407
		y 454
		view 20616
	)
	
	(method (init)
		(super init: &rest)
		(self setPri: 1)
	)
)

(instance voGongTile of View
	(properties
		x 568
		y 514
		view 20605
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
		(= signal (| signal $1000))
		(self setPri: 1)
	)
	
	(method (doVerb)
		(ego setScript: soTakeGong)
	)
	
	(method (setSpeedDirect)
		(return
			(if local0
				(return (super setSpeedDirect: &rest))
			else
				(return 0)
			)
		)
	)
)

(instance soTakeGong of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 4 1 12 1 self)
			)
			(1
				((ScriptID 64018 0) setMotion: PolyPath 568 514 self)
			)
			(2
				((ScriptID 64018 0) setHeading: 315 self)
			)
			(3
				((ScriptID 64018 0) hide:)
				(poBoogle view: 20603 loop: 3 cel: 0 posn: 568 514 init:)
				(poBoogle setCycle: CT 25 1 self)
			)
			(4
				(voGongTile dispose:)
				(messager say: 4 1 12 2)
				(poBoogle setCycle: End self)
			)
			(5
				(poBoogle dispose:)
				((ScriptID 64018 0) posn: 666 513 setLoop: 4 show:)
				((ScriptID 64017 0) set: 89)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance oChatter of TPSound
	(properties)
)

(instance oHaremTalk of TPSound
	(properties)
)

(instance poNull of Prop
	(properties)
)

(instance soHaremGirlsChatter of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(oChatter vThumbView: 20605 cThumbCel: 50)
				(oHaremTalk cThumbCel: 50)
				(self cue:)
			)
			(1 (= ticks (Random 200 300)))
			(2
				(if local1 (self changeState: 1))
				(= local1 1)
				(oHaremTalk
					lDownArrowLoop: 0 0 1 (Random 1 26) self 20600 1
				)
			)
			(3
				(= local1 0)
				(self changeState: 1)
			)
		)
	)
)

(instance voSignBG of View
	(properties
		view 20602
	)
)

(instance foSignCU of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
		(= onMeCheck
			((Polygon new:)
				type: 0
				init: 460 33 546 124 468 223 384 132
				yourself:
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(curRoom setScript: soTakeSign)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance soTakeSign of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 1))
			(1
				(ego get: ((ScriptID 64001 0) get: 19))
				((ScriptID 64017 0) set: 47)
				(voWarningSign dispose:)
				(curRoom arrowDown: oSignCUPlane)
				(self dispose:)
			)
		)
	)
)

(instance oSignCUPlane of Plane
	(properties
		priority 20
	)
	
	(method (init)
		(super
			init:
				(thePlane left:)
				(thePlane top?)
				(thePlane right:)
				(thePlane bottom?)
		)
		(voSignBG init:)
		(foSignCU init:)
		(foExitCU init:)
	)
)

(instance oSeraglioScrollPlane of TorScrollPlane
	(properties
		priority 20
	)
	
	(method (nSeq)
		(AddPicAt self 20600 0 0)
		(AddPicAt self 20601 474 0)
		(AddPicAt self 20603 0 316)
		(AddPicAt self 20602 474 316)
	)
	
	(method (oText)
	)
)

(instance roSeraglio of TPRoom
	(properties)
	
	(method (init)
		(super init: &rest)
		(= plane (oSeraglioScrollPlane init: 948 632 yourself:))
		(music1 pageSize: 20600)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						91
						279
						300
						283
						544
						292
						691
						296
						948
						303
						929
						262
						683
						280
						413
						258
						369
						274
						298
						253
						102
						249
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 814 274 914 268 927 283 932 292 830 291
					yourself:
				)
		)
		(if (not ((ScriptID 64017 0) test: 46))
			(voTableTop init:)
		)
		(if (not ((ScriptID 64017 0) test: 55))
			(voPillow init:)
		)
		(if (not ((ScriptID 64017 0) test: 47))
			(voWarningSign init:)
		)
		(if (not ((ScriptID 64017 0) test: 58)) (voFan init:))
		(if (not ((ScriptID 64017 0) test: 56))
			(voCarpet init:)
		)
		(if (not ((ScriptID 64017 0) test: 89))
			(voGongTile init:)
		)
		(if (not ((ScriptID 64017 0) test: 91))
			(poGirl1 init:)
			(poGirl2 init:)
			(poGirl3 init:)
			(poGirl4 init:)
			(poNull setScript: soHaremGirlsChatter)
		)
		(if ((ScriptID 64017 0) test: 90)
			(voStinkyCarpetOnLedge init:)
		)
		(foLadderHole init:)
		(voLadder init:)
		(foLedge init:)
		(voGongBonger init:)
		(foToCliffExit init:)
		(ego init: oPanner:)
		((ScriptID 64018 0)
			posn: 146 276
			init:
			oPanner:
			bSwing: 0
			hide:
		)
		(theGame handsOn:)
		(ego posn: 102 280 hide:)
		(ego setScript: soClimbIn)
	)
	
	(method (doit)
		(super doit: &rest)
		(if (poNull script?) ((poNull script?) doit:))
	)
	
	(method (setArgs param1)
		(return
			(if (not (KString 7 param1 {foSignCU}))
				(return foSignCU)
			else
				(super setArgs: param1 &rest)
			)
		)
	)
	
	(method (highlight param1)
		(if
			(and
				(== param1 foSignCU)
				(!= (curRoom plane?) oSignCUPlane)
			)
			(super highlight: voWarningSign)
		else
			(super highlight: param1 &rest)
		)
	)
	
	(method (setWander)
		(return
			(if (== (curRoom plane?) oSignCUPlane)
				(return foExitCU)
			else
				(return foToCliffExit)
			)
		)
	)
	
	(method (intoPouch)
		(ego get: ((ScriptID 64001 1) get: 3))
		(ego get: ((ScriptID 64001 0) get: 29))
		(ego get: ((ScriptID 64001 0) get: 30))
	)
)
