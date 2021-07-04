;;; Sierra Script 1.0 - (do not remove this comment)
(script# 25)
(include game.sh)
(use Main)
(use Inset)
(use Actor)

(public
	chapInset 0
)

(instance chapInset of Inset
	(properties
		picture -2
		view 916
		x 8
		y 10
		priority 198
	)
	
	(method (init whoCares theRoomOrInset reg &tmp temp0)
		(= x (+ (Abs (thePlane left:)) 18))
		(= y (+ (Abs (thePlane top?)) 10))
		(super init: whoCares theRoomOrInset reg 1)
		(quitButton init:)
		(volumeUp init:)
		(volumeDown init:)
		(hotButton init:)
		(marker init:)
		(playButton init:)
		(self drawInset:)
	)
)

(instance hotButton of View
	(properties
		x 58
		y 55
		view 916
		loop 2
	)
	
	(method (init)
		(super init: &rest)
		(self
			setPri: 199
			ignoreActors: TRUE
			loop: (if (user useHotspots?) 2 else 1)
			cel: 0
		)
		(UpdateScreenItem self)
	)
	
	(method (doVerb)
		(if (user useHotspots?)
			(user useHotspots: FALSE)
		else
			(user useHotspots: TRUE)
		)
		(self loop: (if (user useHotspots?) 2 else 1))
		(UpdateScreenItem self)
	)
)

(instance quitButton of View
	(properties
		x 144
		y 86
		view 916
		loop 4
	)
	
	(method (init)
		(self setPri: 199 ignoreActors: TRUE)
		(super init:)
		(self setHotspot: 8 10)
	)
	
	(method (doVerb)
		(theGame save:)
		(= quit TRUE)
		(chapInset dispose:)
	)
)

(instance playButton of View
	(properties
		x 141
		y 22
		view 916
		loop 3
	)
	
	(method (init)
		(self ignoreActors: TRUE setPri: 199)
		(super init:)
		(self setHotspot: 8 10)
	)
	
	(method (doVerb)
		(chapInset dispose:)
	)
)

(instance volumeDown of View
	(properties
		x 222
		y 63
		view 916
		loop 6
	)
	
	(method (init)
		(self ignoreActors: TRUE setPri: 199)
		(super init:)
	)
	
	(method (doVerb &tmp newVol [temp1 2])
		(= newVol (theGame masterVolume:))
		(if (< (-- newVol) 0)
			(= newVol 0)
		)
		(theGame masterVolume: newVol)
	)
)

(instance volumeUp of View
	(properties
		x 222
		y 46
		view 916
		loop 5
	)
	
	(method (init)
		(self ignoreActors: TRUE setPri: 199)
		(super init:)
	)
	
	(method (doVerb &tmp newVol [temp1 2])
		(= newVol (theGame masterVolume:))
		(if (> (++ newVol) 15)
			(= newVol 15)
		)
		(theGame masterVolume: newVol)
	)
)

(instance marker of Actor
	(properties
		x 78
		y 52
		view 916
		loop 7
	)
	
	(method (init)
		(self ignoreActors: TRUE setPri: 199)
		(super init:)
		(cond 
			((< curChapter 1)
				(= curChapter 1)
			)
			((> curChapter 8)
				(= curChapter 8)
			)
		)
		(= x (+ x (* (- curChapter 1) 18)))
	)
)
