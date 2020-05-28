;;; Sierra Script 1.0 - (do not remove this comment)
(script# 544)
(include game.sh)
(use Main)
(use brain)
(use SQRoom)
(use Sq4Feature)
(use Motion)
(use User)
(use System)

(public
	rm544 0
)

(local
	draggedIcon
	saveBits
	oldSpeed
)
(instance rm544 of SQRoom
	(properties
		picture 544
		style PLAIN
		lookStr 1
	)
	
	(method (init &tmp [str 20])
		(self setRegions: BRAIN)
		(super init:)
		(ego setCycle: 0)
		(HandsOn)
		(= oldSpeed speed)
		(= speed 0)
		(if (not (Btst fDeletedKQ43)) (KQ43Icon init:))
		(if (not (Btst fDeletedLL4)) (LLIcon init:))
		(if (not (Btst fDeletedDroids)) (droidIcon init:))
		(SQ4Icon init:)
		(if (not (brain formatting?)) (brainIcon init:))
		(toiletIcon init:)
		(exitBar init:)
		(if (Btst fDeletedKQ43)
			(StrCpy @str {Memory Free: 841,912,226 GBytes})
		else
			(StrCpy @str {Memory Free: 2,451 KBytes})
		)
		(= saveBits
			(DoDisplay @str
				#at 40 171
				#color myTextColor7
				#back myLowlightColor
			)
		)
		(= useSortedFeatures FALSE)
		(music fade: 75 10 10 0)
		(globalSound number: 842 vol: 127 loop: 1 play:)
	)
	
	(method (doit &tmp userCurEvent userCurEventX userCurEventY)
		(super doit:)
		(= userCurEvent (User curEvent?))
		(cond 
			((OneOf (userCurEvent type?) mouseDown mouseUp keyDown))
			((not draggedIcon))
			((draggedIcon cycler?))
			(else
				(= userCurEventX (userCurEvent x?))
				(= userCurEventY (userCurEvent y?))
				(if
				(InRect 47 52 270 149 userCurEventX userCurEventY)
					(draggedIcon x: userCurEventX y: userCurEventY)
				)
			)
		)
	)
	
	(method (dispose)
		(= speed oldSpeed)
		(= useSortedFeatures TRUE)
		(music fade: 127 10 10 0)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_TALK (narrator say: 2))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance KQ43Icon of Sq4Prop
	(properties
		x 60
		y 60
		view 544
		priority 14
		signal fixPriOn
		lookStr 3
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DO)
			(cond 
				((toiletIcon onMe: self) (toiletIcon setScript: flushFlash self))
				((== draggedIcon self) (= draggedIcon 0) (= cel 0))
				(draggedIcon)
				(else (= draggedIcon self) (= cel 1))
			)
		else
			(super doVerb: theVerb &rest)
		)
	)
	
	(method (cue)
		(DoDisplay saveBits)
		(= saveBits
			(DoDisplay
				{Memory Free: 841,912,226 GBytes}
				#at 40 171
				#color myTextColor7
				#back myLowlightColor
			)
		)
		(= draggedIcon 0)
		(Bset fDeletedKQ43)
		(self dispose:)
	)
)

(instance SQ4Icon of Sq4Prop
	(properties
		x 100
		y 60
		view 544
		loop 1
		priority 14
		signal fixPriOn
		lookStr 4
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DO)
			(cond 
				((toiletIcon onMe: self) (toiletIcon setScript: flushFlash self))
				((== draggedIcon self) (= draggedIcon 0) (= cel 0))
				(draggedIcon)
				(else (= draggedIcon self) (= cel 1))
			)
		else
			(super doVerb: theVerb &rest)
		)
	)
	
	(method (cue)
		(= quit TRUE)
		(self dispose:)
	)
)

(instance LLIcon of Sq4Prop
	(properties
		x 150
		y 60
		view 544
		loop 2
		priority 14
		signal fixPriOn
		lookStr 5
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DO)
			(cond 
				((toiletIcon onMe: self) (toiletIcon setScript: flushFlash self))
				((== draggedIcon self) (= draggedIcon 0) (= cel 0))
				(draggedIcon)
				(else (= draggedIcon self) (= cel 1))
			)
		else
			(super doVerb: theVerb &rest)
		)
	)
	
	(method (cue)
		(= draggedIcon 0)
		(Bset fDeletedLL4)
		(self dispose:)
	)
)

(instance droidIcon of Sq4Prop
	(properties
		x 60
		y 95
		view 544
		loop 3
		priority 14
		signal fixPriOn
		lookStr 6
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(cond 
				((toiletIcon onMe: self) (toiletIcon setScript: flushFlash self))
				((== draggedIcon self) (= draggedIcon 0) (= cel 0))
				(draggedIcon)
				(else (= draggedIcon self) (= cel 1))
			)
		else
			(super doVerb: theVerb &rest)
		)
	)
	
	(method (cue)
		(= draggedIcon 0)
		(Bset fDeletedDroids)
		(SolvePuzzle fDroidsGone 5)
		(self dispose:)
	)
)

(instance brainIcon of Sq4Prop
	(properties
		x 100
		y 95
		view 544
		loop 4
		priority 14
		signal fixPriOn
		lookStr 7
	)
	
	(method (doVerb theVerb &tmp theX theY)
		(cond 
			((== theVerb V_DO)
				(cond 
					((toiletIcon onMe: self) (toiletIcon setScript: flushFlash self))
					((== draggedIcon self) (= draggedIcon 0) (= cel 0))
					(draggedIcon)
					(else (= draggedIcon self) (= cel 1))
				)
			)
			((and (== theVerb V_LOOK) (not (HaveMouse)))
				(= theX ((User curEvent?) x?))
				(= theY ((User curEvent?) y?))
				(theGame
					setCursor: ((theIconBar curIcon?) cursor?) TRUE 304 172
				)
				(Animate (cast elements?) FALSE)
				(narrator say: 7)
				(theGame
					setCursor: ((theIconBar curIcon?) cursor?) TRUE theX theY
				)
			)
			(else (super doVerb: theVerb &rest))
		)
	)
	
	(method (cue)
		(= draggedIcon 0)
		(DoDisplay saveBits)
		(= saveBits
			(DoDisplay
				{Initiating Formatting Sequence Value to 5000}
				#at 40 171
				#color myTextColor7
				#back myLowlightColor
			)
		)
		(brain formatting: 5000)
		(music number: 869 loop: -1 play: setVol: 75)
		(SolvePuzzle fStartFormatting 15)
		(self dispose:)
	)
)

(instance toiletIcon of Sq4Prop
	(properties
		x 270
		y 155
		view 544
		loop 5
		priority 13
		signal fixPriOn
		lookStr 9
	)
)

(instance exitBar of Sq4Feature
	(properties
		x 36
		y 37
		nsTop 32
		nsLeft 31
		nsBottom 43
		nsRight 42
		sightAngle 90
		lookStr 10
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO (curRoom newRoom: 514))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance flushFlash of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(globalSound number: 839 loop: 1 play: self)
				(client setCel: 1)
				(= cycles 2)
			)
			(1 (draggedIcon setCycle: Forward))
			(2
				(draggedIcon cue:)
				(= cycles 2)
			)
			(3
				(client setCel: 0)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)
