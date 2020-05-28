;;; Sierra Script 1.0 - (do not remove this comment)
(script# ULENCE) ;706
(include game.sh)
(use Main)
(use Sq4Narrator)
(use Sq4Feature)
(use PolyPath)
(use LoadMany)
(use Window)
(use Sound)
(use Motion)
(use Game)
(use System)

(public
	ulence 0
	theBiker 1
	theZapScript 2
	theDeathScript 3
	theDodgeR 4
	theDodgeL 5
	theBikeSound 6
	theSand 7
	theDunes 8
	tRoger 9
)

(local
	local0
	timesZapped
	local2
)
(class ulence of Region
	(properties
		beenInBar 0
		status 0
		deathLoop 0
		fieldOff 0
		bikerComing 0
		saveWindow 0
		egoBusy 0
	)
	
	(method (init)
		(if (!= systemWindow Sq1Window)
			(= saveWindow systemWindow)
			(= systemWindow Sq1Window)
		)
		(super init: &rest)
		(ShowStatus 1)
		(Load VIEW 636)
		(LoadMany SOUND 135 58 50)
		(forceField init:)
		(if (== curRoomNum 610)
			(forceField x: 52 y: 178 sightAngle: 90)
		)
		(if (not (OneOf curRoomNum 615 620))
			(music number: 58 loop: -1 vol: 127 play:)
		)
	)
	
	(method (doit &tmp temp0)
		(super doit:)
		(cond 
			(script 0)
			(
				(and
					(== (ego edgeHit?) SOUTH)
					(OneOf curRoomNum 609 610 611)
					(== (ulence fieldOff?) 0)
				)
				(HandsOff)
				(ego setMotion: 0 y: (- (ego y?) 2))
				(= local0 1)
				(curRoom setScript: theZapScript)
				(theZapScript init:)
			)
			(
				(and
					(& (ego onControl: origin) cLMAGENTA)
					(not local0)
					(== (ulence fieldOff?) 0)
				)
				(HandsOff)
				(ego setMotion: 0)
				(switch curRoomNum
					(611 (ego x: (- (ego x?) 2)))
					(612
						(if (and (> (ego x?) 185) (< (ego y?) 123))
							(ego y: (+ (ego y?) 2))
						else
							(ego y: (+ (ego y?) 2) x: (+ (ego x?) 2))
						)
					)
					(613 (ego y: (+ (ego y?) 2)))
					(614 (ego x: (- (ego x?) 4)))
				)
				(= local0 1)
				(curRoom setScript: theZapScript)
			)
			((and (& (ego onControl: origin) cLRED) local0) (= local0 0))
		)
	)
	
	(method (dispose)
		(= systemWindow saveWindow)
		(super dispose:)
	)
	
	(method (newRoom newRoomNumber)
		(= keep
			(OneOf newRoomNumber 609 610 611 612 613 614 615 620)
		)
		(= initialized FALSE)
		(super newRoom: newRoomNumber &rest)
	)
)

(class Sq1Window of SysWindow
	(properties
		underBits 0
		pUnderBits 0
		bordWid 3
	)
	
	(method (dispose)
		(SetPort 0)
		(Graph GRestoreBits underBits)
		(Graph GRestoreBits pUnderBits)
		(Graph GReAnimate lsTop lsLeft lsBottom lsRight)
		(super dispose:)
	)
	
	(method (open &tmp temp0 temp1)
		(SetPort 0)
		(= color myLowlightColor)
		(= back myTopBordColor)
		(= temp1 1)
		(if (!= priority -1) (= temp1 (| temp1 $0002)))
		(= lsTop (- top bordWid))
		(= lsLeft (- left bordWid))
		(= lsRight (+ right bordWid))
		(= lsBottom (+ bottom bordWid))
		(= underBits
			(Graph GSaveBits lsTop lsLeft lsBottom lsRight 1)
		)
		(if (!= priority -1)
			(= pUnderBits
				(Graph GSaveBits lsTop lsLeft lsBottom lsRight 2)
			)
		)
		(Graph
			GFillRect
			lsTop
			lsLeft
			lsBottom
			lsRight
			temp1
			back
			priority
		)
		(Graph
			GDrawLine
			(+ lsTop 1)
			(+ lsLeft 1)
			(+ lsTop 1)
			(- lsRight 2)
			myTextColor
			priority
		)
		(Graph
			GDrawLine
			(- lsBottom 2)
			(+ lsLeft 1)
			(- lsBottom 2)
			(- lsRight 2)
			myTextColor
			priority
		)
		(Graph
			GDrawLine
			(+ lsTop 1)
			(+ lsLeft 1)
			(- lsBottom 2)
			(+ lsLeft 1)
			myTextColor
			priority
		)
		(Graph
			GDrawLine
			(+ lsTop 1)
			(- lsRight 2)
			(- lsBottom 2)
			(- lsRight 2)
			myTextColor
			priority
		)
		(Graph GShowBits lsTop lsLeft lsBottom lsRight 1)
		(= type (| wCustom wNoSave))
		(super open:)
	)
)

(instance theDeathScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 635
					cel: 0
					setLoop: (ulence deathLoop?)
					setCycle: EndLoop self
				)
			)
			(1 (= seconds 4))
			(2
				(narrator modNum: 707 say: 1 self)
			)
			(3 (EgoDead iconDEAD deathSTIFF))
		)
	)
)

