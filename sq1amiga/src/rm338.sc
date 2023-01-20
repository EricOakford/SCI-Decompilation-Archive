;;; Sierra Script 1.0 - (do not remove this comment)
(script# 338)
(include sci.sh)
(use Main)
(use SQRoom)
(use Actor)

(public
	rm338 0
)

(instance rm338 of SQRoom
	(properties
		picture 38
		style $0008
		horizon 150
		north 37
		east 18
		south 138
		west 238
	)
	
	(method (init)
		(ego init:)
		(self setRegions: 704)
		(super init:)
		(addToPics add: pod eachElementDo: #init doit:)
	)
)

(instance pod of PicView
	(properties
		x 139
		y 124
		description {escape pod}
		lookStr {The escape pod is destined to remain a monument to your visit.}
		view 138
		loop 1
	)
)
