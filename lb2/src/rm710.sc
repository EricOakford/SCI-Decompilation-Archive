;;; Sierra Script 1.0 - (do not remove this comment)
(script# 710)
(include game.sh)
(use Main)
(use LBRoom)
(use Talker)
(use PolyPath)
(use LoadMany)
(use StopWalk)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm710 0
	Rameses_b 2
	Rameses_a 27
)

(instance rm710 of LBRoom
	(properties
		picture 710
		west 700
	)
	
	(method (init)
		(curRoom obstacles: (List new:))
		((ScriptID 2710 0) doit: (curRoom obstacles?))
		(ego
			init:
			normalize: 831
			wearingGown: TRUE
			scaleSignal: scalable
			scaleX: 110
			scaleY: 110
			setPri: 10
			posn: 63 155
		)
		(LoadMany RES_SOUND 710 712 713 714 715 716)
		(LoadMany RES_VIEW 710 714 717 716 994)
		(Load RES_MESSAGE 710)
		(Load RES_FONT 69)
		(super init:)
		(WrapMusic init: 1 1710 1712 1713)
		(wrapMusic2 init: 1 714 715 716)
		(sunnie1 init: setCycle: Forward)
		(sunnie2 init: setCycle: Forward)
		(tut init: setCycle: Walk setLoop: 0)
		(rameses init: setCycle: StopWalk -1)
		(bugsWithMeat init: setLoop: 0 setCycle: Walk)
		(ferret init: setLoop: 5 setCycle: Walk)
		(curRoom setScript: sEnterRoom)
	)
	
	(method (dispose)
		(DisposeScript 2710)
		(super dispose: &rest)
	)
)

(instance sEnterRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((ScriptID 22 0) doit: 1)
				(= ticks 180)
			)
			(1
				(ego setMotion: PolyPath 96 169 self)
			)
			(2
				(ego setPri: -1)
				(messager say: 1 0 0 1)
				(= cycles 1)
			)
			(3
				(sunnie1 setCycle: EndLoop sunnie1)
				(sunnie2 setCycle: EndLoop sunnie2)
			)
			(4)
			(5
				(WrapMusic dispose:)
				(wrapMusic2 dispose: 1)
				(LoadMany RES_SOUND 636 637)
				(LoadMany RES_VIEW 635 632)
				(Load RES_PIC 716)
				(= ticks 60)
			)
			(6
				(ego
					view: 716
					scaleX: 128
					scaleY: 128
					setLoop: 4
					setCel: 0
					cycleSpeed: 12
					setCycle: EndLoop self
				)
				(sunnie1 stopUpd:)
				(sunnie2 stopUpd:)
			)
			(7
				(tut setMotion: PolyPath 107 171 tut)
				(= cycles 35)
			)
			(8
				(messager say: 1 0 0 2)
				(sFXBeetles play: 0 fade: 127 25 10 0)
				(= cycles 1)
			)
			(9
				(bugsWithMeat setMotion: MoveTo 110 188)
				(= ticks 240)
				(rameses setMotion: PolyPath 76 171 self)
			)
			(10
				(ferret setMotion: MoveTo 133 185)
				(sFXFerret play:)
				(sFXBeetles fade:)
			)
			(11
				(rameses setLoop: 3 setCel: 2 setCycle: CycleTo 6 1 self)
			)
			(12
				(rameses
					view: 716
					setLoop: 0
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(13
				(tut stopUpd:)
				(rameses stopUpd:)
				(= ticks 30)
			)
			(14
				(theMusic2 number: 711 flags: 1 loop: -1 play:)
				(theIconBar disable: 7)
				(cast eachElementDo: #hide)
				(curRoom drawPic: 716 9 picture: 716)
				(= cycles 1)
			)
			(15 (= ticks 120))
			(16
				(messager say: 1 0 1)
				(= cycles 1)
			)
			(17 (= ticks 120))
			(18
				(cast eachElementDo: #show)
				(ego dispose:)
				(tut dispose:)
				(rameses dispose:)
				(sunnie1 setCel: 0)
				(sunnie2 setCel: 0)
				(curRoom drawPic: 710 9 picture: 716)
				(= ticks 120)
			)
			(19
				(curRoom newRoom: 715)
				(self dispose:)
			)
		)
	)
)

(instance tut of Actor
	(properties
		x 335
		y 178
		view 717
		signal $4001
	)
	
	(method (cue)
		(messager say: 1 0 0 3)
		(self view: 716 setLoop: 1 setCel: 0 setCycle: EndLoop)
	)
)

(instance rameses of Actor
	(properties
		x 335
		y 178
		view 717
		loop 1
		signal $4001
	)
)

(instance bugsWithMeat of Actor
	(properties
		x -18
		y 168
		view 635
		priority 10
		signal $4010
	)
)

(instance ferret of Actor
	(properties
		x -5
		y 171
		view 632
		loop 5
		signal $4000
		xStep 4
	)
)

(instance sunnie1 of Prop
	(properties
		x 305
		y 184
		view 710
		loop 1
		signal $4001
		cycleSpeed 12
	)
	
	(method (cue)
		(self
			view: 716
			setLoop: 2
			setCel: 0
			setCycle: EndLoop sEnterRoom
		)
	)
)

(instance sunnie2 of Prop
	(properties
		x 282
		y 174
		view 714
		loop 2
		signal $4001
		cycleSpeed 12
	)
	
	(method (cue)
		(self
			view: 716
			setLoop: 3
			setCel: 0
			setCycle: EndLoop sEnterRoom
		)
	)
)

(instance Rameses_a of Talker
	(properties
		x 66
		y 44
		view 1716
		loop 3
		disposeWhenDone 0
		talkWidth 150
		back 15
		textX 20
		textY 60
	)
	
	(method (init)
		(= font userFont)
		(super init: ramesesBust ramesesEyes ramesesMouth &rest)
	)
)

(instance ramesesBust of Prop
	(properties
		view 1716
		loop 1
	)
)

(instance ramesesEyes of Prop
	(properties
		nsTop 20
		nsLeft 12
		view 1716
		loop 2
	)
)

(instance ramesesMouth of Prop
	(properties
		nsTop 31
		nsLeft 19
		view 1716
	)
)

(instance Rameses_b of Talker
	(properties
		x 121
		y 54
		view 1717
		loop 3
		disposeWhenDone 0
		talkWidth 175
		back 15
		textY 50
	)
	
	(method (init)
		(= font userFont)
		(super init: lauraBust lauraEyes lauraMouth &rest)
	)
)

(instance lauraBust of Prop
	(properties
		view 1717
		loop 1
	)
)

(instance lauraEyes of Prop
	(properties
		nsTop 20
		nsLeft 15
		view 1717
		loop 2
	)
)

(instance lauraMouth of Prop
	(properties
		nsTop 26
		nsLeft 15
		view 1717
	)
)

(instance sFXBeetles of Sound
	(properties
		flags $0001
		number 636
		loop -1
	)
)

(instance sFXFerret of Sound
	(properties
		flags $0001
		number 637
	)
)

(instance wrapMusic2 of WrapMusic
	(properties)
	
	(method (init)
		(= wrapSound sWrap)
		(super init: &rest)
	)
)

(instance sWrap of Sound
	(properties)
)
