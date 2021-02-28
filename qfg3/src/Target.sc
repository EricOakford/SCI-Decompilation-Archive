;;; Sierra Script 1.0 - (do not remove this comment)
(script# TARGET)
(include game.sh)
(use Main)
(use Feature)
(use Actor)
(use System)


(class TargFeature of Feature
	(properties
		signal skipCheck
	)
	
	(method (init)
		(super init:)
		(if (not gNewList) (= gNewList (List new:)))
		(gNewList add: self)
	)
	
	(method (dispose)
		(gNewList delete: self)
		(if
		(and (IsObject gNewList) (not (gNewList size?)))
			(gNewList dispose:)
			(= gNewList 0)
		)
		(super dispose:)
	)
	
	(method (getHurt)
	)
)

(class TargProp of Prop
	
	(method (init)
		(super init:)
		(if (not gNewList) (= gNewList (List new:)))
		(gNewList add: self)
	)
	
	(method (dispose)
		(gNewList delete: self)
		(if
		(and (IsObject gNewList) (not (gNewList size?)))
			(gNewList dispose:)
			(= gNewList 0)
		)
		(super dispose:)
	)
	
	(method (getHurt)
	)
)

(class TargActor of Actor
	
	(method (init)
		(super init:)
		(if (not gNewList) (= gNewList (List new:)))
		(gNewList add: self)
	)
	
	(method (dispose)
		(gNewList delete: self)
		(if
		(and (IsObject gNewList) (not (gNewList size?)))
			(gNewList dispose:)
			(= gNewList 0)
		)
		(super dispose:)
	)
	
	(method (getHurt)
	)
)
