;;; Sierra Script 1.0 - (do not remove this comment)
(script# 120)
(include game.sh)
(use Main)
(use Procs)
(use Tactor)
(use TScripts)
(use Intrface)
(use CDActor)
(use LoadMany)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	Room120 0
)

(local
	[local0 2]
	local2
	[str 200]
)
(instance Room120 of FRoom
	(properties
		picture 120
		style $0000
		east 130
		south 170
		west 110
		southX 126
		southY 180
		eastX 320
		eastY 137
		westY 132
	)
	
	(method (init)
		(ego posn: 60 150 init:)
		(Face ego cinderella)
		(if (> walkSound 0)
			(Load SOUND walkSound)
		)
		(switch (theCindStory state?)
			(0
				(LoadMany SOUND 29 129)
			)
		)
		(super init:)
		(theGame setScript: theZapCursor)
		(theIconBar enable: show:)
		(LoadMany VIEW 616 618 612 611)
		(cinderella
			selection: 0
			view: 610
			setLoop: 5
			cel: 2
			normal: 0
			ignoreActors: TRUE
			posn: 201 132 29
			init:
		)
		(headCin
			posn: (- (cinderella x?) 4) (cinderella y?) 29
			ignoreActors: TRUE
			init:
		)
		(arm
			posn: (- (cinderella x?) 4) (cinderella y?) 29
			cycleSpeed: 30
			ignoreActors: TRUE
			setCycle: EndLoop
			init:
		)
		(curRoom setScript: storySelect)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(script 0)
			((== (theCindStory state?) 1)
				(if
					(and
						(>= (ego x?) (godmother x?))
						(== (godmother loop?) 7)
					)
					(curRoom setScript: turnRight)
				)
				(if
					(and
						(< (ego x?) (godmother x?))
						(== (godmother loop?) 6)
					)
					(curRoom setScript: turnLeft)
				)
			)
		)
	)
	
	(method (dispose)
		(if (theMusic handle?)
			(theMusic fade: 0 15 12 1)
			(= walkSound 3)
		)
		(super dispose: &rest)
	)
	
	(method (enterSpecial)
		(self cue:)
	)
)

(instance turnLeft of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(godmother setLoop: 8 setCycle: EndLoop self)
			)
			(1
				(godmother setLoop: 7 setCycle: Forward)
				(= cycles 1)
			)
			(2 (self dispose:))
		)
	)
)

(instance turnRight of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(godmother setLoop: 9 setCycle: EndLoop self)
			)
			(1
				(godmother setLoop: 6 setCycle: Forward)
				(= cycles 1)
			)
			(2 (self dispose:))
		)
	)
)

(instance storySelect of HandsOffScript

	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame setScript: theZapCursor)
				(= ticks 240)
			)
			(1
				(= local2 1)
				(theCindStory selected: TRUE incState:)
				(= gGCindStory theCindStory)
				(proc10_20 (inventory at: 0))
				(theMusic number: 29 priority: 15 setLoop: -1 play:)
				(self setScript: (ScriptID 91 1) self)
			)
			(2
				(localSound number: 129 play:)
				(godmother view: 616 cel: 0 posn: 167 109 loop: 0 init:)
				(cinderella selection: 0)
				(= cycles 1)
			)
			(3
				(godmother setCycle: EndLoop self)
			)
			(4
				(godmother setLoop: 6 setCycle: Forward self)
				(Say 0 godmother cinderella self 1 120 0)
			)
			(5
				(Print 120 1
					#at 70 150
					#color 55
					#time 5
					#dispose
					self
				)
			)
			(6
				(curRoom newRoom: 140)
				(self dispose:)
			)
		)
	)
)

(instance arm of Prop
	(properties
		view 610
		loop 1
		signal ignrAct
	)
	
	(method (doVerb theVerb)
		(cinderella doVerb: theVerb)
	)
)

(instance headCin of Prop
	(properties
		view 610
		signal ignrAct
	)
	
	(method (doVerb theVerb)
		(cinderella doVerb: theVerb)
	)
)

(instance wheels of Actor
	(properties
		view 120
		loop 2
	)
)

(instance wheels2 of Actor
	(properties
		view 120
		loop 2
	)
)

(instance coach of Actor
	(properties
		x -10
		y 60
		view 120
	)
)

(instance cinderella of CDActor
	(properties
		talkerID 2
	)
	
	(method (setUp)
		((= talkerObj cinTalkObj)
			setUp: cinBust cinEyes cinMouth
		)
	)
)

(instance cinTalkObj of TalkerObj
	(properties
		x -1
		y 97
		nsTop 10
		nsLeft 108
		view 611
	)
)

(instance cinBust of View
	(properties
		nsTop 21
		nsLeft 47
		view 611
		loop 6
	)
)

(instance cinEyes of Prop
	(properties
		nsTop 28
		nsLeft 50
		view 611
		loop 4
		cycleSpeed 36
	)
)

(instance cinMouth of Prop
	(properties
		nsTop 36
		nsLeft 41
		view 611
		loop 2
		cycleSpeed 12
	)
)

(instance godmother of Tactor
	(properties
		talkerID 3
	)
	
	(method (init)
		((= talkerObj godMomTalkObj)
			setUp: godMomBust godMomEyes godMomMouth
		)
		(super init: &rest)
	)
)

(instance godMomTalkObj of TalkerObj
	(properties
		x -1
		y 97
		nsTop 10
		nsLeft 108
		view 618
	)
)

(instance godMomBust of View
	(properties
		nsTop 28
		nsLeft 43
		view 618
		loop 6
	)
)

(instance godMomEyes of Prop
	(properties
		nsTop 33
		nsLeft 46
		view 618
		loop 4
		cycleSpeed 36
	)
)

(instance godMomMouth of Prop
	(properties
		nsTop 41
		nsLeft 41
		view 618
		loop 2
		cycleSpeed 12
	)
)

(instance localSound of Sound
	(properties
		flags mNOPAUSE
		number 129
	)
)
