;;; Sierra Script 1.0 - (do not remove this comment)
(script# 130)
(include game.sh)
(use Main)
(use LLRoom)
(use Talker)
(use LoadMany)
(use Sound)
(use Actor)
(use System)

(public
	rm130 0
)

(local
	[local0 2]
	local2
)
(instance rm130 of LLRoom
	(properties
		picture 130
	)
	
	(method (init)
		(LoadMany VIEW 130 1130)
		(LoadMany SOUND 130 140)
		(HandsOff)
		(cigar init: setPri: 15 addToPic:)
		(vMouth init: setPri: 13 addToPic:)
		(brow1 init: addToPic:)
		(brow2 init: addToPic:)
		(addToPics doit:)
		(super init:)
		(SetFFRoom 155)
		(theMusic number: 130 flags: mNOPAUSE loop: 0 play:)
		(self setScript: sCartoon)
		(keyDownHandler add: sCartoon)
		(mouseDownHandler add: sCartoon)
	)
	
	(method (dispose)
		(mouseDownHandler delete: sCartoon)
		(keyDownHandler delete: sCartoon)
		(super dispose:)
		(theMusic fade: 0 15 12 1)
	)
)

(instance vMouth of View
	(properties
		x 154
		y 129
		view 1130
	)
)

(instance brow1 of View
	(properties
		x 127
		y 53
		view 130
	)
)

(instance brow2 of View
	(properties
		x 179
		y 55
		view 130
		loop 1
	)
)

(instance cigar of View
	(properties
		x 201
		y 104
		view 130
		loop 3
	)
)

(instance officeHit of Sound
	(properties
		number 130
	)
)

(instance Mr__Bigg of Talker
	(properties
		x -1
		y 10
		nsTop 79
		nsLeft 111
		view 1130
		loop 3
		priority 14
		signal fixPriOn
		talkWidth 280
		name "Mr. Bigg"
	)
	
	(method (init)
		(= mouth biggMouth)
		(super init: &rest)
	)
	
	(method (doit)
		(super doit:)
		(if (not (mod (++ local2) 32)) (Palette PALCycle 206 254 -1))
	)
	
	(method (show &tmp pnv)
		(if (not underBits)
			(= underBits
				(Graph GSaveBits nsTop nsLeft nsBottom nsRight VMAP)
			)
		)
		(= pnv (PicNotValid))
		(PicNotValid TRUE)
		(if bust
			(DrawCel
				(bust view?)
				(bust loop?)
				(bust cel?)
				(+ (bust nsLeft?) nsLeft)
				(+ (bust nsTop?) nsTop)
				14
			)
		)
		(if eyes
			(DrawCel
				(eyes view?)
				(eyes loop?)
				(eyes cel?)
				(+ (eyes nsLeft?) nsLeft)
				(+ (eyes nsTop?) nsTop)
				14
			)
		)
		(if mouth
			(DrawCel
				(mouth view?)
				(mouth loop?)
				(mouth cel?)
				(+ (mouth nsLeft?) nsLeft)
				(+ (mouth nsTop?) nsTop)
				14
			)
		)
		(DrawCel view loop cel nsLeft nsTop 10)
		(Graph GShowBits nsTop nsLeft nsBottom nsRight VMAP)
		(PicNotValid pnv)
	)
	
	(method (cycle obj &tmp theCel)
		(if (and obj (obj cycler?))
			(= theCel (obj cel?))
			((obj cycler?) doit:)
			(if (!= theCel (obj cel?))
				(DrawCel
					(obj view?)
					(obj loop?)
					(obj cel?)
					(+ (obj nsLeft?) nsLeft)
					(+ (obj nsTop?) nsTop)
					14
				)
				(obj
					nsRight:
						(+
							(obj nsLeft?)
							(CelWide (obj view?) (obj loop?) (obj cel?))
						)
				)
				(obj
					nsBottom:
						(+
							(obj nsTop?)
							(CelHigh (obj view?) (obj loop?) (obj cel?))
						)
				)
				(Graph
					GShowBits
					(+ (obj nsTop?) nsTop)
					(+ (obj nsLeft?) nsLeft)
					(+ (obj nsBottom?) nsTop)
					(+ (obj nsRight?) nsLeft)
					VMAP
				)
			)
		)
	)
)

(instance biggMouth of Prop
	(properties
		view 1130
		priority 14
		signal fixPriOn
		cycleSpeed 10
	)
)

(instance sCartoon of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(Say Mr__Bigg 130 0 #dispose #caller self)
			)
			(2 (= seconds 3))
			(3
				(theIconBar disable:)
				(DrawPic 1 -32762)
				(= seconds 3)
			)
			(4
				(theIconBar enable:)
				(DoDisplay 3 myDisplayColor 130 1)
				(= seconds 3)
			)
			(5 (curRoom newRoom: 140))
		)
	)
	
	(method (handleEvent event &tmp [temp0 2])
		(if
			(and
				(== (self state?) 4)
				(or
					(== (event type?) keyDown)
					(== (event type?) mouseDown)
				)
			)
			(event claimed: TRUE)
			(self changeState: 5)
		)
	)
)