(instance theZapScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(++ timesZapped)
				(ego view: 636)
				(switch curRoomNum
					(609
						(if (ego inRect: 15 187 317 191) (ego setLoop: 2))
					)
					(612
						(if (and (> (ego x?) 185) (< (ego y?) 123))
							(ego setLoop: 3)
						else
							(ego setLoop: 7)
						)
					)
					(611
						(if (ego inRect: 0 181 235 190) (ego setLoop: 2))
					)
					(610 (ego setLoop: 2))
					(614 (ego setLoop: 0))
					(613 (ego setLoop: 3))
				)
				(zapSound play:)
				(= cycles 20)
			)
			(1
				(zapSound stop:)
				(if (not local2) (NormalEgo (ego loop?) 0))
				(= cycles 3)
			)
			(2
				(if local2
					(= timesZapped 4)
					(narrator modNum: ULENCE say: 1 self)
				else
					(= cycles 3)
				)
			)
			(3
				(switch timesZapped
					(1
						(tRoger modNum: ULENCE say: 1)
						(HandsOn)
						(self dispose:)
					)
					(2
						(tRoger modNum: ULENCE say: 2)
						(HandsOn)
						(self dispose:)
					)
					(3
						(switch (Random 0 5)
							(0 (tRoger modNum: ULENCE say: 3))
							(else 
								(tRoger modNum: ULENCE say: 4)
							)
						)
						(HandsOn)
						(self dispose:)
					)
					(else 
						(ego view: 635 setLoop: 0 setCel: 2)
						(= cycles 3)
					)
				)
			)
			(4
				(if (== curRoomNum 613) (ego setPri: 1))
				(ego setCycle: EndLoop self)
			)
			(5
				(narrator modNum: ULENCE say: 2 self)
			)
			(6 (EgoDead iconLIGHTNING deathFORCEFIELD))
		)
	)
)

(instance tongueScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(= local2 1)
				(ego setMotion: PolyPath mouseX mouseY self)
			)
			(1
				(curRoom setScript: theZapScript)
				(self dispose:)
			)
		)
	)
)

(instance theDodgeR of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: 0 setHeading: 90 self)
				(narrator modNum: ULENCE say: 3)
			)
			(1
				(HandsOn)
				(ego view: 635 setLoop: 0 setCel: 0)
			)
			(2
				(ulence status: 5)
				(HandsOff)
				(ego
					posn: (+ (ego x?) 12) (- (ego y?) 10)
					setLoop: 2
					setCycle: EndLoop self
				)
			)
			(3
				(SolvePuzzle fDodgedBikers 5)
				(NormalEgo (ego loop?) 0)
				(ego
					posn: (+ (ego x?) 24) (+ (ego y?) 5)
					heading: 90 self
				)
			)
			(4 (self dispose:))
		)
	)
	
	(method (handleEvent event &tmp temp0)
		(cond 
			((event claimed?) (return))
			(
				(and
					(== (theIconBar curIcon?) (theIconBar at: ICON_WALK))
					(or
						(== (event message?) dirE)
						(& (event type?) direction)
					)
				)
				(self cue:)
				(event claimed: TRUE)
			)
			(else (super handleEvent: event))
		)
	)
)

(instance theDodgeL of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: 0 setHeading: 270 self)
				(narrator modNum: ULENCE say: 4)
			)
			(1
				(HandsOn)
				(ego view: 635 setLoop: 1 setCel: 0)
			)
			(2
				(HandsOff)
				(ulence status: 5)
				(ego
					posn: (- (ego x?) 14) (- (ego y?) 7)
					setLoop: 3
					setCycle: EndLoop self
				)
			)
			(3
				(SolvePuzzle 77 5)
				(NormalEgo (ego loop?) 0)
				(ego
					posn: (- (ego x?) 25) (+ (ego y?) 2)
					setHeading: 225 self
				)
			)
			(4 (self dispose:))
		)
	)
	
	(method (handleEvent event &tmp temp0)
		(cond 
			((event claimed?) (return))
			(
				(and
					(== (theIconBar curIcon?) (theIconBar at: ICON_WALK))
					(or
						(== (event message?) dirE)
						(& (event type?) direction)
					)
				)
				(self cue:)
				(event claimed: TRUE)
			)
			(else (super handleEvent: event))
		)
	)
)

