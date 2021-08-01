;;; Sierra Script 1.0 - (do not remove this comment)
(script# 77)
(include sci.sh)
(use Main)
(use KQ5Room)
(use Sync)
(use Motion)
(use Actor)
(use System)

(public
	rm077 0
)

(local
	local0
	local1
	local2
)
(instance rm077 of KQ5Room
	(properties
		picture 77
		south 13
	)
	
	(method (init)
		(super init:)
		(HandsOff)
		(= local0 (theGame setCursor: invCursor 1))
		(self setScript: cartoon)
		(muscha init:)
		(ego
			normal: 0
			view: 776
			loop: 3
			cel: 1
			posn: 175 150
			init:
		)
		((ego head?) hide:)
		((ScriptID 763) doit:)
		(theMusic number: 800 loop: -1 vol: 127 playBed:)
	)
	
	(method (doit &tmp temp0)
		(cond 
			(script (script doit:))
			((= temp0 (self edgeToRoom: (ego edgeHit?))) (curRoom newRoom: temp0))
		)
	)
	
	(method (dispose)
		(theGame setCursor: local0 1)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
		)
	)
)

(instance cartoon of Script
	(properties)
	
	(method (doit &tmp [temp0 2])
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(smallArm cycleSpeed: 3 setCycle: End self init:)
				(muscha loop: 0 cel: 0)
			)
			(1
				(= local2 1)
				(smallArm loop: 5 setCycle: Fwd)
				(= cycles 25)
			)
			(2
				(curRoom drawPic: 78)
				(smallArm dispose:)
				(ball1 init:)
				(ball2 cycleSpeed: 3 setCycle: Fwd init:)
				(muscha
					view: 778
					loop: 0
					cycleSpeed: 0
					setScript: (catMove new:)
					posn: 156 70
				)
				(theMouth cycleSpeed: 3 cel: 4 init:)
				(wArm cycleSpeed: 2 setScript: (catMove new:) init:)
				(eArm cycleSpeed: 2 setScript: (catMove new:) init:)
				(ego hide:)
				((ego head?) hide:)
				(= cycles 1)
			)
			(3 (= seconds 1))
			(4
				(theMouth setCycle: MouthSync 973)
				(SpeakAudio 973 self)
			)
			(5
				(theMouth setScript: 0 setCycle: 0 cel: 4)
				(= cycles 1)
			)
			(6
				(cls)
				(curRoom drawPic: 77)
				(ball1
					view: 776
					loop: 7
					posn: 126 100
					setCycle: Fwd
					cycleSpeed: 3
				)
				(ball2 dispose:)
				(muscha
					view: 776
					loop: 0
					cel: 0
					cycleSpeed: 5
					setScript: (muschaMove new:)
					posn: 127 105
				)
				(wArm hide:)
				(eArm hide:)
				(ego
					normal: 0
					view: 776
					loop: 3
					setCel: 0
					posn: 152 136
					setPri: 9
					show:
				)
				(theMouth setCycle: 0 hide:)
				(SpeakAudio 974 self)
			)
			(7 (SpeakAudio 975 self))
			(8
				(= local2 0)
				(muscha loop: 2 cel: 0 setCycle: End self)
			)
			(9
				(= local2 1)
				(theMusic number: 801 loop: -1 playBed:)
				(cls)
				(ball1 dispose:)
				(muscha hide:)
				(ego hide:)
				(curRoom drawPic: 79)
				(mordac init:)
				(mHand cycleSpeed: 2 setCycle: Fwd init:)
				(cat cycleSpeed: 1 setScript: catMove init:)
				(= cycles 1)
			)
			(10 (= seconds 2))
			(11
				(mordac setCycle: MouthSync 927)
				(SpeakAudio 927 self)
			)
			(12
				(cls)
				(mordac setCycle: MouthSync 928)
				(SpeakAudio 928 self)
			)
			(13
				(cls)
				(mordac setCycle: MouthSync 929)
				(SpeakAudio 929 self)
			)
			(14
				(cls)
				(mordac setCycle: 0)
				(SpeakAudio 930 self)
			)
			(15
				(cls)
				(curRoom drawPic: 80)
				(mordac hide:)
				(mHand hide:)
				(cat hide:)
				(wArm hide:)
				(eArm hide:)
				(alex init: setCycle: End self cycleSpeed: 3)
			)
			(16 (alex setCycle: Beg self))
			(17
				(alexFace init: setCycle: MouthSync 931)
				(SpeakAudio 931 self)
			)
			(18
				(alexFace hide:)
				(alex setCycle: End self)
			)
			(19 (alex setCycle: Beg self))
			(20
				(alexFace show: setCycle: MouthSync 932)
				(SpeakAudio 932 self)
			)
			(21
				(alexFace hide:)
				(alex setCycle: Beg self)
			)
			(22
				(cls)
				(alex dispose:)
				(alexFace setCycle: 0 dispose:)
				(curRoom drawPic: 79)
				(mordac show:)
				(mHand show:)
				(cat show:)
				(mordac setCycle: MouthSync 933)
				(SpeakAudio 933 self)
			)
			(23
				(cls)
				(mordac setCycle: MouthSync 934)
				(SpeakAudio 934 self)
			)
			(24
				(theMusic number: 802 loop: -1 playBed:)
				(cls)
				(curRoom drawPic: 77)
				(mordac setCycle: 0 dispose:)
				(mHand dispose:)
				(cat dispose:)
				(muscha
					view: 776
					loop: 0
					cel: 0
					posn: 127 105
					setCycle: 0
					setScript: 0
					show:
				)
				(ego show:)
				(smallMouth init: setCycle: MouthSync 976)
				(SpeakAudio 976 self)
			)
			(25
				(smallMouth hide:)
				(muscha loop: 8 cel: 0 cycleSpeed: 4 setCycle: End self)
			)
			(26
				(smallMouth
					setCycle: MouthSync 977
					x: (+ (smallMouth x?) 1)
					show:
				)
				(SpeakAudio 977 self)
			)
			(27
				(smallMouth hide:)
				(ego get: 27)
				(SolvePuzzle 2)
				(muscha loop: 9 cel: 0 setCycle: End self)
			)
			(28
				(smallMouth setCycle: 0 dispose:)
				(cls)
				(curRoom drawPic: 78)
				(ego hide:)
				(muscha
					view: 778
					setScript: (mouthMove new:)
					posn: 156 70
				)
				(theMouth cycleSpeed: 3 show:)
				(wArm show:)
				(eArm show:)
				(= cycles 1)
			)
			(29 (= seconds 2))
			(30
				(theMouth setCycle: MouthSync 978)
				(SpeakAudio 978 self)
			)
			(31
				(theMouth setScript: 0 setCycle: 0 cel: 4)
				(muscha setScript: 0 setCycle: 0 cel: 0)
				(cls)
				(SpeakAudio 979 self)
			)
			(32
				(theMusic fade:)
				(cls)
				(curRoom newRoom: 13)
			)
		)
	)
)

