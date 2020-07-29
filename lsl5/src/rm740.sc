;;; Sierra Script 1.0 - (do not remove this comment)
(script# 740)
(include game.sh)
(use Main)
(use LLRoom)
(use LoadMany)
(use Wander)
(use Sound)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm740 0
)

(local
	local0
	local1
	local2
	local3
	local4
	local5 =  1
	local6
	local7
)
(instance rm740 of LLRoom
	(properties
		picture 740
	)
	
	(method (init)
		(LoadMany SOUND 742 742 752 753 754 752 753 754)
		(Load SCRIPT WANDER)
		(super init:)
		(HandsOn)
		(User canControl: FALSE)
		(theIconBar disable: ICON_WALK ICON_TALK ICON_ITEM ICON_INVENTORY)
		(larry init:)
		(theMusic number: 740 setLoop: -1 flags: mNOPAUSE play: 127)
		(self setScript: sRoom)
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(cast contains: bodyPart)
				(!= local4 (= local3 (GetTime SYSTIME1)))
			)
			(= local4 local3)
			(++ local7)
			(if (< local5 1000) (++ local5))
			(cond 
				((== local7 30) (TimePrint 740 0))
				((not (mod local7 8))
					(cond 
						((not local6) (TimePrint 740 1))
						((and (> local7 19) (< local6 5)) (TimePrint 740 2))
					)
				)
			)
		)
	)
	
	(method (dispose)
		(DisposeScript WANDER)
		(super dispose:)
	)
	
	(method (doVerb)
		(return 1)
	)
)

(instance sRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 2))
			(1
				(TimePrint 740 3)
				(= seconds 2)
			)
			(2
				(larry
					cycleSpeed: 6
					moveSpeed: 6
					observeControl: cGREEN
					setCycle: Forward
					setMotion: Wander
				)
				(= seconds 3)
			)
			(3
				(TimePrint 740 4)
				(TimePrint 740 5 #at -1 185)
				(larry setMotion: 0)
				(theIconBar curIcon: (theIconBar at: 2))
				(theGame setCursor: ((theIconBar curIcon?) cursor?))
				(Animate (cast elements?) 0)
				(bodyPart init: perform: findWhere)
				(curRoom setScript: sRoar)
			)
		)
	)
)

(instance sRoar of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 1 4)))
			(1 (soundFX number: 742 play:))
			(2 (self init:))
		)
	)
)

(instance sFadeout of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(larry
					illegalBits: 0
					setLoop: 1
					setCycle: Forward
					setMotion: MoveTo 147 78 self
				)
			)
			(1
				(larry setMotion: MoveTo 131 56 self)
			)
			(2
				(theMusic fade: 80 10 10 0)
				(if modelessDialog (modelessDialog dispose:))
				(curRoom newRoom: 750)
			)
		)
	)
)

(instance bodyPart of Actor
	(properties
		description {her}
		view 740
		signal ignrAct
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(self onMe: (User curEvent?))
				(not (Random 0 local5))
			)
			(= local1 1)
		)
		(if
			(and
				(not (-- local1))
				(!= (curRoom script?) sFadeout)
			)
			(self perform: findWhere)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2
				(switch cel
					(1 (TimePrint 740 6))
					(0 (TimePrint 740 6))
					(3 (TimePrint 740 7))
					(2 (TimePrint 740 7))
					(4 (TimePrint 740 8))
					(5 (TimePrint 740 9))
					(6 (TimePrint 740 10))
				)
			)
			(3
				(larry cue: 1)
				(self perform: findWhere)
				(switch cel
					(1
						(TimePrint 740 11 #at -1 185 #title {The Audience} #mode teJustCenter #dispose)
					)
					(0
						(TimePrint 740 12 #at -1 185 #title {The Audience} #mode teJustCenter #dispose)
					)
					(3
						(TimePrint 740 13 #at -1 185 #title {The Audience} #mode teJustCenter #dispose)
					)
					(2
						(TimePrint 740 14 #at -1 185 #title {The Audience} #mode teJustCenter #dispose)
					)
					(4
						(TimePrint 740 15 #at -1 185 #title {The Audience} #mode teJustCenter #dispose)
					)
					(5
						(TimePrint 740 16 #at -1 185 #title {The Audience} #mode teJustCenter #dispose)
					)
					(6
						(TimePrint 740 17 #at -1 185 #title {The Audience} #mode teJustCenter #dispose)
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance findWhere of Code
	(properties)
	
	(method (doit param1 &tmp temp0 temp1)
		(while
			(==
				(OnControl
					4
					(= temp0 (Random 10 310))
					(= temp1 (Random 10 170))
				)
				2
			)
		)
		(param1 setCel: (Random 0 6) posn: temp0 temp1)
		(= local1 (Random 60 360))
		(= local1 (* local5 (Random 5 30)))
	)
)

(instance larry of Actor
	(properties
		x 160
		y 100
		description {you and Lana}
		lookStr {That's you and Lana wrestling, but you can't touch you from way up here!}
		view 743
		signal $4800
	)
	
	(method (init)
		(super init:)
		(self setCycle: EndLoop)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(3 (TimePrint 740 18))
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
	
	(method (cue param1 &tmp temp0 temp1 temp2)
		(switch param1
			(0 (self setCycle: 0))
			(1
				(if (> local6 9)
					(HandsOff)
					(curRoom setScript: sFadeout)
				else
					(patFX number: (+ 752 (/ local6 2)) play:)
					(++ local6)
					(cond 
						((< (= temp0 (GetAngle x y 153 84)) 90)
							(= temp1 (+ x (Random 10 40)))
							(= temp2 (- y (Random 10 40)))
						)
						((< temp0 180)
							(= temp1 (+ x (Random 10 40)))
							(= temp2 (+ y (Random 10 40)))
						)
						((< temp0 270)
							(= temp1 (- x (Random 10 40)))
							(= temp2 (+ y (Random 10 40)))
						)
						(else
							(= temp1 (- x (Random 10 40)))
							(= temp2 (- y (Random 10 40)))
						)
					)
					(self
						setLoop: 0
						setCycle: Forward
						setMotion: MoveTo temp1 temp2 self
					)
				)
			)
		)
	)
)

(instance soundFX of Sound)

(instance patFX of Sound
	(properties
		flags mNOPAUSE
	)
)