(instance theBiker of Sq4Actor
	(properties
		x 1000
		y 1000
		yStep 15
		view 632
		signal (| ignrAct ignrHrz)
		illegalBits $0000
		xStep 15
		lookStr 5
	)
	
	(method (doVerb theVerb param2)
		(switch theVerb
			(V_LOOK
				(narrator modNum: ULENCE say: 5)
			)
			(V_TALK
				(narrator modNum: ULENCE say: 6)
			)
			(V_DO
				(narrator modNum: ULENCE say: 7)
			)
			(V_SMELL
				(narrator modNum: ULENCE say: 8)
			)
			(V_TASTE
				(narrator modNum: ULENCE say: 9)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance forceField of Sq4Feature
	(properties
		y 189
		nsBottom 189
		nsRight 319
		sightAngle 180
		onMeCheck ignrAct
		lookStr 10
	)
	
	(method (doVerb theVerb param2)
		(switch theVerb
			(V_LOOK
				(narrator modNum: ULENCE say: 10)
			)
			(V_TASTE
				(if (== (ulence egoBusy?) 1)
					(narrator modNum: ULENCE say: 11)
				else
					(HandsOff)
					(narrator modNum: ULENCE say: 12)
					(curRoom setScript: tongueScript)
				)
			)
			(V_SMELL
				(switch (Random 0 6)
					(0
						(narrator modNum: ULENCE say: 13)
					)
					(1
						(narrator modNum: ULENCE say: 14)
					)
					(2
						(narrator modNum: ULENCE say: 15)
					)
					(3
						(narrator modNum: ULENCE say: 16)
					)
					(else 
						(narrator modNum: ULENCE say: 17)
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance theDunes of Sq4Feature
	(properties
		y 1
	)
	
	(method (doVerb theVerb param2)
		(switch theVerb
			(V_LOOK
				(narrator modNum: ULENCE say: 18)
				(if (OneOf curRoomNum 610 609)
					(narrator modNum: ULENCE say: 19)
				)
			)
			(V_SMELL
				(switch (Random 0 6)
					(0
						(narrator modNum: ULENCE say: 20)
					)
					(1
						(narrator modNum: ULENCE say: 21)
					)
					(2
						(narrator modNum: ULENCE say: 22)
					)
					(3
						(narrator modNum: ULENCE say: 23)
					)
					(4
						(narrator modNum: ULENCE say: 24)
					)
					(5
						(narrator modNum: ULENCE say: 25)
					)
					(6
						(narrator modNum: ULENCE say: 26)
					)
				)
			)
			(V_TASTE
				(switch (Random 0 4)
					(0
						(narrator modNum: ULENCE say: 27)
					)
					(1
						(narrator modNum: ULENCE say: 28)
					)
					(2
						(narrator modNum: ULENCE say: 29)
					)
					(3
						(narrator modNum: ULENCE say: 31)
					)
					(4
						(narrator modNum: ULENCE say: 30)
					)
					(5
						(narrator modNum: ULENCE say: 32)
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance theSand of Sq4Feature
	(properties
		y 1
	)
	
	(method (doVerb theVerb param2)
		(switch theVerb
			(V_SMELL
				(switch (Random 0 3)
					(0
						(narrator modNum: ULENCE say: 34)
					)
					(1
						(narrator modNum: ULENCE say: 35)
					)
					(2
						(narrator modNum: ULENCE say: 36)
					)
					(3
						(narrator modNum: ULENCE say: 37)
					)
				)
			)
			(V_LOOK
				(narrator modNum: ULENCE say: 38)
			)
			(V_TASTE
				(switch (Random 0 4)
					(0
						(narrator modNum: ULENCE say: 39)
					)
					(1
						(narrator modNum: ULENCE say: 40)
					)
					(2
						(narrator modNum: ULENCE say: 41)
					)
					(3
						(narrator modNum: ULENCE say: 42)
					)
					(4
						(narrator modNum: ULENCE say: 43)
					)
				)
			)
			(4
				(narrator modNum: ULENCE say: 44)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance theBikeSound of Sound
	(properties
		flags mNOPAUSE
		number 841
	)
)

(instance zapSound of Sound
	(properties
		flags mNOPAUSE
		number 135
	)
)

(instance tRoger of Sq4Talker
	(properties
		z 400
		noun 7
		modNum ULENCE
		view 1008
		talkerNum ROGER
		mouthOffsetX 21
		mouthOffsetY 32
		eyeOffsetX 27
		eyeOffsetY 21
	)
)