(instance catMove of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setCycle: Beg)
				(= state -1)
				(= seconds (Random 5 8))
			)
		)
	)
)

(instance mouthMove of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client cel: (Random 0 8))
				(= state -1)
				(= cycles 3)
			)
		)
	)
)

(instance muschaMove of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client cycleSpeed: 5 loop: 0 cel: 0 setCycle: End self)
			)
			(1
				(client loop: 1 cel: 0 setCycle: Fwd)
			)
		)
	)
)

(instance faceMove of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(alexFace hide:)
				(alex setCycle: Beg self)
			)
			(1
				(alexFace show: setCycle: Fwd)
				(= seconds (Random 3 5))
				(= state -1)
			)
		)
	)
)

(instance alex of Prop
	(properties
		x 173
		y 102
		view 784
	)
)

(instance alexFace of Prop
	(properties
		x 181
		y 65
		view 784
		loop 1
		cel 5
		priority 15
		signal $0010
		cycleSpeed 3
	)
)

(instance smallArm of Prop
	(properties
		x 127
		y 105
		view 776
		loop 4
		priority 4
		signal $0010
	)
)

(instance smallMouth of Prop
	(properties
		x 126
		y 94
		view 776
		loop 6
		priority 4
		signal $0010
	)
)

(instance muscha of Prop
	(properties
		x 127
		y 105
		view 776
		priority 3
		signal $0010
	)
)

(instance theMouth of Prop
	(properties
		x 128
		y 95
		view 1027
		cel 4
		priority 5
		signal $0010
	)
)

(instance wArm of Prop
	(properties
		x 130
		y 121
		view 778
		loop 3
		priority 8
		signal $0010
	)
)

(instance eArm of Prop
	(properties
		x 182
		y 125
		view 778
		loop 2
		priority 8
		signal $0010
	)
)

(instance mordac of Prop
	(properties
		x 157
		y 70
		view 1031
		loop 3
		cel 4
		priority 9
		signal $0010
	)
)

(instance mHand of Prop
	(properties
		x 167
		y 82
		view 782
		cel 4
		priority 7
		signal $0010
	)
)

(instance cat of Prop
	(properties
		x 189
		y 80
		view 782
		loop 1
		priority 7
		signal $0010
	)
)

(instance ball1 of Prop
	(properties
		x 161
		y 112
		view 778
		loop 4
		priority 15
		signal $0011
	)
)

(instance ball2 of Prop
	(properties
		x 161
		y 112
		view 778
		loop 5
		cel 2
		priority 14
		signal $0010
	)
)
